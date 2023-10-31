package com.corn.market.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corn.market.review.dao.ReviewDao;
import com.corn.market.review.domain.ReviewReg;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao dao;
	
	//리뷰 등록
	public void regReview(ReviewReg review) {
		dao.insertReview(review);
	}
	
	//판매글의 거래상태를 거래완료로 수정
	public void modifyPostStatus(String post_id) {
		dao.updatePostStatus(post_id);
	}
	
	//판매글의 거래상태 가져오기
	public String getPostStatus(String post_id) {
		return dao.selectPostStatus(post_id);
	}
	
}
