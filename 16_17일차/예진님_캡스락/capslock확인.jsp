<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>





</head>
<body>


<input type="text"  id="a">  


<script>
 


function checkCapsLock() {
    var capsLockOn = event.getModifierState && event.getModifierState('CapsLock');

    if (capsLockOn) {
       alert('Caps Lock is on');
    } else {
      alert('Caps Lock is off');
    }
}
document.getElementById("a").addEventListener('keydown',     checkCapsLock ) ;
 

 

</script>
</body>
</html>