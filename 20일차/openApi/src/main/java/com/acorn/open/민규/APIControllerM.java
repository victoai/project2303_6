package com.acorn.open.민규;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIControllerM {

	@Autowired
	ApiExplorerM api;

	@ResponseBody
	@GetMapping(value = "/airM", produces = "application/json;charset=UTF-8")
	public String airApi() throws IOException {
		String result = api.getApiTest();
		return result;
	}

	@ResponseBody
	@GetMapping(value = "/airListM")
	public ArrayList<AirDTO> airAPI() throws IOException {
		String result = api.getApiTest();
		ArrayList<AirDTO> list = api.fromJSONItems(result);
		return list;
	}

	@RequestMapping(value = "/airCheckM", method = RequestMethod.GET)
	public void SearchAll2(Model model) throws SQLException, IOException {
		String result = api.getApiTest();
		ArrayList<AirDTO> list = api.fromJSONItems(result);		
		model.addAttribute("list", list);

	}
}
