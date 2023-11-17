<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
function a() {
    return new Promise(function(resolve, reject) {
        alert("dfdfdf");
        let formData = new FormData(document.getElementById('myForm'));
        alert(formData + "dfdfdfdf");
        console.log(formData);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            url: "/form/ftest",
            data: formData,
            success: function(data) {
                alert("성공");
                console.log("success");
                resolve(data); // 성공 시 resolve 호출
            },
            error: function(error) {
                alert("에러 발생");
                console.error("에러", error);
                reject(error); // 에러 발생 시 reject 호출
            }
        });
    });
}

// 함수 호출 및 프로미스 처리
a().then(function(result) {
        // 성공적으로 처리된 경우
        console.log("결과:", result);
    })
    .catch(function(error) {
        // 에러가 발생한 경우
        console.error("에러 발생:", error);
    });


/*
 * 
 function a() {

		alert("dfdfdf");
		let formData = new FormData(document.getElementById('myForm'));
		alert( formData + "dfdfdfdf");
		console.log( formData);
		//alert( formData.username);
		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			url : "/form/ftest",
			data : formData,
			success : function(data) {
				alert("성공");
				console.log("success")
			}
			
		});
	
 
 */

 
</script>
</head>
<body>


	<form id="myForm">
		<label>파일 업로드:</label> <input type="file" name="file">
		<input type="text" name="username">
		<button type="button" onclick="a()">전송</button>
	</form>
</body>
</html>