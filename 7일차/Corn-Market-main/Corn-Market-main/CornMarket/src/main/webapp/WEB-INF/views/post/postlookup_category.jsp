<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Corn-Market 게시판</title>
    <c:set var="path" value="${pageContext.request.contextPath}" />

    <link rel="stylesheet" href="${path}/resources/css/post/postlookup.css" />
  </head>

  <body>
    <jsp:include page="../base/header.jsp" />
    <!-- 상품 리스트 -->
    <section class="board-lookup-wrap">
      <div>
        <h1 class="board-lookup-title" id="onchange" name="onchange">사기</h1>
        <div class="board-select-wrap">
          <p class="sort-way" id="sortway" name="sortway">${list[0].category_name}</p>
          <select class="sort-way-select1" id="sortchoice1" name="sortchoice1" onchange="categoryChange(this)">
            <option value="recent">최신글</option>
            <option value="category">카테고리별</option>
            <option value="town">지역별</option>
          </select>
          <select class="sort-way-select2" name="sortchoice2" id="sortchoice2" onchange="showValue(this)"></select>
        </div>
      </div>

      <div class="board-card-wrap" id="category_list" style="display: none"></div>
      <div class="board-card-wrap" id="town_list" style="display: none"></div>
      <div class="board-card-wrap" id="recent_list">
        <c:forEach items="${list}" var="post">
          <article class="board-card-top">
            <a class="board-card-link" href="<c:url value='/post/${post.post_id}'/>">
              <img class="board-card-photo" src="${path}${post.post_img}" />
              <div class="board-card-desc">
                <div class="board-card-title">${post.title}</div>
                <div class="board-card-price">${post.price}원</div>
                <div class="board-card-region">서울시 ${post.town_name}</div>
              </div>
            </a>
          </article>
        </c:forEach>
      </div>
      
        <div class="pageInfo_wrap">
          <div class="pageInfo_area">
            <ul id="pageInfo" class="pageInfo">
              <!-- 이전페이지 버튼 -->
              <c:if test="${pageMaker.prev}">
                <li class="pageInfo_btn previous">
                  <a href="${path}/post/category?id=${list[0].category_id}&PageNum=${pageMaker.startPage-1}">
                    <img
                    class="post_btn"
                    src="${path }/resources/images/post/prev.png"
                    width="28px"
                    height="28px"
                	/>
				  </a>
                </li>
              </c:if>

              <!-- 각 번호 페이지 버튼 -->
              <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                <li class="pageInfo_btn"><a href="${path}/post/category?id=${list[0].category_id}&PageNum=${num}" id="clickPage${num}">${num}</a></li>
              </c:forEach>

              <!-- 다음페이지 버튼 -->
              <c:if test="${pageMaker.next}">
                <li class="pageInfo_btn next"><a href="${path}/post/category?id=${list[0].category_id}&PageNum=${pageMaker.endPage + 1}">
					<img
                    class="post_btn"
                    src="${path }/resources/images/post/next.png"
                    width="28px"
                    height="28px"
                	/>
				</a></li>
              </c:if>
            </ul>
          </div>
        </div>

      <form id="moveForm" action="get">
        <input
          type="hidden"
          name="pageNum"
          id="loadPage"
          value="${pageMaker.cri.pageNum}"
        />
        <input type="hidden" name="amount" value="${pageMaker.cri.amount}" />
      </form>
    </section>
    <input type="hidden" id="path_url" value="${path}" />
    <input type="hidden" id="post_url" value="<c:url value='/post/'/>" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${path}/resources/js/post/postlookup.js"></script>
    <jsp:include page="../base/footer.jsp" />
  </body>
</html>
