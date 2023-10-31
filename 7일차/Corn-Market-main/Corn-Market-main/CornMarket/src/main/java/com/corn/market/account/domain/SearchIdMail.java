package com.corn.market.account.domain;

public class SearchIdMail {

	private String user_name;
	private String email_id;
	private String email_domain;
	
	public SearchIdMail() {}

	public SearchIdMail(String user_name, String email_id, String email_domain) {
		super();
		this.user_name = user_name;
		this.email_id = email_id;
		this.email_domain = email_domain;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getEmail_domain() {
		return email_domain;
	}

	public void setEmail_domain(String email_domain) {
		this.email_domain = email_domain;
	}

	@Override
	public String toString() {
		return "SearchIdMail [user_name=" + user_name + ", email_id=" + email_id + ", email_domain=" + email_domain
				+ "]";
	}
	
}
