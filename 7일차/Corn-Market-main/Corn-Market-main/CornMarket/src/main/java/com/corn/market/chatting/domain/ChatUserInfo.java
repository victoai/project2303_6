package com.corn.market.chatting.domain;

public class ChatUserInfo {

	private String user_name;
	private String profile_img;
	
	public ChatUserInfo() {}

	public ChatUserInfo(String user_name, String profile_img) {
		super();
		this.user_name = user_name;
		this.profile_img = profile_img;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	@Override
	public String toString() {
		return "ChatUserInfo [user_name=" + user_name + ", profile_img=" + profile_img + "]";
	}
	
}
