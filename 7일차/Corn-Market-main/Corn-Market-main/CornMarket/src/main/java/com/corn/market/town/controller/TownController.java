package com.corn.market.town.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.corn.market.town.domain.TownCertification;
import com.corn.market.town.domain.TownInfo;
import com.corn.market.town.service.TownService;

@Controller
public class TownController {
	
	@Autowired
	private TownService townService;

	//동네인증 페이지
	@GetMapping("/town")
	public String townCertificationGET() {
		
		return "town/popup_profile_mytown";
	}
	
	//동네인증 정보 받아서 테이블에 등록
	@PostMapping("/town")
	public String townCertificationPOST(@RequestBody TownInfo townInfo, HttpSession session) {
		System.out.println(townInfo);
		//session.setAttribute("id", "fourkimm"); //세션 테스트
		//인터셉터가 true (아이디가 세션이 있음)
		String id = (String) session.getAttribute("id");
		String town_code = townService.getTownCode(townInfo);
		TownCertification town = new TownCertification(id, town_code, townInfo.getLatitude(), townInfo.getLongitude());
		townService.deleteAndInputAddress(town);
		return "redirect:/town";
	}
	
}
