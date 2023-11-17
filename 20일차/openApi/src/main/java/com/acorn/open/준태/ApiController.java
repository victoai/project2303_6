package com.acorn.open.준태;

import java.io.IOException;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ApiController {
	 
	
	@Autowired
	ApiExplorer22 api2;
	  
	
 

	@RequestMapping("/apijun")
	public String testapi2(Model model) throws IOException {
		String result = api2.testapi();
		ArrayList<PMClass> list =api2.fromJSONtoItems(result);
	model.addAttribute("list", list);
		return "apitest2";
		
	}
	

 
}
