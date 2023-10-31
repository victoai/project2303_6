<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>프로필 사진 변경</title>
    <link rel="stylesheet" href="${path}/resources/css/profile/popup.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  </head>
  <body>
    <div class="container">
      <div class="popup-wrap" id="popup">
        <div class="popup">
          <div class="popup-head">
            <span class="head-title">프로필 사진 변경하기</span>
          </div>
          <form
            name="fileForm"
            action="<c:url value='/profile/update/image' />"
            method="post"
            enctype="multipart/form-data"
          >
            <div class="popup-body">
              <div class="body-content">
                <div class="body-titlebox">
                  <input type="text" class="img_loader" id="file_name" readonly />
                </div>
                <div class="body-contentbox">
                  <input
                    type="file"
                    name="file"
                    id="input_file"
                    accept="image/*"
                    style="display: none"
                    onchange="getFileName(this)"
                  />
                  <input type="button" class="serach_btn" value="불러오기" id="file_btn" />
                </div>
              </div>
            </div>
            <div class="popup-foot">
              <span class="pop-btn confirm" id="confirm" onclick="submitBtn()">저장</span>
              <span class="pop-btn close" id="close" onclick="self.close()">창 닫기</span>
            </div>
          </form>
        </div>
      </div>
    </div>
    <script>
      //불러오기 버튼
      function clickFileBtn() {
        $('#file_btn').click(() => {
          $('#input_file').click();
        });
      }
      $(document).ready(clickFileBtn);
      //파일명 가져오기
      function getFileName(file) {
        console.log(file.value);
        console.log(file.files[0].name);
        $('#file_name').attr('value', file.files[0].name);
        console.log('파일명:' + $('#file_name').val());
      }
      //저장 버튼
      function submitBtn() {
        window.opener.name = 'updateImage'; // 부모창의 이름 설정
        fileForm.action = '<c:url value="/profile/update/image"/>'; //부모창에 호출될 url
        fileForm.method = 'POST';
        fileForm.target = 'updateImage'; // 타켓을 부모창으로 설정
        fileForm.submit();
        self.close();
      }
    </script>
  </body>
</html>
