package com.corn.market.chatting.domain;

public class ChattingRoom {

	private String room_id;
	private String post_id;
	private String seller_id;
	private String buyer_id;
	
	public ChattingRoom() {}

	public ChattingRoom(String room_id, String post_id, String seller_id, String buyer_id) {
		super();
		this.room_id = room_id;
		this.post_id = post_id;
		this.seller_id = seller_id;
		this.buyer_id = buyer_id;
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

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	@Override
	public String toString() {
		return "ChattingRoom [room_id=" + room_id + ", post_id=" + post_id + ", seller_id=" + seller_id + ", buyer_id="
				+ buyer_id + "]";
	}
	
}
