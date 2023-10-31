<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅 목록</title>
<link rel="stylesheet" href="${path}/resources/css/chatting/chat_list.css" />

</head>
<body>
	<div class="popup-wrap">
		<div class="chatlist_wrap">
			<h2>CHATTING LIST</h2>
			<ul>
			<c:forEach var="room" items="${list}" varStatus="st">
				<li>
					<img class="chat_profile" src="${path}${room.other_profile_img}" onclick="window.opener.location.href='<c:url value="/profile/${room.other_user_id}"/>'"/></img>
					<div class="chat_content_wrap" onclick="location.href='<c:url value="/chatting/list/${room.room_id}"/>'">
						<div class="chat_info">
							<div class="chat_nickname">${room.other_nickname}</div>
							<div class="chat_town">${room.post_town_name}</div>
							<div class="chat_date">${room.last_send_date}</div>
						</div>
						<div class="chat_contents">${room.last_chat_content}</div>
					</div>
					<input type="hidden" id="chat_room_id_${st.count}" value="${room.room_id}">
					<div class="chat_delete" id="${room.room_id}" onclick="removeChatRoom(this)"></div>
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>

	<input type="hidden" id="chat_user_id" value="${user_id}">
	<input type="hidden" id="delete_url" value="<c:url value='/chatting/list/delete' />">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${path}/resources/js/chatting/chatting_list.js"></script>
</body>
</html>