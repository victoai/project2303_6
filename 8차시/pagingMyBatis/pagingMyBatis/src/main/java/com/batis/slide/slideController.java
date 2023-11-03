package com.batis.slide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class slideController {
	
	@Autowired
	slideServiceI service;


	//슬라이드 Ajax
	@GetMapping("/slideHome")
	public String slideHome() {
		return "slidePage";
	}
	
	@ResponseBody
	@GetMapping("/slidelist")
	public Map< String , Object> slidePage(String p, Model model) {
		int currentPage = 1;
		if(p != null) {
			currentPage = Integer.parseInt(p);
		}
		int toRecords = service.getTotalCnt();
		System.out.println(toRecords + ", "+ p);
		int pageSize = 6;
		
		SlideHandler handler = new SlideHandler(currentPage, toRecords, pageSize);
		List<SlideDTO> list = service.getListSlide(currentPage, pageSize);
		
		//ajax에서 응답하는거라 model 사용 못함
		Map< String , Object>  map = new HashMap<String, Object>();
		int totalPage = handler.totalPage;
		System.out.println("총 갯수 = " + totalPage);
		System.out.println("출력 = " + list);
		
		map.put("totalPage",totalPage);
		map.put("list",list);
		
		return map;
	}

	
}
