package com.corn.market.profile.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.corn.market.common.fileUpload.FileUploadService;
import com.corn.market.profile.domain.ProfileReview;
import com.corn.market.profile.domain.ProfileSale;
import com.corn.market.profile.domain.ProfileUpdate;
import com.corn.market.profile.service.ProfileService;

@Controller
public class ProfileController {
	
	@Autowired
	ProfileService service;
	@Autowired
	private FileUploadService uploadService;
	
	//본인 프로필 기본 페이지 (판매중)
	@GetMapping("/profile")
	public String profileOnSale(HttpSession session, Model model) { 
		String id = (String) session.getAttribute("id");
		ProfileSale profileSale = service.getProfileSales(id, "판매중");
		model.addAttribute("profile", profileSale);
		return "profile/profile_page";
	}
	//본인 프로필 판매완료 페이지
	@GetMapping("/profile/offsale")
	public String profileOffSale(HttpSession session, Model model) { 
		String id = (String) session.getAttribute("id");
		ProfileSale profileSale = service.getProfileSales(id, "거래완료");
		model.addAttribute("profile", profileSale);
		return "profile/profile_page";
	}
	//본인 프로필 거래후기 페이지
	@GetMapping("/profile/review")
	public String profileReview(HttpSession session, Model model) { 
		String id = (String) session.getAttribute("id");
		ProfileReview profileReview = service.getProfileReviews(id);
		model.addAttribute("profile", profileReview);
		return "profile/profile_review";
	}
	
	//본인 프로필 수정 페이지
	@GetMapping("/profile/update")
	public String otherProfileUpdate(HttpSession session, Model model) { 
		String id = (String) session.getAttribute("id");
		ProfileUpdate profileUpdate = service.getProfileInfo(id);
		model.addAttribute("profile", profileUpdate);
		return "profile/popup_profile_update";
	}
	//닉네임 중복 확인
	@ResponseBody
	@GetMapping("/profile/update/check")
	public String checkNickname(HttpSession session, String nickname) {
		int result_ = service.checkNickname(nickname);
		String result = Integer.toString(result_); //중복일때 1, 중복아님 0
		//System.out.println("중복확인 (0:중복아님) : "+result);
		return result; 
	}
	//닉네임 수정
	@PostMapping("/profile/update/nickname")
	public String modifyNickname(HttpSession session, String nickname) {
		String id = (String) session.getAttribute("id");
		service.modifyNickname(id, nickname);
		return "redirect:/profile"; //프로필페이지
	}
	//프로필이미지 수정페이지
	@GetMapping("/profile/update/image")
	public String modifyProfileImagePage() {
		return "profile/popup_profile_img";
	}
	//프로필이미지 수정
	@PostMapping("/profile/update/image")
	public String modifyProfileImage(HttpSession session, MultipartFile file, 
			HttpServletRequest request) {
		String id = (String) session.getAttribute("id");
		//서비스 메서드로 파일 업로드
		String url = uploadService.oneFileUpload(file, request);
		//DB에 이미지데이터 등록
		service.modifyProfileImage(id, url);
		return "redirect:/profile/update"; //프로필수정페이지 맵핑으로
	}
	
}
