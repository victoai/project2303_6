//프로필 수정 팝업
function update_myinfo() {
  //팝업창 화면 가운데로 띄우기
  let popupWidth = 680;
  let popupHeight = 700;
  let popupX = Math.ceil((window.screen.width - popupWidth) / 2);
  let popupY = Math.ceil((window.screen.height - popupHeight) / 2);
  var url = $('#urlBtn').val();
  window.open(
    url + '/profile/update',
    'profile-update',
    'width=' + popupWidth + ',height=' + popupHeight + ',left=' + popupX + ', top=' + popupY
  );
}
//프로필 이미지 수정 팝업
function change_img() {
  //팝업창 화면 가운데로 띄우기
  let popupWidth = 450;
  let popupHeight = 300;
  let popupX = Math.ceil((window.screen.width - popupWidth) / 2);
  let popupY = Math.ceil((window.screen.height - popupHeight) / 2);
  var url = $('#urlBtn').val();
  window.open(
    url + '/profile/update/image',
    'profile-image-update',
    'width=' + popupWidth + ',height=' + popupHeight + ',left=' + popupX + ', top=' + popupY
  );
}

