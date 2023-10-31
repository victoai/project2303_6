package com.corn.market.member.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.corn.market.member.dao.MemberDao;
import com.corn.market.member.domain.KakaoToken;
import com.corn.market.member.domain.Member;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MemberController {

	@Autowired
	MemberDao dao;

	//----------------------회원가입---------------------//
	// 회원가입 페이지 이동 
	@GetMapping("/signup")
	public String signupForm() {
		return "signup/signUp";
	}

	//회원가입 완료  
	@PostMapping("/signup")
	public String signupPOST(Member member) throws Exception {
		dao.memberSignup(member);
		return "redirect:/login";
	}

	//아이디 중복체크 
	@ResponseBody
	@PostMapping("/idcheck")
	public int idcheck(@RequestBody String user_id) throws Exception {
		int result= dao.idCheck(user_id);	
		//System.out.println(user_id + " : " + result);
		return result; //아이디가 있으면 1 없으면 0
	} 

	//닉네임 중복체크 
	@ResponseBody
	@PostMapping("/nickcheck")
	public int nickcheck(@RequestBody String nickname) throws Exception {
		int result=dao.nicknameCheck(nickname);
		//System.out.println(nickname + " : " + result);
		return result; //닉네임이 있으면 1 없으면 0
	} 

	//----------------------로그인 --------------------//

	//카카오 로그인
	@GetMapping("/oauth/callback")
	public @ResponseBody String kakaoCallback(String code) { //Data를 리턴해준느 컨트롤러 함수

		//1. 토큰을 받기위해 post방식으로 key=value형식으로 사용자 정보 요청 
		//Retrofit2 
		//OkHttp
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders(); //헤더에는 컨텐츠 타입을 담을거임 
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String,String> params =new LinkedMultiValueMap();//body data 담아야함 
		params.add("grant_type", "authorization_code");
		params.add("client_id", "d2d3eda3457799ca1d69cf37f8cbfaf8");
		params.add("redirect_uri", "http://localhost:8188/oauth/callback");
		params.add("code", code);
		//원래 변수화 시켜야지 코드가 이쁜데 .. 나중에..	  

		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest=
				new HttpEntity<>(params,headers);
		//kakaoTokenRequest 는 body와header값을 가지고있는 엔티티가된다. 
		//exchanger가 HttpEntity를 받게되어있어서 만듬 
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
				);

		//json정보를 온걸 자바에어 다루기위해 처리하는것 ( json라이브러리추가 )
		ObjectMapper objectMapper = new ObjectMapper ();
		KakaoToken kakaoToken = null;
		try {
			kakaoToken = objectMapper.readValue(response.getBody(), KakaoToken.class);
			///받아온 response.getBody()를  KakaoToken클래스에 담아줄거임 변수명이 일치해야함 
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "카카오 토큰 요청 완료, 토큰요청에 대한 응답  :"+response.getBody(); 
	}

	// 로그인페이지  
	@GetMapping("/login")
	public String loginForm() {
		return "login/login";
	} 

	// 로그인 전 아이디, 비밀번호 확인
	@ResponseBody
	@PostMapping("/login/checklogin")
	public int checkLogin(@RequestBody Member member) throws Exception {
		// 아이디 비밀번호 확인
		int result = dao.checkLogin(member); 
		return result; //아이디 비밀번호 일치하면 1, 아니면 0
	}

	// 로그인 진행 
	@PostMapping("/login")
	public String login(Member member, boolean rememberMe, 
			HttpServletResponse response,HttpServletRequest request) throws Exception {

		//System.out.println("id : " + member.getUser_id());
		//System.out.println("passwd : " + member.getUser_pw());
		//System.out.println("rememberMe : " + rememberMe);

		// 로그인 유지 체크 확인 
		if (rememberMe == true) { 
			Cookie cookie = new Cookie("userId", member.getUser_id());
			cookie.setMaxAge(60 * 60 * 24 * 7);
			cookie.setPath("/"); 
			response.addCookie(cookie);
		}
		// 아이디 세션 등록 
		HttpSession session= request.getSession(); // 섹션 얻어오는거임 
		session.setAttribute("id", member.getUser_id());
		// 메인화면으로 이동 
		return "redirect:/main";
	}

	// 로그아웃 
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		session.invalidate();
		return "redirect:/login";
	}

}

