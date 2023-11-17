// 웹소켓 연결
var webSocket;
function connect() {
  webSocket = new WebSocket('ws://localhost:8090/market/chat');
  //webSocket = new WebSocket('ws://ip주소:8090/market/chat');

  webSocket.onopen = onOpen;
  webSocket.onmessage = onMessage;
  webSocket.onclose = onClose;
}
// 웹소켓 연결 끊기
function disconnect() {
  webSocket.close();
}
// 메시지 전송
function sendMsg() {
  var msg = $('#message').val();
  send('send_msg', msg);
  //ajaxChatContent(msg); //DB에 저장
  $('#message').val(''); //입력창 초기화
}
// 웹소켓에 데이터 전송
function send(type, msg) {
  let room = $('#room_id').val();
  let id = $('#user_id').val();
  //var msg = $('#message').val(); //메시지 입력 input
  let chattingContent = {
    type: type, // 'enter':웹소켓 연결시, 'send_msg':메시지 전송시, 'out':웹소켓 끊어질시
    room_id: room,
    sender_id: id,
    chat_content: msg,
  };
  webSocket.send(JSON.stringify(chattingContent)); //웹소켓 서버로 JSON 전송
}

// 웹소켓 서버에 연결되었을 때 호출되는 이벤트
function onOpen(evt) {
  send('enter', '');
  //alert('연결되었습니다');
}
// 웹소켓 서버에서 메시지를 받았을 때 호출되는 이벤트
function onMessage(evt) {
  let id = $('#user_id').val();
  var data = JSON.parse(evt.data); //JSON -> 객체
  //날짜 표시
  appendNowDate();
  if (data.sender_id == id) {
    //본인 메시지
    appendSendMessage(data.chat_content);
  } else {
    //타인 메시지
    appendRecvMessage(data.chat_content);
  }
}
// 웹소켓 서버와 연결이 끊어졌을 때 호출되는 이벤트
function onClose(evt) {
  send('out', '');
  //alert('연결을 끊었습니다');
}

/*   HTML 추가 메소드   */
// 본인 메시지 채팅창에 추가
function appendSendMessage(msg) {
  let time = showTime();
  $('#chatMessageArea').append(
    '<div class="my_profile"> <div class="my_time">' + time + '</div> <div class="my_say">' + msg + '</div> </div>'
  );
  scrollDown();
}
// 타인 메시지 채팅창에 추가
function appendRecvMessage(msg) {
  let time = showTime();
  let img = $('#other_prf_img').val();
  $('#chatMessageArea').append(
    '<div class="other_profile"><img class="other_img" src="' +
      img +
      '" /><div class="other_say">' +
      msg +
      '</div><div class="other_time">' +
      time +
      '</div></div>'
  );
  scrollDown();
}
//현재날짜 채팅창에 추가
function appendNowDate() {
  let today = showDate(); //오늘 날짜
  let lastDateArr = $('.chat_date');
  let index = lastDateArr.length - 1;
  let lastDate;
  if (lastDateArr.length) lastDate = lastDateArr[index].innerText;
  //console.log(lastDate);
  if (lastDate != showDate() || !lastDate) {
    $('#chatMessageArea').append(`<div class="chat_date">${today}</div>`);
    scrollDown();
  }
}
//현재시간 표시
function showTime() {
  let today = new Date();
  let hours = ('0' + today.getHours()).slice(-2);
  let minutes = ('0' + today.getMinutes()).slice(-2);
  let time = hours + ':' + minutes;
  return time;
}
//현재날짜 표시
function showDate() {
  let today = new Date();
  let day = today.getFullYear() + '년 ' + (today.getMonth() + 1) + '월 ' + today.getDate() + '일';
  return day;
}
// 스크롤 맨밑으로
function scrollDown() {
  var chatAreaHeight = $('#chatArea').height(); //채팅창 틀
  var maxScroll = $('#chatMessageArea').height() - chatAreaHeight;
  $('#chatArea').scrollTop(maxScroll); //숫자가 높을수록 스크롤 제일 아래
}

/*   이벤트 관련 메소드   */

// 메시지 전송 - 입력창 Enter키
function enterKey() {
  $('#message').keypress(function (event) {
    var keycode = event.keyCode;
    if (keycode == '13') {
      sendMsg(); //메시지 전송
    }
    event.stopPropagation(); // 상위로 이벤트 전파 막음
  });
}
// 메시지 전송 - 전송버튼 클릭
function clickSendBtn() {
  $('#sendBtn').click(function () {
    sendMsg(); //메시지 전송
  });
}
// 나가기버튼 클릭 (테스트용)
function clickOutBtn() {
  $('#exitBtn').click(function () {
    disconnect(); //웹소켓 연결 끊기
  });
}

//페이지 로드 시
$(document).ready(() => {
  connect(); //페이지 로드시 채팅방 접속
  enterKey();
  clickSendBtn();
  scrollDown();
  $('#revBtn').click(openReview);
  $('#deal_end').click(dealEndBtn);
});

/*
   => 핸들러에서 처리
//ajax - 보내는 메시지 DB에 전송
function ajaxChatContent(msg) {
  let url = $('#chat_content_url').val();
  let room = $('#room_id').val();
  let id = $('#user_id').val();
  //var msg = $('#message').val(); //메시지 입력 input
  let chattingContent = {
    room_id: room,
    sender_id: id,
    chat_content: msg,
  };
  $.ajax({
    type: 'POST',
    url: url + room,
    headers: { 'content-type': 'application/json' },
    data: JSON.stringify(chattingContent),
    success: function () {
      //alert('채팅 내용 전송');
    },
    error: function () {
      alert('error');
    },
  });
}
*/

//거래 완료 버튼
function dealEndBtn() {
  //alert('거래완료');
  //판매글id로 판매글 상태 거래완료로 변경
  let endUrl = $('#dealEndUrl').val();
  let post_id = $('#post_id').val();
  let endMsg =
    '판매자가 거래를 완료했습니다.<br>' +
    '거래후기를 남겨주세요.<br>' +
    '<input type="button" value="거래후기 작성하러 가기" class="goto_review other_review" id="revBtn">';
  $.ajax({
    type: 'POST',
    url: endUrl,
    dataType: 'text',
    data: post_id,
    contentType: 'text/plain; charset=utf-8',
    success: function (data) {
      //alert('성공');
      let status = data;
      //console.log(status);
      if (status == '거래완료') {
        $('#deal_end').attr('disabled', 'true');
        $('#deal_end').css('cursor', 'default');
      } else if (status == '판매중') {
        //채팅창에 거래완료 메시지 추가
        send('send_msg', endMsg);
        //ajaxChatContent(endMsg); //DB에 저장
        $('#deal_end').attr('disabled', 'true');
        $('#deal_end').css('cursor', 'default');
      }
    },
    error: function (data) {
      alert('error');
    },
  });
}

//거래 후기 페이지 revBtn
//거래후기창 팝업으로 열기
function openReview() {
  if ($('#seller_id').val() == $('#user_id').val()) {
    alert('구매자만 후기를 작성할 수 있습니다.');
    return;
  }
  let url = $('#reviewUrl').val();

  let popupWidth = 650;
  let popupHeight = 750;
  let popupX = Math.ceil((window.screen.width - popupWidth) / 2);
  let popupY = Math.ceil((window.screen.height - popupHeight) / 2);
  window.name = 'chatWindow';
  window.open(
    url,
    'RegReview',
    'width=' + popupWidth + ',height=' + popupHeight + ',left=' + popupX + ', top=' + popupY
  );
}
