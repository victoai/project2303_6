package com.batis.paging;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class pageController {
	
	@Autowired
	pageServiceI service;
	
	//페이징
	@GetMapping("/listpage")
	public String logintest(String p, Model model) {
		int currentPage = 1;
		if(p != null) {
			currentPage = Integer.parseInt(p);
		}
		int toRecords = service.getTotalCnt();
		System.out.println(toRecords + ", "+ p);
		int pageSize = 3;
		int grpSize = 2;
		PageHandler handler = new PageHandler(currentPage, toRecords, pageSize, grpSize);
		List<BoardDTO> list = service.getListPage(currentPage, pageSize);
		
		model.addAttribute("handler",handler);
		model.addAttribute("list",list);
		
		return "listPage";
	}

	

}
