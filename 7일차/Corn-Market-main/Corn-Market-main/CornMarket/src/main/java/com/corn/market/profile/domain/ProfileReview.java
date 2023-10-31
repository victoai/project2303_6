package com.corn.market.profile.domain;

import java.util.ArrayList;

public class ProfileReview {
	
	private String user_id;
	private String nickname;
	private String temperature;
	private String town_name;
	private String join_date;
	private String profile_img;
	private ArrayList<Review> reviewList;
	
	public ProfileReview() {}

	public ProfileReview(String user_id, String nickname, String temperature, String town_name, String join_date,
			String profile_img, ArrayList<Review> reviewList) {
		super();
		this.user_id = user_id;
		this.nickname = nickname;
		this.temperature = temperature;
		this.town_name = town_name;
		this.join_date = join_date;
		this.profile_img = profile_img;
		this.reviewList = reviewList;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTown_name() {
		return town_name;
	}

	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public ArrayList<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(ArrayList<Review> reviewList) {
		this.reviewList = reviewList;
	}

	@Override
	public String toString() {
		return "ProfileReview [user_id=" + user_id + ", nickname=" + nickname + ", temperature=" + temperature
				+ ", town_name=" + town_name + ", join_date=" + join_date + ", profile_img=" + profile_img
				+ ", reviewList=" + reviewList + "]";
	}

}
