package org.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.java.dao.ItemD;
import org.java.entity.Item;
import org.java.form.CustomerForm;
import org.java.model.CartInformation;
import org.java.model.ItemInfo;
import org.java.util.PaginationResult;
import org.java.util.Utils;
import org.java.validator.CustomerFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	
	 @Autowired
	   private CustomerFormValidator customerFormValidator;
	 
	 @Autowired
	 private ItemD itemD;
	 
	 @RequestMapping("/")
	String index() {
		return "index";
		
	}
	
	 @RequestMapping("/403")
	   public String accessDenied() {
	      return "/403";
	   }
	 
	  
	   
	   @RequestMapping("/contactUs")
	   public String contact() {
	      return "contactUs";
	   }

	   
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
	      
	   //get the food item list from the database to display
	      
	 
	   }
	   
	// Product List
	   @RequestMapping({ "/productList" })
	   public String listProductHandler(Model model, //
	         @RequestParam(value = "name", defaultValue = "") String likeName,
	         @RequestParam(value = "page", defaultValue = "1") int page) {
	      final int maxResult = 5;
	      final int maxNavigationPage = 10;
	 
	      PaginationResult<ItemInfo> result = itemD.queryItems(page, //
	            maxResult, maxNavigationPage, likeName);
	 
	      model.addAttribute("paginationProducts", result);
	      return "productList";
	   }
	   
	 
	   @RequestMapping({ "/buyProduct" })
	   public String listProductHandler(HttpServletRequest request, Model model, //
	         @RequestParam(value = "code", defaultValue = "") String code) {
	 
	      Item product = null;
	      if (code != null && code.length() > 0) {
	         product = itemD.findProduct(code);
	      }
	      if (product != null) {
	 
	         //
	         CartInformation cartInfo = Utils.getCartInSession(request);
	 
	         ItemInfo itemInfo = new ItemInfo(product);
	 
	         cartInfo.addItem(itemInfo, 1);
	      }
	 
	      return "redirect:/shoppingCart";
	   }
}
