package com.corn.market.chatting.domain;

public class ChattingRoomDeleteInfo {

	private String user_id;
	private String room_id;
	
	public ChattingRoomDeleteInfo() {}

	public ChattingRoomDeleteInfo(String user_id, String room_id) {
		super();
		this.user_id = user_id;
		this.room_id = room_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	@Override
	public String toString() {
		return "ChattingRoomDeleteInfo [user_id=" + user_id + ", room_id=" + room_id + "]";
	}
	
}
