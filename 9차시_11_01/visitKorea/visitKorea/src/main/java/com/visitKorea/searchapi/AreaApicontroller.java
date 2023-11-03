//package com.visitKorea.searchapi;
//
//import java.io.IOException;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class AreaApicontroller {
//
//	
//	@Autowired
//	AreaApiExplorer areaapi;
//	
//	@Autowired
//	CategoryDAO dao;
//	
//	
//	
//	
//	// 지역기반 관광정보 조회
//	@ResponseBody
//	@GetMapping(value = "/areajson", produces = "application/json;charset=UTF-8")
//	public String areaApi() throws IOException {
//		int totalcnt = areaapi.getTotalCnt();
//		String result = areaapi.getAreaDataAPI(totalcnt);
//		return result;
//	}
//	
//	@GetMapping("/area")
//	public String areaData(Model model) throws IOException {
//		int totalcnt = areaapi.getTotalCnt(); 
//		String result = areaapi.getAreaDataAPI(totalcnt);
//		ArrayList<AreaDTO> list = areaapi.fromJSONoItems(result);
//	    model.addAttribute("areaData", list);
//		return "area";
//	}
//	
//}
