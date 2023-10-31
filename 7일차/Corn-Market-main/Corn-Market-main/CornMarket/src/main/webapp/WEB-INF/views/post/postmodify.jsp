<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Corn-Market 수정</title>
    <c:set var="path" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" href="${path}/resources/css/post/postmodify.css" />
  </head>

  <body>
  	<jsp:include page="../base/header.jsp" />
    <div class="board-wrap">
      <form method="post" action="<c:url value='/post/${post.post_id}/modify'/>" enctype="multipart/form-data">
        <div class="board-img-wrap">
          <div class="board-container">
            <ul class="image-preview" id="preview"></ul>
          </div>
          <span class="btn-delete">삭제</span>
          <div class="board-button">
            <input
              type="file"
              id="chooseFile"
              class="real-upload"
              name="files"
              accept="image/*"
              multiple="multiple"
              style="display: none"
            />
            <label for="chooseFile" class="upload"> 사진올리기 </label>
          </div>
          <div class="fileContainer">
            <div class="fileInput">
              <p>FILE NAME :</p>
              <p id="fileName"></p>
            </div>
          </div>
        </div>
        <div class="board-title-wrap">
          <h2 class="board-title">제목</h2>
          <input
            class="board-title-input"
            name="title"
            id="registertitle"
            type="text"
            placeholder="제목을 입력해주세요"
            value="${post.title}"
          />
        </div>
        <div class="board-cate-wrap">
          <h2 class="board-cate">카테고리</h2>
          <select class="board-cate-select" name="category_id" id="registercate">
            <option value="select">선택</option>
            <option value="A1101">가구,인테리어</option>
            <option value="B1102">전자기기</option>
            <option value="C1103">의류</option>
            <option value="D1104">뷰티,미용</option>
            <option value="E1105">취미,게임,음반</option>
            <option value="F1106">반려동물용품</option>
          </select>
        </div>
        <div class="board-sellingprice-wrap">
          <h2 class="board-sellingprice">판매가격</h2>
          <input
            class="board-sellingprice-input"
            type="text"
            placeholder="가격을 입력해주세요"
            id="inputonchange"
            name="price"
            value="${post.price}"
          />
          <p class="board-sellingprice-won">원</p>
          <p class="board-check">나눔</p>
          <input type="checkbox" class="board-checkbox" id="boardcheckbox" name="boardcheckbox" value="1" />
        </div>
        <div class="board-contents-wrap">
          <textarea placeholder="내용을 입력해주세요" name="content" id="registercontent"></textarea>
        </div>
        <div class="board-btn-wrap">
          <input class="board-btn" type="submit" value="수정" />
        </div>
      </form>
    </div>
	<jsp:include page="../base/footer.jsp" />

    <input type="hidden" value="${post.content}" id="post_content" />
    <input type="hidden" value="${post.category_id}" id="post_category_id" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${path}/resources/js/post/postmodify.js"></script>
    <script type="text/javascript">
      //카테고리 select 선택되게 하기
      function selectedCategory() {
        $('#registercate').val($('#post_category_id').val()).prop('selected', true);
      }
      //내용 줄바꿈 변경
      function replaceContent() {
        var text = $('#post_content').val().replaceAll('<br>', '\n');
        $('#registercontent').html(text);
      }
      selectedCategory();
      replaceContent();
    </script>
  </body>
</html>
