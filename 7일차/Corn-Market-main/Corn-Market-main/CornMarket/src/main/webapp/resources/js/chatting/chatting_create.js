//채팅방 생성 버튼 form submit
// 채팅방이 없는 경우 생성 후 이동
// 채팅방이 있으면 바로 이동
function createNewChatRoom() {
  $('#chattingsub_btn').click(() => {
    checkChatRoom();
  });
}
createNewChatRoom();
//<input type="button" value="채팅방 생성" id="new_room_btn" /> <br />

//판매글id와 구매자id(세션)로 채팅방 확인
function checkChatRoom() {
  let url = $('#chat_url').val();
  let post_id = $('#post_id').val();
  $.ajax({
    type: 'POST',
    url: url + 'check',
    data: post_id,
    contentType: 'text/plain; charset=utf-8',
    success: function (data) {
      //조회 결과 없으면 0 있으면 방id 반환
      //alert(data);
      let check = data;
      if (check == 0) {
        //채팅방 생성
        let chatAction = $('#chat_url').val();
        openChatList('');
        chat_room_frm.action = chatAction;
        chat_room_frm.method = 'POST';
        chat_room_frm.target = 'chatting';
        chat_room_frm.submit();
      } else if (check != 0) {
        //존재하는 채팅방으로 이동
        //<input type="hidden" value="<c:url value='/chatting/list/' />" id="chat_url" />
        let url = $('#chat_url').val() + '/' + check;
        openChatList(url);
      }
    },
    error: function (data) {
      alert('error');
    },
  });
}

//채팅창 팝업으로 열기
function openChatList(url) {
  let targetUrl = url;
  let popupWidth = 630;
  let popupHeight = 691;
  let popupX = Math.ceil((window.screen.width - popupWidth) / 2);
  let popupY = Math.ceil((window.screen.height - popupHeight) / 2);
  let features = 'width=' + popupWidth + ',height=' + popupHeight + ',left=' + popupX + ', top=' + popupY;
  window.open(targetUrl, 'chatting', features);
}
