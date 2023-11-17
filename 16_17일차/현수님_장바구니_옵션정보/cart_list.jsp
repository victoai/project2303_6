<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="path" value="<%=request.getContextPath()%>"></c:set>




<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
.cart-title {
	border-bottom: 1px #d9d9d9 solid;
}

.cart-store-wrap {
	display: flex;
}

.store-img {
	width: 100px;
	height: 100px;
}

.menu-title-wrap {
	display: flex;
}

.xbutton {
	width: 25px;
}

.menu-img {
	width: 200px;
}

.menu-detail-wrap{
display:flex;
}
</style>


</head>


<body>
	<jsp:include page="../base/header.jsp" />
	<section id="content">
		<div class="cart-title">장바구니</div>

		<div class="cart-store-wrap">
			<img class="store-img"
				src="${path}/storeImages/${storeInfo[0].storeImage}">
			<div>${storeInfo[0].storeName}</div>
		</div>

		<div class="menu-title-wrap">
			<div class="menu-title">${menuInfo[0].menuName}</div>
			<img class="xbutton" src="${path}/resources/icons/xbutton.png">
		</div>

		<div class="menu-detail-wrap">
			<img class="menu-img"
				src="${path}/menuImages/${menuInfo[0].menuImage}">
			<div class="menu-option">
				<c:forEach var="cartItem" items="${cartInfo.options}">
					<c:set var="splitValues" value="${fn:split(cartItem, '/')}" />
                    <p>Option Name: ${splitValues[1]} Option Price: ${splitValues[2]}</p>
				</c:forEach>
			</div>
		</div>




	</section>

	<jsp:include page="../base/footer.jsp" />

</body>
</html>