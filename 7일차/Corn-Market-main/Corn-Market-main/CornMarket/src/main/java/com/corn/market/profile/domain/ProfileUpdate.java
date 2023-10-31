package com.corn.market.profile.domain;

public class ProfileUpdate {
	
	private String profile_img;
	private String nickname;
	
	public ProfileUpdate() {}

	public ProfileUpdate(String profile_img, String nickname) {
		super();
		this.profile_img = profile_img;
		this.nickname = nickname;
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

	@Override
	public String toString() {
		return "ProfileUpdateInfo [profile_img=" + profile_img + ", nickname=" + nickname + "]";
	}
	
}
