package com.corn.market.chatting.domain;

import java.util.Objects;

public class WebSocketInfo {
	
	private String room_id;
	private String sender_id;

	public WebSocketInfo() {}

	public WebSocketInfo(String room_id, String sender_id) {
		super();
		this.room_id = room_id;
		this.sender_id = sender_id;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	@Override
	public String toString() {
		return "WebSocketInfo [room_id=" + room_id + ", sender_id=" + sender_id + "]";
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(room_id,sender_id);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(!(obj instanceof WebSocketInfo)) return false;
		WebSocketInfo w = (WebSocketInfo) obj;
		return this.room_id.equals(w.room_id) && this.sender_id.equals(w.sender_id);
	}
	
}
