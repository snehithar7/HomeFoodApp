package org.java.controller;

import java.util.List;

import org.java.dao.ItemD;
import org.java.dao.OrderD;
import org.java.model.OrderDetailInformation;
import org.java.model.OrderInformation;
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
	   private ItemD productD;
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
}
