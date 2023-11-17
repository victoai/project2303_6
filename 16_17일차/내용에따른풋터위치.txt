<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            margin: 0;
            padding: 0;
        }
        #content {
            margin-bottom: 50px; /* 여분의 여백으로 풋터가 바닥에 유지되도록 설정 */
            height: 2000px;
            
        }
        #footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #f1f1f1;
            padding: 15px;
        }
    </style>
</head>
<body>

<div id="content">
    <!-- 내용이 들어갈 부분 -->
    <p>  section !!!! </p>
</div>

<div id="footer">
    <!-- 풋터 내용 -->
    <p>Your footer content goes here.</p>
</div>

<script>
    // 문서의 높이를 조절하는 함수
    function adjustDocumentHeight() {
        var contentHeight = document.getElementById('content').offsetHeight;
        var windowHeight = window.innerHeight;
        var footerHeight = document.getElementById('footer').offsetHeight;

        if (contentHeight + footerHeight < windowHeight) {
            // 내용이 적을 때
            document.getElementById('footer').style.position = 'fixed';
        } else {
            // 내용이 많을 때
            document.getElementById('footer').style.position = 'static';
        }
    }

    // 페이지 로드 시와 창 크기 변경 시에도 호출
    window.onload = adjustDocumentHeight;
    window.onresize = adjustDocumentHeight;
</script>

</body>
</html>
