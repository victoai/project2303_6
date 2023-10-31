//채팅방 삭제
function removeChatRoom(self) {
  //console.log(self);
  let url = $('#delete_url').val();
  let room_id = $(self).attr('id');
  $.ajax({
    type: 'POST',
    url: url,
    data: room_id,
    contentType: 'text/plain; charset=utf-8',
    success: function () {
      window.location.reload();
    },
    error: function () {
      alert('error');
    },
  });
}
