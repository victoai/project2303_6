package com.corn.market.account.api;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

	@Autowired
	private JavaMailSender mailSender;
	private int mailCode;
	
	//메일 코드 생성 (난수)
	public String makeMailCode() {
		int num = (int)(Math.random()*9000+1000);
		//System.out.println("인증번호: "+num);
		mailCode = num;
		String code = Integer.toString(num);
		return code;
	}
	
	//메일 내용
	private String mailContent(int code) {
		String content = "<div style=\"width: 600px;margin: 10px auto;\">"
				+"<p style=\"margin:0 0 24px;padding:0;font-family:'Apple SD Gothic','\00b9d1\00c740\00ace0\00b515','Nanum Gothic',sans-serif;font-size:26px;color:#000;letter-spacing:-1px;line-height:32px;font-weight:bold\">"
				+"이메일 인증을 진행해주세요</p>"
				+"<p style=\"margin:0 0 40px;padding:0;font-family:'Apple SD Gothic','\00b9d1\00c740\00ace0\00b515','Nanum Gothic',sans-serif;font-size:18px;color:#404040;letter-spacing:-1px;line-height:24px\">"
				+"안녕하세요. CornMarket을 이용해주셔서 감사합니다.<br>"
				+"비밀번호 찾기를 위해 아래 인증번호를 화면에 입력해주세요.</p>"
				+"<div style=\"width: 100%;height: 140px;background-color: #f9f9fe;border: 1px solid lightgrey;border-radius: 3px;\">"
				+"<div style=\"font-family:'Apple SD Gothic','\00b9d1\00c740\00ace0\00b515','Nanum Gothic',sans-serif;font-size:48px;color:#202020;letter-spacing:8px;line-height:140px;text-align: center;\">"
				+ code +"</div></div>"
				+"<p style=\"padding:16px 0 0;font-family:'Apple SD Gothic','\00b9d1\00c740\00ace0\00b515','Nanum Gothic',sans-serif;text-align:center;font-size:16px;color:#808080;letter-spacing:-1px;line-height:24px\">"
				+"본 인증번호의 유효기간은 10분 입니다.</p></div>";
		
		return content;
	}
	
	//메일 전송
	public String sendMail(String email) {
		String code = "";
		//스프링에서 제공하는 메일 API
		MimeMessage message = mailSender.createMimeMessage();
		try {
			//메일코드 생성 + 메일내용 작성
			makeMailCode();
			String content = mailContent(mailCode);
			code = Integer.toString(mailCode);
			
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			try {
				helper.setFrom(new InternetAddress("help.cornmarket.com","CornMarket","UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} // 보낸 사람 이름
			helper.setTo(email); // 받을 메일
			helper.setSubject("이메일 인증을 진행해주세요"); // 이메일 제목
			// true -> html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
			helper.setText(content, true); // 이메일 내용
			//메일 전송
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return code;
	}
	
}
