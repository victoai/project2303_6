<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/resources/css/main/section.css"/>
</head>
<body>
<!-- 인기있는 키워드 -->
        <section class="home-keywords-content">
            <h3 class="home-keywords-title">
                <a class="text-bold-title" href="">중고거래 인기검색어</a>
            </h3>
            <ul class="keyword-top-list">
                <li class="keyword-item">
                    <a class="keyword-link" rel="nosublink" href="">자전거</a>
                </li>
                <li class="keyword-item">
                    <a class="keyword-link" rel="nosublink" href="">의자</a>
                </li>
                <li class="keyword-item">
                    <a class="keyword-link" rel="nosublink" href="">아이폰</a>
                </li>
                <li class="keyword-item">
                    <a class="keyword-link" rel="nosublink" href="">냉장고</a>
                </li>
                <li class="keyword-item">
                    <a class="keyword-link" rel="nosublink" href="">노트북</a>
                </li>
                <li class="keyword-item">
                    <a class="keyword-link" rel="nosublink" href="">패딩</a>
                </li>
                <li class="keyword-item">
                    <a class="keyword-link" rel="nosublink" href="">아이패드</a>
                </li>
                <li class="keyword-item">
                    <a class="keyword-link" rel="nosublink" href="">모니터</a>
                </li>
                <li class="keyword-item">
                    <a class="keyword-link" rel="nosublink" href="">스타벅스</a>
                </li>
                <li class="keyword-item">
                    <a class="keyword-link" rel="nosublink" href="">책상</a>
                </li>
            </ul>
        </section>
</body>
</html>