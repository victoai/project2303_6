package com.corn.market.chatting.domain;

public class ChattingContent {
	
	private String room_id;
	private String sender_id;
	private String chat_content;
	
	public ChattingContent() {}

	public ChattingContent(String room_id, String sender_id, String chat_content) {
		super();
		this.room_id = room_id;
		this.sender_id = sender_id;
		this.chat_content = chat_content;
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

	public String getChat_content() {
		return chat_content;
	}

	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}

	@Override
	public String toString() {
		return "ChattingContent [room_id=" + room_id + ", sender_id=" + sender_id + ", chat_content=" + chat_content
				+ "]";
	}

}
