package com.corn.market.review.domain;

public class ReviewReg {

	private String room_id;
	private String rate;
	private String review;
	
	public ReviewReg() {}

	public ReviewReg(String room_id, String rate, String review) {
		super();
		this.room_id = room_id;
		this.rate = rate;
		this.review = review;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "ReviewReg [room_id=" + room_id + ", rate=" + rate + ", review=" + review + "]";
	}
	
}
