//닉네임 받아오기
let nickname = opener.document.getElementById('nickname').value;
$('#rv_nickname').text(nickname);
let room_id = opener.document.getElementById('room_id').value;
$('#room_id').val(room_id);

//폼 유효성 체크
function checkForm() {
  let url = $('#url').val();
  let frm = document.frm;
  if (frm.rate.value == '') {
    alert('별점을 선택해주세요.');
    return;
  } else {
    window.opener.name = 'chat';
    frm.method = 'POST';
    frm.action = url + '/review';
    frm.target = 'chat';
    frm.submit();
    self.close();
  }
}
//완료후 창닫기
function closePopup() {
  let url = $('#chatUrl').val();
  window.opener.location.href = url + room_id; //부모창 이동 url
  self.close();
}
