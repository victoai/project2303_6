<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.imageBox{
 display: flex;
}

img{
	width:100px;
	height:100px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	function search() {
		alert("dkdkdkd");
		 console.log("console  ok ????");
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/airList",
			success : function(data) {
				alert("dfdfdfd" + data);
				console.log(data);
				alert(data[0].informCause);

				alert(data.length);
				let result = toHTML(data);
				$("#result").html(result);
				alert("성공");
			},
			error : function(err) {
				console.log(err);
				alert("error");
			}
		});
	}

	function toHTML(d) {
		console.log(d);
		let str = "<ul>";
		for (let i = 0; i < d.length; i++) {
			let item = d[i];
			let resultGrade = item.informGrade.split(',');
			let resultinform = item.informCause.replace('○', '').trim();
			
			str += '<div id="imageBox">' +
		       '<img src="' + item.image1 + '">' +
	            '<img src="' + item.image2 + '">' +
	            '<img src="' + item.image3 + '">' +
		    '</div><br>';
			str += "<li>" + resultinform + "<br>" + item.dataTime + "<br>";
			for(let grade of resultGrade){
				str += grade + "<br>";
			}
			str += "</li>";
		}
		str += "</ul>";
		alert(str);

		return str;

	}
</script>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 	<h1>대기 오염 지수</h1>
	<button onclick="search()">air</button>
	<div id="result"></div>

</body>
</html>