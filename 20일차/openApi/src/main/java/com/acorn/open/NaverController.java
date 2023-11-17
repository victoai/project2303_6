package com.acorn.open;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NaverController {
	
	@Autowired
	NaverShopSearch search;
  
	@ResponseBody
	@RequestMapping(value = "/naver", method = RequestMethod.GET)
	public List<ItemDto>   naverShop( ) {	 
		  String result =   search.search("adidas");
		  System.out.println( result);
	      List<ItemDto> itemDtoList =search.fromJSOntoItems(result);	        
	      System.out.println( itemDtoList.size());
		  String s="dfdfd";
		return itemDtoList; //itemDtoList;
	}

}
