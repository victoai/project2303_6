package com.acorn.open.uis;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataController {
	
	@Autowired
	ApiExplorer2 api;

	

		@ResponseBody
		@RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		public String test2() throws IOException {			 
			String result = "";		 
			result = api.getDatas();			 
			return result;
		} 
		

		@ResponseBody
		@RequestMapping(value = "/dataList", method = RequestMethod.GET)
		public ArrayList<DataDto> test3() throws IOException {
		 
			String   result = api.getDatas();
			ArrayList<DataDto>   list  =api.getDataList(result);			 
			return list;
		} 
		
		
		//데이타를 view와 함께 응답
		@RequestMapping(value = "/dataview", method = RequestMethod.GET )
		public String test(HttpServletRequest request) throws IOException {
			 
			 		
			String result = api.getDatas();
			request.setAttribute("result", api.getDataList(result));			
			return "dataView";
		}

}
