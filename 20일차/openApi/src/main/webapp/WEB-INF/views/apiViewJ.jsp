<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	table {
		text-align: center;
		border: 1px solid black;
	}
	td{
		border: 1px solid black;
	}
	.wrap{
		border: 3px solid #408fff;
		border-radius: 10px;
		margin: 30px;
		padding: 20px;
		position: relative;
  		z-index: 1;
		
	}
	.wrap::after{
		content: "";
		background-image: url("https://thumb.photo-ac.com/34/342b6a2885dc90e2b6787a4ed9c3375b_t.jpeg");
		background-position: center;
		background-size: cover;
		opacity: 0.3;
	
		position: absolute;
		top: 0px;
        left: 0px;
        right: 0px;
        bottom: 0px;
		z-index: -1;
	}
	
	body{
		 background-color: #f0f8ff
	}
	img{
		width: 200px;
		height: 230px; 
		margin: 7px;
		
	}
</style>
<title>Insert title here</title>
</head>
<body>

	<c:forEach var="api" items="${apiList}">
		<c:if test="${api.imageUrl1 != 'null' }">
		
			<div class="wrap">
				<h4>${api.informData}</h4>
				<p>${api.informGrade}</p>
				
				<img src="${api.imageUrl1}">
				<img src="${api.imageUrl2}">
				<img src="${api.imageUrl3}">
				<img src="${api.imageUrl4}">
				<img src="${api.imageUrl5}">
				<img src="${api.imageUrl6}">

				<p>${api.informCause}</p>
		
			</div>
		</c:if>
	</c:forEach>

	

</body>
</html>