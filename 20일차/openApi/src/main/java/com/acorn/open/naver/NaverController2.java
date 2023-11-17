package com.acorn.open.naver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NaverController2 {
	
	@Autowired
	ApiExamSearchBlog  api;	
		
	@ResponseBody
	@RequestMapping(value="/naver1" , method=RequestMethod.GET , produces = "application/json;charset=utf-8")
	public String  shopping() {		
		String result  =api.getShopping();
		return result;
	}	
	
	@ResponseBody
	@RequestMapping(value="/naver2" , method=RequestMethod.GET)
	public  List<ItemDto>  shopping2() {		
		String result  =      api.getShopping();
		List<ItemDto> list =  api.fromJSOntoItems(result);
		return list;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/naver3" , method=RequestMethod.GET)
	public  List<ItemDto>  shopping3() {		 
		List<ItemDto> list = new ArrayList<ItemDto>();
		list.add( new ItemDto("bread1" , "/test"  ,"test.jpg", 25000));
		list.add( new ItemDto("bread2" , "/test"  ,"test.jpg", 25000));
		list.add( new ItemDto("bread3" , "/test"  ,"test.jpg", 25000));
		list.add( new ItemDto("bread4" , "/test"  ,"test.jpg", 25000));		 
		return list;
	}

}
