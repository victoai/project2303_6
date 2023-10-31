package com.corn.market.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corn.market.account.domain.AccountId;
import com.corn.market.account.domain.AccountPw;
import com.corn.market.account.domain.SearchIdMail;
import com.corn.market.account.domain.SearchIdPhone;
import com.corn.market.account.domain.SearchPw;
import com.corn.market.account.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	//아이디 찾기 페이지로 이동
	@RequestMapping(value = "/account/id", method = RequestMethod.GET)
	public String searchIdGET() {
		return "account/idaccount";
	}
	
	//휴대폰으로 아이디 찾기 전 확인
	@ResponseBody
	@RequestMapping(value = "/account/id/phone/check", method = RequestMethod.POST)
	public int checkSearchIdPhone(@RequestBody SearchIdPhone idPhone) {
		AccountId accountId = service.searchIdPhone(idPhone);
		int check = (accountId.getUser_id()==""||accountId.getUser_id()==null) ? 0 : 1;
		//찾는 아이디가 없으면 0, 있으면 1
		return check;
	}
	//아이디 찾기 - 휴대폰번호로
	@RequestMapping(value = "/account/id/phone", method = RequestMethod.POST)
	public String searchIdPhone(SearchIdPhone phone, Model model) {
		//System.out.println(phone); //
		AccountId accountId = service.searchIdPhone(phone);
		model.addAttribute("accountId",accountId);
		return "account/idsuccess_popup";
	}
	
	//이메일로 아이디 찾기 전 확인
	@ResponseBody
	@RequestMapping(value = "/account/id/mail/check", method = RequestMethod.POST)
	public int checkSearchIdMail(@RequestBody SearchIdMail idMail) {
		AccountId accountId = service.searchIdMail(idMail);
		int check = (accountId.getUser_id()==""||accountId.getUser_id()==null) ? 0 : 1;
		//찾는 아이디가 없으면 0, 있으면 1
		return check;
	}
	//아이디 찾기 - 이메일로
	@RequestMapping(value = "/account/id/mail", method = RequestMethod.POST)
	public String searchIdMail(SearchIdMail mail, Model model) {
		//System.out.println(mail);
		AccountId accountId = service.searchIdMail(mail);
		model.addAttribute("accountId",accountId);
		return "account/idsuccess_popup";
	}
	
	//비밀번호 찾기 페이지 이동
	@RequestMapping(value = "/account/pw", method = RequestMethod.GET)
	public String searchPwGET() {
		return "account/pwaccount";
	}
	
	//이메일 인증번호 전송전 이메일 확인 checkEmail()
	@ResponseBody
	@RequestMapping(value = "/account/pw/mail/check", method = RequestMethod.POST)
	public int checkMail(@RequestBody String email) {
		//System.out.println("이메일 전송전 확인:"+email);
		int check = service.checkEmail(email);
		//이메일이 회원정보에 없으면 0, 있으면 1
		return check;
	}
	
	//비밀번호 찾기 전 확인 checkPw()
	@ResponseBody
	@RequestMapping(value = "/account/pw/check", method = RequestMethod.POST)
	public int checkSearchPw(@RequestBody SearchPw searchPw) {
		AccountPw accountPw = service.searchPw(searchPw);
		int check = (accountPw.getUser_pw()==""||accountPw.getUser_pw()==null) ? 0 : 1;
		//찾는 비밀번호가 없으면 0, 있으면 1
		return check;
	}
	//비밀번호 찾기
	@RequestMapping(value = "/account/pw", method = RequestMethod.POST)
	public String searchPwPOST(SearchPw searchPw, Model model) {
		//System.out.println(searchPw);
		AccountPw accountPw = service.searchPw(searchPw);
		model.addAttribute("accountPw", accountPw);
		return "account/pwsuccess_popup";
	}
	
}
