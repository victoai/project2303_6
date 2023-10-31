package com.corn.market.chatting.domain;

public class ChattingRoomInfo {

	private String room_id;
	private String other_user_id;
	private String other_profile_img;
	private String other_nickname;
	private String last_chat_content;
	private String last_send_date;
	private String post_town_name;
	
	public ChattingRoomInfo() {}

	public ChattingRoomInfo(String room_id, String other_user_id, String other_profile_img, String other_nickname,
			String last_chat_content, String last_send_date, String post_town_name) {
		super();
		this.room_id = room_id;
		this.other_user_id = other_user_id;
		this.other_profile_img = other_profile_img;
		this.other_nickname = other_nickname;
		this.last_chat_content = last_chat_content;
		this.last_send_date = last_send_date;
		this.post_town_name = post_town_name;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getOther_user_id() {
		return other_user_id;
	}

	public void setOther_user_id(String other_user_id) {
		this.other_user_id = other_user_id;
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

	public String getLast_chat_content() {
		return last_chat_content;
	}

	public void setLast_chat_content(String last_chat_content) {
		this.last_chat_content = last_chat_content;
	}

	public String getLast_send_date() {
		return last_send_date;
	}

	public void setLast_send_date(String last_send_date) {
		this.last_send_date = last_send_date;
	}

	public String getPost_town_name() {
		return post_town_name;
	}

	public void setPost_town_name(String post_town_name) {
		this.post_town_name = post_town_name;
	}

	@Override
	public String toString() {
		return "ChattingRoomInfo [room_id=" + room_id + ", other_user_id=" + other_user_id + ", other_profile_img="
				+ other_profile_img + ", other_nickname=" + other_nickname + ", last_chat_content=" + last_chat_content
				+ ", last_send_date=" + last_send_date + ", post_town_name=" + post_town_name + "]";
	}
	
}
