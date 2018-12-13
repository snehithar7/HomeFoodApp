package org.java.controller;
 
import java.io.IOException;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.java.dao.OrderD;
import org.java.dao.ItemD;
import org.java.entity.Item;
import org.java.form.CustomerForm;
import org.java.model.CartInformation;
import org.java.model.CustomerInfo;
import org.java.model.ProductInfo;
import org.java.utils.PaginationResult;
import org.java.utils.Utils;
import org.java.validator.CustomerFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
@Transactional
public class MainController {
 
   @Autowired
   private OrderD orderDAO;
 
   @Autowired
   private ItemD productDAO;
 
   @Autowired
   private CustomerFormValidator customerFormValidator;
 
   @InitBinder
   public void myInitBinder(WebDataBinder dataBinder) {
      Object target = dataBinder.getTarget();
      if (target == null) {
         return;
      }
      System.out.println("Target=" + target);
 
     
      if (target.getClass() == CartInformation.class) {
 
      }
 
      
      else if (target.getClass() == CustomerForm.class) {
         dataBinder.setValidator(customerFormValidator);
      }
 
   }
 
   @RequestMapping("/403")
   public String accessDenied() {
      return "/403";
   }
 
   @RequestMapping("/")
   public String home() {
      return "index";
   }
   
   @RequestMapping("/aboutUs")
   public String aboutUs() {
      return "aboutUs";
   }
  
   
   @RequestMapping("/contactUs")
   public String contact() {
      return "contactUs";
   }
 
  
// food List
   @RequestMapping({ "/foodMenu" })
   public String listProductHandler(Model model, //
         @RequestParam(value = "name", defaultValue = "") String likeName,
         @RequestParam(value = "page", defaultValue = "1") int page) {
      final int maxResult = 5;
      final int maxNavigationPage = 10;
 
      PaginationResult<ProductInfo> result = productDAO.queryProducts(page, //
            maxResult, maxNavigationPage, likeName);
 
      model.addAttribute("paginationProducts", result);
      return "foodMenu";
   }
 
   @RequestMapping({ "/buyProduct" })
   public String listProductHandler(HttpServletRequest request, Model model, //
         @RequestParam(value = "code", defaultValue = "") String code) {
 
      Item product = null;
      if (code != null && code.length() > 0) {
         product = productDAO.findProduct(code);
      }
      if (product != null) {
 
         //
         CartInformation cartInfo = Utils.getCartInSession(request);
 
         ProductInfo productInfo = new ProductInfo(product);
 
         cartInfo.addProduct(productInfo, 1);
      }
 
      return "redirect:/shoppingCart";
   }
 
   @RequestMapping({ "/shoppingCartRemoveProduct" })
   public String removeProductHandler(HttpServletRequest request, Model model, //
         @RequestParam(value = "code", defaultValue = "") String code) {
      Item product = null;
      if (code != null && code.length() > 0) {
         product = productDAO.findProduct(code);
      }
      if (product != null) {
 
         CartInformation cartInfo = Utils.getCartInSession(request);
 
         ProductInfo productInfo = new ProductInfo(product);
 
         cartInfo.removeProduct(productInfo);
 
      }
 
      return "redirect:/shoppingCart";
   }
 
   // POST: Update quantity for product in cart
   @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
   public String shoppingCartUpdateQty(HttpServletRequest request, //
         Model model, //
         @ModelAttribute("cartForm") CartInformation cartForm) {
 
      CartInformation cartInfo = Utils.getCartInSession(request);
      cartInfo.updateQuantity(cartForm);
 
      return "redirect:/shoppingCart";
   }
 
   // GET: Show cart.
   @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
   public String shoppingCartHandler(HttpServletRequest request, Model model) {
      CartInformation myCart = Utils.getCartInSession(request);
 
      model.addAttribute("cartForm", myCart);
      return "shoppingCart";
   }
 
   // GET: Enter customer information.
   @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
   public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
 
      CartInformation cartInfo = Utils.getCartInSession(request);
 
      if (cartInfo.isEmpty()) {
 
         return "redirect:/shoppingCart";
      }
      CustomerInfo customerInfo = cartInfo.getCustomerInfo();
 
      CustomerForm customerForm = new CustomerForm(customerInfo);
 
      model.addAttribute("customerForm", customerForm);
 
      return "shoppingCartCustomer";
   }
 
   // POST: Save customer information.
   @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
   public String shoppingCartCustomerSave(HttpServletRequest request, //
         Model model, //
         @ModelAttribute("customerForm") @Validated CustomerForm customerForm, //
         BindingResult result, //
         final RedirectAttributes redirectAttributes) {
 
      if (result.hasErrors()) {
         customerForm.setValid(false);
         // Forward to reenter customer info.
         return "shoppingCartCustomer";
      }
 
      customerForm.setValid(true);
      CartInformation cartInfo = Utils.getCartInSession(request);
      CustomerInfo customerInfo = new CustomerInfo(customerForm);
      cartInfo.setCustomerInfo(customerInfo);
 
      return "redirect:/shoppingCartConfirmation";
   }
 
   // GET: Show information to confirm.
   @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
   public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
      CartInformation cartInfo = Utils.getCartInSession(request);
 
      if (cartInfo == null || cartInfo.isEmpty()) {
 
         return "redirect:/shoppingCart";
      } else if (!cartInfo.isValidCustomer()) {
 
         return "redirect:/shoppingCartCustomer";
      }
      model.addAttribute("myCart", cartInfo);
 
      return "shoppingCartConfirmation";
   }
 
   // POST: Submit Cart (Save)
   @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
 
   public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
      CartInformation cartInfo = Utils.getCartInSession(request);
 
      if (cartInfo.isEmpty()) {
 
         return "redirect:/shoppingCart";
      } else if (!cartInfo.isValidCustomer()) {
 
         return "redirect:/shoppingCartCustomer";
      }
      try {
         orderDAO.saveOrder(cartInfo);
      } catch (Exception e) {
 
         return "shoppingCartConfirmation";
      }
 
      // Remove Cart from Session.
      Utils.removeCartInSession(request);
 
      // Store last cart.
      Utils.storeLastOrderedCartInSession(request, cartInfo);
 
      return "redirect:/shoppingCartFinalize";
   }
 
   @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
   public String shoppingCartFinalize(HttpServletRequest request, Model model) {
 
      CartInformation lastOrderedCart = Utils.getLastOrderedCartInSession(request);
 
      if (lastOrderedCart == null) {
         return "redirect:/shoppingCart";
      }
      model.addAttribute("lastOrderedCart", lastOrderedCart);
      return "shoppingCartFinalize";
   }
 
   @RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
   public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
         @RequestParam("code") String code) throws IOException {
      Item product = null;
      if (code != null) {
         product = this.productDAO.findProduct(code);
      }
      if (product != null && product.getImage() != null) {
         response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
         response.getOutputStream().write(product.getImage());
      }
      response.getOutputStream().close();
   }
 
}