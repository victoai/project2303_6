package com.corn.market.chatting.domain;

public class ChattingContentList {
	
	private String sender_id; 
	private String date; //년 월 일
	private String chat_content;
	private String time;
	
	public ChattingContentList() {}

	public ChattingContentList(String sender_id, String date, String chat_content, String time) {
		super();
		this.sender_id = sender_id;
		this.date = date;
		this.chat_content = chat_content;
		this.time = time;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getChat_content() {
		return chat_content;
	}

	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ChattingContentList [sender_id=" + sender_id + ", date=" + date + ", chat_content=" + chat_content
				+ ", time=" + time + "]";
	}

}
