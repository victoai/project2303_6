// data
function categoryChange(e) {
  const text = e.options[e.selectedIndex].text;
  $('#sortway').html(text + '로 조회하기');

  let select1 = $('#sortchoice1 option:selected').val();
  let postUrl = $('#post_url').val();
  if (select1 == 'recent') {
    window.location.href = postUrl;
  }
  var target = document.getElementById('sortchoice2');

  target.options.length = 0;

  var category = [
    { '': '선택하세요' },
    { A1101: '가구,인테리어' },
    { B1102: '전자기기' },
    { C1103: '의류' },
    { D1104: '뷰티,미용' },
    { E1105: '취미,게임,음반' },
    { F1106: '반려동물용품' },
  ];
  var town = [
    { '': '선택하세요' },
    { 11010: '종로구' },
    { 11020: '중구' },
    { 11030: '용산구' },
    { 11040: '성동구' },
    { 11050: '광진구' },
    { 11060: '동대문구' },
    { 11070: '중랑구' },
    { 11080: '성북구' },
    { 11090: '강북구' },
    { 11100: '도봉구' },
    { 11110: '노원구' },
    { 11120: '은평구' },
    { 11130: '서대문구' },
    { 11140: '마포구' },
    { 11150: '양천구' },
    { 11160: '강서구' },
    { 11170: '구로구' },
    { 11180: '금천구' },
    { 11190: '영등포구' },
    { 11200: '동작구' },
    { 11210: '관악구' },
    { 11220: '서초구' },
    { 11230: '강남구' },
    { 11240: '송파구' },
    { 11250: '강동구' },
  ];

  if (e.value == 'category') var d = category;
  else if (e.value == 'town') var d = town;
  console.log('선택 개수:' + d.length);

  for (let i = 0; i < d.length; i++) {
    for (a in d[i]) {
      var opt = document.createElement('option');
      opt.value = a;
      opt.innerHTML = d[i][a];
      target.appendChild(opt);
    }
  }
}

function categoryList() {
  let select2 = $('#sortchoice2 option:selected').val();
  let postUrl = $('#post_url').val();
  let pathUrl = $('#path_url').val();
  $.ajax({
    type: 'POST',
    url: postUrl + '/category/' + select2, //카테고리id url로 전송
    dataType: 'json',
    success: function (data) {
      //alert('카테고리id 전송 성공');
      let list = data;
      //console.log(list[0]);
      $('#recent_list').hide(); //기존 리스트 숨기기
      $('#town_list').hide();
      $('#category_list').empty(); //카테고리 목록 초기화
      list.forEach((post) => {
        var categoryContent =
          '<article class="board-card-top">' +
          '<a class="board-card-link" href="' +
          postUrl +
          '/' +
          post.post_id +
          '">' +
          '<img class="board-card-photo" src="' +
          pathUrl +
          post.post_img +
          '" />' +
          '<div class="board-card-desc">' +
          '<div class="board-card-title">' +
          post.title +
          '</div>' +
          '<div class="board-card-price">' +
          post.price +
          '</div>' +
          '<div class="board-card-region">서울시 ' +
          post.town_name +
          '</div>' +
          '</div> </a> </article>';
        $('#category_list').append(categoryContent); //판매글 하나씩 추가
      });
      $('#category_list').show();
    },
    error: function () {
      alert('올바르게 선택해주세요.');
    },
  });
}

function townList() {
  let select2 = $('#sortchoice2 option:selected').val();
  let postUrl = $('#post_url').val();
  let pathUrl = $('#path_url').val();
  $.ajax({
    type: 'GET',
    url: postUrl + '/town/' + select2, //townid url로 전송
    dataType: 'json',
    success: function (data) {
      //alert('카테고리id 전송 성공');
      let list = data;
      //console.log(list[0]);
      $('#recent_list').hide(); //기존 리스트 숨기기
      $('#category_list').hide();
      $('#town_list').empty(); //카테고리 목록 초기화
      list.forEach((post) => {
        var townContent =
          '<article class="board-card-top">' +
          '<a class="board-card-link" href="' +
          postUrl +
          '/' +
          post.post_id +
          '">' +
          '<img class="board-card-photo" src="' +
          pathUrl +
          post.post_img +
          '" />' +
          '<div class="board-card-desc">' +
          '<div class="board-card-title">' +
          post.title +
          '</div>' +
          '<div class="board-card-price">' +
          post.price +
          '</div>' +
          '<div class="board-card-region">서울시 ' +
          post.town_name +
          '</div>' +
          '</div> </a> </article>';
        $('#town_list').append(townContent); //판매글 하나씩 추가
      });
      $('#town_list').show();
    },
    error: function () {
      alert('올바르게 선택해주세요.');
    },
  });
}

function showValue() {
  let select1 = $('#sortchoice1 option:selected').val();
  //console.log('대분류:' + select1);
  if (select1 == 'category') categoryList(); //카테고리별 데이터 가져오기 ajax
  if (select1 == 'town') townList(); //지역별 데이터 가져오기 ajax
}

$(document).ready(function () {
  let loadpage = $('#loadPage').val();
  //console.log(loadpage);
  $('#clickPage' + loadpage).css({
    color: '#ffffff',
    'background-color': 'rgb(241, 196, 15)',
    'border-radius': '2px',
  });
});
