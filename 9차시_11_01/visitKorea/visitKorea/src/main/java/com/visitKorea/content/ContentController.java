package com.visitKorea.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.visitKorea.paging.PageHandler;
import com.visitKorea.sido.SidoDTO;
import com.visitKorea.sido.SidoService;

@Controller
public class ContentController {

	@Autowired
	ContentExplorer api;
	
	@Autowired
	ContentService cs;
	
	@Autowired
	SidoService ss;
	
	// 게시글 데이터 수집
	@ResponseBody
	@GetMapping(value = "/cotAll", produces = "application/json;charset=UTF-8")
	public void cotAPI() throws IOException {
		
		int totalcnt = api.getTotal();
		int listnum = 100;
		int lastlistnum = totalcnt % listnum;
		int pagenum = (totalcnt / listnum);
		if(lastlistnum != 0 ) {
			pagenum = (totalcnt / listnum) +1 ;
		}
		
		System.out.println("데이터 전체수 : " + totalcnt);
		for(int i = 1; i <= pagenum; i++) {
			ArrayList<ContentDTO> listResult = api.getContentAPI(listnum, i);
			cs.getInsertAll(listResult);
			System.out.println(listnum*i);
		}
	}
	
	//데이터 표출
	@GetMapping(value="/sights")
	public String sightsView(Model model) throws IOException{
		
		int totalcnt = cs.getTotalCnt();
		model.addAttribute("totalcnt" , totalcnt);
		
		ArrayList<SidoDTO> sidoList = ss.getSelectAll();
		model.addAttribute("sidoList" , sidoList);
//		
//		PageHandler handler = cs.getPaging(1);
//		model.addAttribute("handler" , handler);
//		
//		ArrayList<ContentDTO> list = cs.getSelectAll(handler.getStartList(), handler.getEndList());
//		model.addAttribute("contentList" , list);
//		
		return "area";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/sightslist" , method=RequestMethod.GET)
	//public Map<String, Object> sightsList(@RequestParam(required=false, defaultValue="1") int currentPage) throws IOException{
	public Map<String, Object> sightsList(@RequestParam(required=false, defaultValue="1") int currentPage) throws IOException{
		
		Map<String, Object> map  = new HashMap<>();
		
		PageHandler handler = cs.getPaging(currentPage);
		map.put("handler" , handler);
		
		ArrayList<ContentDTO> list = cs.getSelectAll(handler.getStartList(), handler.getEndList());
		map.put("contentList" , list);
		
		return map;
	}
	
}
