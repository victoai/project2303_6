package com.acorn.open.현수;

import java.io.IOException;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiExplorerControllerh {

	@Autowired
	ApiExplorerh api;

	@ResponseBody
	@GetMapping(value = "/apih1", produces = "application/json;charset=UTF-8")
	public String openApi() throws IOException {
		String result = api.getApiTest();
		return result;

	}

	@GetMapping(value = "/apih")
	public String openApi2(Model model) throws IOException {
		String result = api.getApiTest();
		ArrayList<ItemDTO> list = api.fromJsonToItems(result);
		model.addAttribute("list", list);
		return "openApi";
	}
}