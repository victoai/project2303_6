package com.acorn.baemin.cart.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.baemin.cart.domain.CartInfoDTO;
import com.acorn.baemin.cart.service.CartServiceImp;
import com.acorn.baemin.domain.MenuDTO;
import com.acorn.baemin.domain.StoreDTO;


@Controller
public class CartController {

	@Autowired
	CartServiceImp service;	
	
	@PostMapping("/cartList")
	public String receiveCartData(CartInfoDTO cartinfoDTO, Model model, @RequestParam int menuCode) {
		System.out.println("Menu Code: " + menuCode);
		List<StoreDTO> storeInfo = service.selectStoreInfo(menuCode);
		List<MenuDTO> menuInfo = service.selectMenuInfo(menuCode);
		model.addAttribute("cartInfo", cartinfoDTO);
		model.addAttribute("menuInfo", menuInfo);
		model.addAttribute("storeInfo", storeInfo);
		System.out.println(menuInfo);
		System.out.println(storeInfo);
		
	    return "home/cart_list";
	}
}
