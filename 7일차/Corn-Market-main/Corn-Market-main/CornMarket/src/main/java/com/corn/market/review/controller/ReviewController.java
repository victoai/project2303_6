package com.corn.market.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corn.market.review.domain.ReviewReg;
import com.corn.market.review.service.ReviewService;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService service;
	
	@GetMapping("/review")
	public String reviewPage() {
		return "review/popup_review";
	}
	
	@PostMapping("/review")
	public String regReview(ReviewReg review) {
		//System.out.println("리뷰:"+review);
		service.regReview(review);
		return "redirect:/chatting/list";
	}
	//판매글의 거래상태를 거래완료로 수정
	@ResponseBody
	@RequestMapping(value="/review/post-end", produces = "application/json; charset=utf-8", 
	method = RequestMethod.POST)
	public String changePostStatus(@RequestBody String post_id) {
		//System.out.println("거래완료할 판매글id"+post_id);
		String status = service.getPostStatus(post_id);
		//System.out.println("거래상태:"+status);
		if(status.equals("판매중")) {
			service.modifyPostStatus(post_id);
			//System.out.println("done");
		}
		return status;
	}

}
