<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Corn-Market 거래후기</title>
    <link rel="stylesheet" href="${path}/resources/css/review/popup_review.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${path}/resources/js/profile/update_myinfo.js"></script>
  </head>
  <body>
    <div class="container">
      <div class="popup-wrap" id="popup">
        <div class="popup">
          <div class="popup-head">
            <span class="head-title">거래 후기 보내기</span>
          </div>
          
          <form name="frm" id="myform" method="post" action="<c:url value='/review'/>">
            <input type="hidden" name="room_id" id="room_id">

            <div class="popup-body">
              <div class="body-content">
                <div class="body-titlebox">
                	<div class="titlebox1">

                		<span id="rv_nickname"></span>님과 거래가 어떠셨나요?

	           		</div>
           			<div class="titlebox2">
           				별점은 상대방이 알 수 없어요
           			</div>
           			<fieldset>

				        <input type="radio" name="rate" value="5" id="rate1"><label for="rate1">⭐</label>
				        <input type="radio" name="rate" value="4" id="rate2"><label for="rate2">⭐</label>
				        <input type="radio" name="rate" value="3" id="rate3"><label for="rate3">⭐</label>
				        <input type="radio" name="rate" value="2" id="rate4"><label for="rate4">⭐</label>
				        <input type="radio" name="rate" value="1" id="rate5"><label for="rate5">⭐</label>

					</fieldset>	
                </div>
                <div class="body-contentbox">
                	<p>거래후기</p>

                	<textarea class="reviewbox" cols="60" rows="15" name="review"></textarea>

                </div>
            </div>
           <div class="popup-foot">
              <span class="pop-btn confirm" id="confirm" onclick="checkForm()">저장</span>
              <span class="pop-btn close" id="close" onclick="self.close()">창 닫기</span>
            </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <input type="hidden" value="<c:url value='/chatting/list/'/>" id="chatUrl" />
    <input type="hidden" value="${path}" id="url" />
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${path}/resources/js/review/review.js"></script>

  </body>
</html>
