package com.corn.market.profile.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corn.market.post.domain.PostList;
import com.corn.market.profile.dao.ProfileDao;
import com.corn.market.profile.domain.ProfileReview;
import com.corn.market.profile.domain.ProfileSale;
import com.corn.market.profile.domain.ProfileUpdate;
import com.corn.market.profile.domain.Review;
import com.corn.market.profile.domain.Sale;

@Service
public class ProfileService {

	@Autowired
	private ProfileDao dao;
	
	//프로필+판매내역 조회
	public ProfileSale getProfileSales(String user_id, String post_status) {
		//판매내역 가져오기
		Map<String, String> saleMap = new HashMap<String, String>(2);
		saleMap.put("user_id", user_id);
		saleMap.put("post_status", post_status);
		ArrayList<Sale> list = (ArrayList<Sale>) dao.selectSale(saleMap);
		getPostImgThumbnail(list);
		ProfileSale profileSale = dao.selectProfileSale(user_id);
		profileSale.setSaleList(list);
		return profileSale;
	}
	
	//프로필+거래후기 조회
	public ProfileReview getProfileReviews(String user_id) {
		ArrayList<Review> list = (ArrayList<Review>) dao.selectReview(user_id);
		ProfileReview profileReview = dao.selectProfileReview(user_id);
		profileReview.setReviewList(list);
		return profileReview;
	}
	
	//프로필 수정 조회
	public ProfileUpdate getProfileInfo(String id) {
		ProfileUpdate profileUpdate = dao.selectProfileInfo(id);
		return profileUpdate;
	}
	
	//프로필 닉네임 수정 등록
	public void modifyNickname(String user_id, String nickname) {
		Map<String, String> nicknameMap = new HashMap<String, String>(2);
		nicknameMap.put("nickname", nickname);
		nicknameMap.put("user_id", user_id);
		dao.updateProfileNickname(nicknameMap);
	}
	
	//프로필 사진 수정 등록
	public void modifyProfileImage(String user_id, String profile_img) {
		Map<String, String> imgMap = new HashMap<String, String>(2);
		imgMap.put("profile_img", profile_img);
		imgMap.put("user_id", user_id);
		dao.updateProfileImage(imgMap);
	}
	
	//프로필 닉네임 중복 확인, 중복이면 1, 중복아니면 0
	public int checkNickname(String nickname) {
		return dao.checkNickname(nickname);
	}
	
	//프로필의 판매글 이미지 썸네일 가져오기
	public void getPostImgThumbnail(ArrayList<Sale> saleList) {
		for (Sale sale : saleList) {
			String[] imgs = sale.getPost_img().split(",");
			sale.setPost_img(imgs[0]);
		}
	}
}
