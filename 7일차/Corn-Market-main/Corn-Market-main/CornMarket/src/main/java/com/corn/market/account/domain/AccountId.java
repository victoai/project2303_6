package com.corn.market.account.domain;

public class AccountId {
	
	private String user_id;
	
	public AccountId() {}

	public AccountId(String user_id) {
		super();
		this.user_id = user_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "AccountId [user_id=" + user_id + "]";
	}
	
	

}
