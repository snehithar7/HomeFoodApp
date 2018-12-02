package org.java.controller;

import java.util.List;

import org.java.dao.ItemD;
import org.java.dao.OrderD;
import org.java.model.OrderDetailInformation;
import org.java.model.OrderInformation;
import org.java.util.PaginationResult;
import org.java.validator.CustomerFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class AdminController {
	
	@Autowired
	   private OrderD orderD;
	 
	  
	
	   @Autowired
	   private CustomerFormValidator customerFormValidator;
	 
	
	// GET: Show Login Page
	   @RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
	   public String login(Model model) {
	 
	      return "login";
	   }
	   @RequestMapping(value = { "/admin/order" }, method = RequestMethod.GET)
	   public String orderView(Model model, @RequestParam("orderId") String orderId) {
	      OrderInformation orderInfo = null;
	      if (orderId != null) {
	         orderInfo = this.orderD.getOrderInfo(orderId);
	      }
	      if (orderInfo == null) {
	         return "redirect:/admin/orderList";
	      }
	      List<OrderDetailInformation> details = this.orderD.listOrderDetailInfos(orderId);
	      orderInfo.setDetails(details);
	 
	      model.addAttribute("orderInfo", orderInfo);
	 
	      return "order";
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
	            = orderD.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
	 
	      model.addAttribute("paginationResult", paginationResult);
	      return "orderList";
	   }
}
