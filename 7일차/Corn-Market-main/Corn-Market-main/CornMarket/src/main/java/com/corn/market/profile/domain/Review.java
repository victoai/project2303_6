package com.corn.market.profile.domain;

public class Review {
	
	private String user_id ;
	private String profile_img ;
	private String nickname;
	private String review;
	
	public Review() {}

	public Review(String user_id, String profile_img, String nickname, String review) {
		super();
		this.user_id = user_id;
		this.profile_img = profile_img;
		this.nickname = nickname;
		this.review = review;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Review [user_id=" + user_id + ", profile_img=" + profile_img + ", nickname=" + nickname + ", review="
				+ review + "]";
	}

}
