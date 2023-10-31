package com.corn.market.account.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailApiController {
	
	@Autowired
	private MailSendService mailSendService;
	
	//메일 전송 & code 쿠키에 담기
	@RequestMapping(value = "/account/pw/mail", method = RequestMethod.GET)
	public void sendMail(String email,HttpServletRequest request,HttpServletResponse response) {
		//System.out.println(email);
		String code = mailSendService.sendMail(email); //메일 전송
		//String code = mailSendService.makeMailCode(); //인증코드만 테스트
		//쿠키 생성 및 수정
		Cookie cookie = new Cookie("mail_code", code);
		cookie.setMaxAge(600); //10분
		response.addCookie(cookie);
	}
	
	//인증 코드 체크
	@ResponseBody
	@RequestMapping(value = "/account/pw/mail/code", method = RequestMethod.GET)
	public String checkMailCode(String mail_code,HttpServletRequest request) {
		//System.out.println("입력받은 값: "+mail_code);//
		String cookie_code = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie ck : cookies) {
				//System.out.println("쿠키이름: "+ck.getName());//
				if(ck.getName().equals("mail_code")) {
					cookie_code = ck.getValue();
					//System.out.println("쿠키값: "+cookie_code);//
				}//if
			}//for
		}//if
		int code_check_ = cookie_code.equals(mail_code) ? 1 : 0; //인증코드 일치시 1, 아니면 0
		String code_check = Integer.toString(code_check_);
		return code_check;
	}
	

}
