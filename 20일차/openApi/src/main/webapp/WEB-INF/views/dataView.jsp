<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.acorn.open.uis.DataDto" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	a{
		text-decoration: none;
	}
	table, td{
		border: solid black 1px;
		text-align:center;
		border-collapse: collapse;
	}	
</style>
<body>
<table>
	

	<%
	ArrayList<DataDto> result= (ArrayList<DataDto>)request.getAttribute("result");
	boolean first=true;
	for(DataDto r:result){
		if(first){
			%>
			
			<tr>
	<% for(String s: r.getInformGrade().split(",")) {%>
		<th><%=s.split(":")[0] %></th>
		<%} %>
		<th>미세먼지 평균</th>
		<th>날짜</th>
	</tr>
			<% 
			first=false;
		}
	%>
	
	<tr>
		<% for(String s: r.getInformGrade().split(",")) {
			if(s.split(":")[1].trim().equals("좋음")){
				%>
				<td>😀</td>
				<% 
			}else if(s.split(":")[1].trim().equals("보통")){
				%>
				<td>🫥</td>
				<% 
			}else if(s.split(":")[1].trim().equals("나쁨")){
				%>
				<td>😒</td>
				<% 
			}
		%>
		
		
		<%} %>
		
	 <td><%=r.getInformCause() %></td>
	 <td><%=r.getDataTime() %></td>
	</tr>
	
	<%} %>
	</table>
</body>
</html>