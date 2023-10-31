<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>프로필 수정</title>
    <link rel="stylesheet" href="${path}/resources/css/profile/popup_update.css" />
  </head>
  <body>
    <div class="container">
      <div class="popup-wrap" id="popup">
        <div class="popup">
          <div class="popup-head">
            <span class="head-title">프로필 수정</span>
          </div>
          <form name="frm" id="frm">
            <div class="popup-body">
              <div class="body-content">
                <div class="body-titlebox">
                  <div class="update_first">
                    <img src="${path}${profile.profile_img}" />
                    <input type="button" value="변경" onclick="change_img()" />
                  </div>
                </div>
                <div class="body-contentbox">
                  <div class="update_second">
                    <div class="up_main">
                      <div class="up_title">닉네임</div>
                      <input
                        type="text"
                        placeholder="${profile.nickname}"
                        class="update_nickname"
                        name="nickname"
                        id="update_nickname"
                      />
                      <input
                        type="button"
                        value="중복확인"
                        class="double_check"
                        id="check_btn"
                        onclick="checkNickname()"
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="popup-foot">
              <span class="pop-btn confirm" id="confirm" onclick="checkForm()">저장</span>
              <span class="pop-btn close" id="close" onclick="self.close()">창 닫기</span>
            </div>
          </form>
        </div>
      </div>
    </div>
    <input type="hidden" id="urlBtn" value="${path}" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${path}/resources/js/profile/update_myinfo.js"></script>
    <script type="text/javascript">
      //닉네임 중복확인
      function checkNickname() {
        let nickname = $('#update_nickname').val();
        if (nickname == '') {
            alert('닉네임을 입력해주세요.');
            $('#update_nickname').focus();
            return;
        }
        //console.log(nickname);
        $.ajax({
          type: 'GET', //중복확인
          url: '<c:url value="/profile/update/check?nickname=" />' + nickname,
          success: function (num) {
            //console.log(num);
            //alert('ddd');
            if (num == 0) alert('닉네임을 사용하실 수 있습니다.');
            else if (num == 1) {
              alert('이미 존재하는 닉네임 입니다.');
              $('#nm').val('');
            }
          },
          error: function () {
            alert('error');
          },
        }); //$.ajax()
      }

      //닉네임 폼 체크
      function checkForm() {
        let nickname = $('#update_nickname').val();
        if (nickname == '') {
          alert('닉네임을 입력해주세요.');
          $('#update_nickname').focus();
        } else {
          profilePopupSubmit();
        }
      }

      //닉네임 수정후 제출
      function profilePopupSubmit() {
        window.opener.name = 'updateProfile'; // 부모창의 이름 설정
        frm.action = '<c:url value='/profile/update/nickname'/>'; //부모창에 호출될 url
        frm.method = 'POST';
        frm.target = 'updateProfile'; // 타켓을 부모창으로 설정
        frm.submit();
        self.close();
      }
    </script>
  </body>
</html>
