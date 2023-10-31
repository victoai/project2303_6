<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>프로필 - ${profile.nickname}</title>
    <link rel="stylesheet" href="${path}/resources/css/profile/profile.css" />
  </head>
  <body>
  <jsp:include page="../base/header.jsp"/>
    <section class="profile-section">
      <div class="profile_wrap">
        <div class="profile_first">
          <div class="profile_img-wrap">
            <div class="prf-img" style="background-image: url('${path}${profile.profile_img}')"></div>
            <!-- 본인 프로필 -->
            <c:if test="${sessionScope.id == profile.user_id}">
              <input type="button" class="profile_update_btn info-btn" value="프로필 수정" onclick="update_myinfo()" />
            </c:if>
          </div>

          <div class="profile_contents">
            <div class="nickname-wrap">
              <div class="profile_nickname">${profile.nickname}</div>
              <!-- 본인 프로필 -->
              <c:if test="${sessionScope.id == profile.user_id}">
                <input
                  type="button"
                  value="채팅하기"
                  class="chatting-btn info-btn"
                  onclick="location.href='${path}/chatting/list'"
                />
              </c:if>
            </div>

            <ul class="profile-info-wrap">
              <li class="manner_degree profile-info">
                <div class="flexf">
                  <div class="degree-info flexf-items">매너온도</div>
                  <div class="degree flexf-items">${profile.temperature}℃</div>
                </div>
              </li>
              <li class="town-confirm profile-info">
                <div class="flexf">
                  <div class="town-info flexf-items">${profile.town_name} 인증</div>
                  <!-- 본인 프로필 -->
                  <c:if test="${sessionScope.id == profile.user_id}">
                    <input
                      type="button"
                      class="town-confirm-btn info-btn flexf-items"
                      value="동네 인증하기"
                      onclick="mytown()"
                    />
                  </c:if>
                </div>
              </li>
              <li class="join-date profile-info">
                <div class="flexf">${profile.join_date} 가입</div>
              </li>
            </ul>
          </div>
        </div>

        <div class="profile_second">
          <div class="profile_nav">
            <c:if test="${sessionScope.id == profile.user_id}">
              <div class="profile_status onsale"><a href="<c:url value='/profile' />">판매중</a></div>
              <div class="profile_status offsale"><a href="<c:url value='/profile/offsale' />">판매완료</a></div>
              <div class="profile_status review"><a href="<c:url value='/profile/review' />">거래후기</a></div>
            </c:if>
            <c:if test="${sessionScope.id != profile.user_id}">
              <div class="profile_status onsale"><a href="<c:url value='/profile/' />${profile.user_id}">판매중</a></div>
              <div class="profile_status offsale"><a href="<c:url value='/profile/' />${profile.user_id}/offsale">판매완료</a></div>
              <div class="profile_status review"><a href="<c:url value='/profile/' />${profile.user_id}/review">거래후기</a></div>
            </c:if>
          </div>
          <!-- 리뷰 리스트 -->
          <div class="profile_review_wrap card-wrap">
            <c:forEach var="rv" items="${profile.reviewList}">
              <div class="review_part">
                <div class="review_nickname">${rv.nickname}</div>
                <div class="real_review">${rv.review}</div>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </section>
<jsp:include page="../base/footer.jsp"/>
    <!-- profile.user_id -->
    <input type="hidden" id="id_info" value="${profile.user_id}" />
    <input type="hidden" id="sessionId" value="${sessionScope.id}" />
    <input type="hidden" id="urlBtn" value="${path}" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${path}/resources/js/town/town.js"></script>
    <script src="${path}/resources/js/profile/update_myinfo.js"></script>
    <script>
      var id_info = $('#id_info').val();
      var session_id = $('#sessionId').val();
      var url = 'http://localhost:8090' + $('#urlBtn').val();
      var link = document.location.href;
      if (session_id == id_info) {
        if (link == url + '/profile') {
          $('.onsale').css('background-color', 'rgb(241, 196, 15)');
          $('.onsale a').css('color', 'white');
        }
        if (link == url + '/profile/offsale') {
          $('.offsale').css('background-color', 'rgb(241, 196, 15)');
          $('.offsale a').css('color', 'white');
        }
        if (link == url + '/profile/review') {
          $('.review').css('background-color', 'rgb(241, 196, 15)');
          $('.review a').css('color', 'white');
        }
      }
      if (session_id != id_info) {
        if (link == url + '/profile/' + id_info) {
          $('.onsale').css('background-color', 'rgb(241, 196, 15)');
          $('.onsale a').css('color', 'white');
        }
        if (link == url + '/profile/' + id_info + '/offsale') {
          $('.offsale').css('background-color', 'rgb(241, 196, 15)');
          $('.offsale a').css('color', 'white');
        }
        if (link == url + '/profile/' + id_info + '/review') {
          $('.review').css('background-color', 'rgb(241, 196, 15)');
          $('.review a').css('color', 'white');
        }
      }
    </script>
  </body>
</html>
