package com.corn.market.common.fileUpload;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.corn.market.profile.domain.ProfileUpdate;
import com.corn.market.profile.service.ProfileService;

@Controller
public class FileUploadTestController {

	@Autowired
	private FileUploadService uploadService;
	@Autowired
	private ProfileService service;
	
	//파일 한개
	@RequestMapping(value = "/profile/test1", method = RequestMethod.POST)
	public String fileTest1(MultipartFile file, HttpServletRequest request) {
		
		//서비스 메서드로 파일 업로드
		String url = uploadService.oneFileUpload(file, request);
		
		//DB에 이미지데이터 등록
		String id = "bb22";
		service.modifyProfileImage(id, url);
		
		//데이터 확인 테스트용
		ProfileUpdate profile = service.getProfileInfo(id);
		request.setAttribute("profile", profile);
		
		return "profile/img_test";
	}
	
	//파일 여러개
	@RequestMapping(value = "/profile/test2", method = RequestMethod.POST)
	public String fileTest2(MultipartHttpServletRequest files, HttpServletRequest request) {
		
		//서비스 메서드로 파일 업로드
		String url = uploadService.multiFileUpload(files, request);
		
		//DB에 이미지데이터 등록
		String id = "aa11";
		service.modifyProfileImage(id, url);

		//데이터 확인 테스트용
		ProfileUpdate profile = service.getProfileInfo(id);
		if(profile.getProfile_img().contains(",")) { //url에 콤마를 포함하고 있을 경우(사진 여러개)
			profile.setProfile_img(profile.getProfile_img().split(",")[0]); //url중 한개 가져오기
		} 
		request.setAttribute("profile", profile);
		
		return "profile/img_test";
	}
}
