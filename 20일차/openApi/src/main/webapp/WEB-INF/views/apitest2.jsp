<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
div{
border: 2px solid black;
border-radius: 20px;
padding: 20px;
}
img{
height: 222px;
}
.a{
height: 222px;
}
.b{
width: 1200px;
margin: 0 auto;
margin-bottom: 20px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div >
<c:forEach var="item" items="${list}" >
<c:if test="${item.imageUrl1 !='null'}">
<div class="b">
<h2>${item.informData} </h2>
<p>
${item.informCode} ※통보코드(PM10, PM25, O3) <br>
</p>
<p>
${item.informCause} <br>
${item.informOverall} <br>

</p>
<div class="a">
<img alt="" src="${item.imageUrl1}">
<img alt="" src="${item.imageUrl2}">
<img alt="" src="${item.imageUrl3}">
<img alt="" src="${item.imageUrl4}">
<img alt="" src="${item.imageUrl5}">
<img alt="" src="${item.imageUrl6}">
</div>
<p>
${item.informGrade} <br>
발표 시간 : ${item.dataTime}
</p>
</div>
</c:if>
</c:forEach>
</div>
</body>
</html>