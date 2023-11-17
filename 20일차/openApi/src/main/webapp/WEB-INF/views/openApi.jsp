<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

table, tr, td {
border: 1px solid black;
border-collapse: collapse;
}

img{
witdh : 50px;
height : 120px;
}

</style>
</head>
<body>
<table>
<c:forEach var="item" items="${list}">
<c:if test="${item.imageUrl1 !='null' && item.imageUrl2 !='null' && item.imageUrl3 !='null' && item.imageUrl4 !='null' && item.imageUrl5 !='null' && item.imageUrl6 !='null'}">
<tr>
<td>${item.informData}</td>
<td>${item.informGrade}
<div>
<img src="${item.imageUrl1}">
<img src="${item.imageUrl2}">
<img src="${item.imageUrl3}">
<img src="${item.imageUrl4}">
<img src="${item.imageUrl5}">
<img src="${item.imageUrl6}">
</div>
</td>
<td>${item.informCause}</td>
</tr>
</c:if>
</c:forEach>
</table>

</body>
</html>