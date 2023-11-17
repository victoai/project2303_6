package com.acorn.open.naver;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

 
public class NaverControllerTest {

	
	
	@ResponseBody
	@GetMapping("/naverTest")
	public ArrayList<ItemDto> test(){		
		NaverService  service = new NaverService();
		String respnse  = service.getShoppingList();
		ArrayList<ItemDto> result  = service.fromJsonItem(respnse);
		return result;		
	}
}
