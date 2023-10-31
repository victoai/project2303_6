package com.corn.market.account.domain;

public class SearchIdPhone {
	
	private String user_name;
	private String phone;
	
	public SearchIdPhone() {}
	
	public SearchIdPhone(String user_name, String phone) {
		super();
		this.user_name = user_name;
		this.phone = phone;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "AccountIdPhone [user_name=" + user_name + ", phone=" + phone + "]";
	}
	
	
	
}
