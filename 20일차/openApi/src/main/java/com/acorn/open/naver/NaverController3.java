package com.acorn.open.naver;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

 
@Controller
public class NaverController3 {
	
	@ResponseBody
	@RequestMapping(value="/naverTest" , method=RequestMethod.GET , produces = "application/json;charset=utf-8")
	public String test() {
		ApiExamSearchBlog4 search = new ApiExamSearchBlog4();
		String result  = search.getShoppingList();
		return result;		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/naverTest2" , method=RequestMethod.GET , produces = "application/json;charset=utf-8")
	public ArrayList<ItemDto> test2() {
		ApiExamSearchBlog4 search = new ApiExamSearchBlog4();
		String result  = search.getShoppingList();
		ArrayList<ItemDto>  list  =  search.fromJsonItemList(result);
		return list;		
	}
}
