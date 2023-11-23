package com.acorn.baemin.login.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acorn.baemin.domain.SellerDTO;
import com.acorn.baemin.domain.UserDTO;
import com.acorn.baemin.login.repository.LoginRepository;
import com.acorn.baemin.login.repository.LoginRepositoryI;
import com.acorn.baemin.login.service.LoginService;

@Controller
public class LoginController {


	@Autowired
	private LoginService loginService;


 		@GetMapping(value="/kakaoLogin")
							// RequestParam으로 jsp에서 넘겨준 code를 받아옴
 		public String kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
 			System.out.println("code : " + code);
 			
			// 받아온 코드를 토큰을 받는데 쓰이도록 넘겨줌
 			String access_Token = loginService.getAccessToken(code);
 			System.out.println("access_Token : " + access_Token);
 			
			// 받아온 토큰에 담겨져 있는 유저정보를 맵에 저장
 			HashMap<String, Object> userInfo = loginService.getUserInfo(access_Token);
 			System.out.println("access_Token : " + access_Token);

 			System.out.println("nickname : " + userInfo.get("profile_nickname"));
 			System.out.println("email : " + userInfo.get("account_email"));
 			
 			return "redirect:/home";
 	    	}


}