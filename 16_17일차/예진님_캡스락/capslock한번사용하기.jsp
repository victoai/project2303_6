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

<input type="text" id="myInput" placeholder="���̵��Է�">

<script>
function checkCapsLockOnce(event) {
    var capsLockOn = event.getModifierState && event.getModifierState('CapsLock');
    var inputElement = document.getElementById('myInput');

    if (capsLockOn) {
        console.log('Caps Lock is on');
        // Caps Lock�� ���� ���� �� ���ϴ� ������ ������ �� �ֽ��ϴ�.
        // ��: ����ڿ��� �˸��� ǥ���ϰų� ��Ÿ���� �����ϴ� ���� �۾�
        inputElement.style.border = '2px solid red';

        // �� �̻� �̺�Ʈ�� �������� �ʵ��� ������ ����
        inputElement.removeEventListener('keydown', checkCapsLockOnce);
    }
}

// input ��ҿ� �̺�Ʈ ������ ���
document.getElementById('myInput').addEventListener('keydown', checkCapsLockOnce);
</script>

</body>
</html>
