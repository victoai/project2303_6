package com.corn.market.account.domain;

public class AccountPw {
	
	private String user_pw;
	
	public AccountPw() {}

	public AccountPw(String user_pw) {
		super();
		this.user_pw = user_pw;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	@Override
	public String toString() {
		return "AccountPw [user_pw=" + user_pw + "]";
	}
	
	

}
