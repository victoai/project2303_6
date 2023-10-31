package com.corn.market.chatting.domain;

import java.util.List;

public class ChattingInfo {
	
	private String seller_id;
	private String room_id;
	private String post_id;
	private String post_title;
	private String post_img;
	private String other_profile_img;
	private String other_nickname;
	private String post_price;
	private List<ChattingContentList> chatlist;
	
	public ChattingInfo() {}

	public ChattingInfo(String seller_id, String room_id, String post_id, String post_title, String post_img,
			String other_profile_img, String other_nickname, String post_price, List<ChattingContentList> chatlist) {
		super();
		this.seller_id = seller_id;
		this.room_id = room_id;
		this.post_id = post_id;
		this.post_title = post_title;
		this.post_img = post_img;
		this.other_profile_img = other_profile_img;
		this.other_nickname = other_nickname;
		this.post_price = post_price;
		this.chatlist = chatlist;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_img() {
		return post_img;
	}

	public void setPost_img(String post_img) {
		this.post_img = post_img;
	}

	public String getOther_profile_img() {
		return other_profile_img;
	}

	public void setOther_profile_img(String other_profile_img) {
		this.other_profile_img = other_profile_img;
	}

	public String getOther_nickname() {
		return other_nickname;
	}

	public void setOther_nickname(String other_nickname) {
		this.other_nickname = other_nickname;
	}
	
	public String getPost_price() {
		return post_price;
	}

	public void setPost_price(String post_price) {
		this.post_price = post_price;
	}

	public List<ChattingContentList> getChatlist() {
		return chatlist;
	}

	public void setChatlist(List<ChattingContentList> chatlist) {
		this.chatlist = chatlist;
	}

	@Override
	public String toString() {
		return "ChattingInfo [seller_id=" + seller_id + ", room_id=" + room_id + ", post_id=" + post_id
				+ ", post_title=" + post_title + ", post_img=" + post_img + ", other_profile_img=" + other_profile_img
				+ ", other_nickname=" + other_nickname + ", post_price=" + post_price + ", chatlist=" + chatlist + "]";
	}

}
