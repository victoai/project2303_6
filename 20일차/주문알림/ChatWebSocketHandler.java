package com.corn.market.chatting.api;

import java.util.Date;


/*
 * 
 * 	<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.9.3</version>
		</dependency>
	
		<!-- https://mvnrepository.com/artifact/org.json/json -->
		<dependency>
			<groupId>org.json</groupId>
			<artifactId>json</artifactId>
			<version>20160810</version>
		</dependency>
	
		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-mapper-asl</artifactId>
			<version>1.9.13</version>
		</dependency>
 * 
 */
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.corn.market.chatting.domain.ChattingContent;
import com.corn.market.chatting.domain.WebSocketInfo;
import com.corn.market.chatting.service.ChattingService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatWebSocketHandler extends TextWebSocketHandler {
	
	private ConcurrentHashMap<WebSocketInfo, WebSocketSession> sessionList = new ConcurrentHashMap<WebSocketInfo, WebSocketSession>();
	
	@Autowired
	private ChattingService service;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//log(session.getId() + " 연결됨");
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//log(session.getId() + " 연결 종료됨");
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//JSON 객체 Map으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> chatContent = objectMapper.readValue(message.getPayload(), Map.class);
		String type = chatContent.get("type");
		//log(session.getId() + "데이터구분:"+type);
		WebSocketInfo info = new WebSocketInfo(chatContent.get("room_id"), chatContent.get("sender_id"));
		
		//웹소켓 연결됨, 메시지 전송, 웹소켓 연결 끊어짐
		switch (type) {
		case "enter":
			//클라이언트 연결, Map에 세션 저장
			sessionList.put(info, session);
			break;
		case "send_msg":
			//같은 채팅방에 메세지 전송
			for(WebSocketInfo w : sessionList.keySet()) {
				if(w.getRoom_id().equals(chatContent.get("room_id"))) {
					sessionList.get(w).sendMessage(message);
				}
			}
			//데이터 저장
			String room_id = chatContent.get("room_id");
			String sender_id = chatContent.get("sender_id");
			String chat_content = chatContent.get("chat_content");
			ChattingContent chattingContent = new ChattingContent(room_id, sender_id, chat_content);
			service.regChattingContent(chattingContent);
			//System.out.println("데이터 저장");
			break;
		case "out":
			//클라이언트 연결 해제, 세션 삭제
			sessionList.remove(info);
			break;
		default:
			break;
		}
		
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log(session.getId() + "에서 예외 발생: " + exception.getMessage());
	}

	private void log(String logmsg) {
		System.out.println(new Date() + "-" + logmsg);
	}

}