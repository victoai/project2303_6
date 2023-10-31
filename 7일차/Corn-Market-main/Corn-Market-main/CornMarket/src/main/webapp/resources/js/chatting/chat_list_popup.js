function chatListPop() {
  //팝업창 화면 가운데로 띄우기
  let popupWidth = 630;
  let popupHeight = 691;
  let popupX = Math.ceil((window.screen.width - popupWidth) / 2);
  let popupY = Math.ceil((window.screen.height - popupHeight) / 2);
  var url = $('#urlBtn').val(); // <input type="hidden" id="urlBtn" value="${path}" />
  window.open(
    url + '/chatting/list',
    'chatting-list',
    'width=' + popupWidth + ',height=' + popupHeight + ',left=' + popupX + ', top=' + popupY
  );
}
