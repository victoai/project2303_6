<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Result</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.min.js" charset="utf-8"></script>
<meta name="viewport" content="width=device-width,initial-scale=1">
</head>

<body>
	<div class="kakao">								<!-- client_id에 자신이 발급받은 Rest api 주소를, redirect_uri에 자신이 발급받은 redirect_uri 주소를 입력-->
	    <a href="https://kauth.kakao.com/oauth/authorize?client_id=e70ac5a45fa1cba7935fa44d0c23e6d6&redirect_uri=http://localhost:8080/baemin/kakaoLogin&response_type=code">
	        카카오로 간편 로그인
	    </a>
	</div>
</body>

</html>