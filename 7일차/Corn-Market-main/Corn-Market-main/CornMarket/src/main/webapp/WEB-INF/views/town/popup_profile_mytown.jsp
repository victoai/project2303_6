<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Corn-Market 동네인증</title>
    <link rel="stylesheet" href="${path}/resources/css/town/popup_mytown.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  </head>
  <body>
    <div class="container">
      <div class="popup-wrap" id="popup">
        <div class="popup">
          <div class="popup-head">
            <span class="head-title">동네인증하기</span>
          </div>
          <div class="popup-body">
            <div class="body-content">
              <div class="body-titlebox" id="map" style="position:relative;overflow:hidden;"></div>
              <div class="body-contentbox">
                <h3 class="town_location" id="town_location">현재위치가 <span class="town_info" id="town_info"></span class="town">에 있습니다.</h3>
                <input type="button" class="search_btn" id="search_btn" value="동네인증" />
              </div>
            </div>
          </div>
          <div class="popup-foot">
            <span class="pop-btn confirm" id="confirm" onclick="">인증완료</span>
            <span class="pop-btn close" id="close" onclick="self.close()">창 닫기</span>
          </div>
        </div>
      </div>
    </div>
    <div id="data_div" style="display: none;"></div>

    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=93ce7269a8f7fe1402b282d3008199ed&libraries=services"></script>
    <script src="${path}/resources/js/town/town_popup.js"></script>
    <script>
      let url_profile = '<c:url value="/profile" />';
      let url_town = '<c:url value="/town" />';
    </script>
  </body>
</html>
