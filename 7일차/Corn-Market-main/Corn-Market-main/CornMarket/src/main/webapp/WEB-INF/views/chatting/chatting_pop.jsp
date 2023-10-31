<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>채팅 - ${chat.other_nickname}</title>
    <link rel="stylesheet" href="${path}/resources/css/chatting/popup_chat.css" />
  </head>
  <body>
    <div class="container">
      <div class="popup-wrap" id="popup">
        <div class="popup">
          <div class="popup-head">
            <span class="head-title">CHATTING ROOM</span>
          </div>
          <div class="popup-body">
            <div class="body-content">
              <div class="body-titlebox">
                <div class="titlebox1">
                  <img class="chat_profile" src="${path}${info.profile_img}"  onclick="window.opener.location.href='${path}/profile'"/>
                  <p>${info.user_name}</p>
                  <input type="button" class="chatlist_btn" value="채팅목록" onclick="location.href='<c:url value="/chatting/list"/>'"/>
                </div>
                <div class="titlebox2" onclick=" window.opener.location.href='<c:url value="/post/${chat.post_id}"/>'">
                  <img class="chat_object" src="${path}${chat.post_img}"></img>
                  <div class="titlebox2-2">
                    <p>${chat.post_title}</p>
                    <p>${chat.post_price}원</p>
                  </div>
                </div>
              </div>
              <div id="chatArea">
                <div class="body-contentbox" id="chatMessageArea">
                <c:set var="dt" value="${chat.chatlist[0].date}"/>
                <c:set var="ct" value="0"/>
                <c:forEach var="content" items="${chat.chatlist}" varStatus="st">
                  <c:if test="${st.index == 0}">
                    <div class="chat_date" id="chat_date_${st.count}">${content.date}</div>
              </c:if>
              <c:if test="${st.index!=0 && content.date!=dt}">
                    <div class="chat_date" id="chat_date_${st.count}">${content.date}</div>
                    <c:set var="dt" value="${content.date}"/>
                    <c:set var="ct" value="${st.count}"/>
              </c:if>     
                  
                  <c:if test="${content.sender_id != id}">
                  <!-- 타인이 보낸 메시지 -->
                  <div class="other_profile">
                    <img class="other_img" src="${path}${chat.other_profile_img}" onclick="window.opener.location.href='${path}/profile/${content.sender_id}'"/>
                    <div class="other_say">${content.chat_content}</div>
                    <div class="other_time">${content.time}</div>
                  </div>
                  </c:if>
                  <c:if test="${content.sender_id == id}">
                  <!-- 본인이 보낸 메시지 -->
                  <div class="my_profile">
                    <div class="my_time">${content.time}</div>
                    <div class="my_say">${content.chat_content}</div>
                  </div>
                  </c:if>
                </c:forEach>
                </div>
        <input type="hidden" value="${ct}" id="list_size" />
              </div>
            </div>
          </div>
          <div class="popup-foot">
          <c:if test="${chat.seller_id == id}">
            <input type="button" value="거래완료" class="deal_end" id="deal_end" />
          </c:if>
            <input type="text" class="chatting" id="message" />
            <input type="button" value="입력" class="chat_send" id="sendBtn" name="send" />
          </div>
        </div>
      </div>
    </div>

	  <input type="hidden" value="${path}${chat.other_profile_img}" id="other_prf_img" />
	  <input type="hidden" value="${chat.seller_id}" id="seller_id" />
	  <input type="hidden" value="${chat.other_nickname}" id="nickname" />
	  <input type="hidden" value="${chat.post_id}" id="post_id" />
	  <input type="hidden" value="${chat.room_id}" id="room_id" />
    <input type="hidden" value="<c:url value='/review/post-end'/>" id="dealEndUrl" />
    <input type="hidden" value="${path}/review" id="reviewUrl" />
    <input type="hidden" value="<c:url value='/chatting/list/' />" id="chat_content_url" />
    <input type="hidden" value="${id}" id="user_id" />
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${path}/resources/js/chatting/chatting_pop.js"></script>
  </body>
</html>
