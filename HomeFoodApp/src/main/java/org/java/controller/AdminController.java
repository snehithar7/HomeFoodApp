package org.java.controller;
 
import java.util.List;
 
import org.apache.commons.lang.exception.ExceptionUtils;
import org.java.dao.OrderD;
import org.java.dao.ItemD;
import org.java.entity.Item;
import org.java.form.ItemForm;
import org.java.model.OrderDetailInformation;
import org.java.model.OrderInformation;
import org.java.model.ProductInfo;
import org.java.utils.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
public class AdminController {
 
   @Autowired
   private OrderD orderDAO;
 
   @Autowired
   private ItemD productDAO;
 

 
   @InitBinder
   public void myInitBinder(WebDataBinder dataBinder) {
      Object target = dataBinder.getTarget();
      if (target == null) {
         return;
      }
      System.out.println("Target=" + target);
 
     
   }
 
   // GET: Show Login Page
   @RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
   public String login(Model model) {
 
      return "login";
   }
 
 
 
   @RequestMapping(value = { "/admin/orderList" }, method = RequestMethod.GET)
   public String orderList(Model model, //
         @RequestParam(value = "page", defaultValue = "1") String pageStr) {
      int page = 1;
      try {
         page = Integer.parseInt(pageStr);
      } catch (Exception e) {
      }
      final int MAX_RESULT = 5;
      final int MAX_NAVIGATION_PAGE = 10;
 
      PaginationResult<OrderInformation> paginationResult //
            = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
 
      model.addAttribute("paginationResult", paginationResult);
      return "orderList";
   }
 
  
   

   @RequestMapping(value = { "/admin/order" }, method = RequestMethod.GET)
   public String orderView(Model model, @RequestParam("orderId") String orderId) {
      OrderInformation orderInfo = null;
      if (orderId != null) {
         orderInfo = this.orderDAO.getOrderInfo(orderId);
      }
      if (orderInfo == null) {
         return "redirect:/admin/orderList";
      }
      List<OrderDetailInformation> details = this.orderDAO.listOrderDetailInfos(orderId);
      orderInfo.setDetails(details);
 
      model.addAttribute("orderInfo", orderInfo);
 
      return "order";
   }
    //food Menu
   @RequestMapping({ "/admin/pListe" })
   public String listProductHandler(Model model, //
         @RequestParam(value = "name", defaultValue = "") String likeName,
         @RequestParam(value = "page", defaultValue = "1") int page) {
      final int maxResult = 5;
      final int maxNavigationPage = 10;
 
      PaginationResult<ProductInfo> result = productDAO.queryProducts(page, //
            maxResult, maxNavigationPage, likeName);
 
      model.addAttribute("paginationProducts", result);
      return "pListe";
   }
 
}
