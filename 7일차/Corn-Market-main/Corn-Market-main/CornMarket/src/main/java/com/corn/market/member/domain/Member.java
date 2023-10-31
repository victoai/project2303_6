package com.corn.market.member.domain;

public class Member {

	private String user_name;
	private String user_id;
	private String nickname;
	private String user_pw;
	private String email;
	private String phone;
	
	public Member() {};
	
	public Member(String user_name, String user_id, String nickname, String user_pw, String email, String phone) {
		super();
		this.user_name = user_name;
		this.user_id = user_id;
		this.nickname = nickname;
		this.user_pw = user_pw;
		this.email = email;
		this.phone = phone;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Member [user_name=" + user_name + ", user_id=" + user_id + ", nickname=" + nickname + ", user_pw="
				+ user_pw + ", email=" + email + ", phone=" + phone + "]";
	}
	
}