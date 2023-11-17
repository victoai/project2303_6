<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Caps Lock Check</title>
</head>
<body>

<input type="text" id="myInput" placeholder="아이디입력">

<script>
function checkCapsLockOnce(event) {
    var capsLockOn = event.getModifierState && event.getModifierState('CapsLock');
    var inputElement = document.getElementById('myInput');

    if (capsLockOn) {
        console.log('Caps Lock is on');
        // Caps Lock가 켜져 있을 때 원하는 동작을 수행할 수 있습니다.
        // 예: 사용자에게 알림을 표시하거나 스타일을 변경하는 등의 작업
        inputElement.style.border = '2px solid red';

        // 더 이상 이벤트를 감지하지 않도록 리스너 제거
        inputElement.removeEventListener('keydown', checkCapsLockOnce);
    }
}

// input 요소에 이벤트 리스너 등록
document.getElementById('myInput').addEventListener('keydown', checkCapsLockOnce);
</script>

</body>
</html>
