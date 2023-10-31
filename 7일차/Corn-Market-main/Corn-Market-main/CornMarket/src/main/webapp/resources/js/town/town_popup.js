/**
 *
 */
//완료후 창닫기
function townCertify() {
  window.opener.location.href = url_profile; //부모창 이동 url
  self.close();
}

$('document').ready(loadEvent);

//동네찾기 버튼
function loadEvent() {
  geoFindMe();
  $('#search_btn').click(clickEvent);
}
function clickEvent() {
  //alert('dd');
  geoFindMe();
  $('#town_location').show();
}

//데이터 전송
$('#confirm').click(clickPost);
function clickPost() {
  //alert('dd');
  ajax();
}

function ajax() {
  //alert('ajax');
  const product = document.querySelector('li');
  let town_name = $('#town_info').text();
  let latitude = product.dataset.latitude; //데이터 속성 값 가져오기
  let longitude = product.dataset.longitude; //데이터 속성 값 가져오기
  let town = {
    town_name: town_name,
    latitude: latitude,
    longitude: longitude,
  };
  //console.log(town);
  //console.log('이름:' + town_name + ' 위도:' + latitude + ' 경도:' + longitude);

  $.ajax({
    type: 'POST',
    url: url_town,
    headers: { 'content-type': 'application/json' },
    data: JSON.stringify(town),
    success: function () {
      //console.log('데이터 전송 성공');
      townCertify();
    },
    error: function () {
      alert('오류 발생');
    },
  });
}

//Geolocation API
function geoFindMe() {
  if (!navigator.geolocation) {
    alert('사용중인 브라우저에서는 위치 정보를 이용할 수 없습니다.');
  } else {
    navigator.geolocation.getCurrentPosition((position) => {
      const latitude = position.coords.latitude;
      const longitude = position.coords.longitude;
      $('#data_div').html('<li data-latitude=' + latitude + ' data-longitude=' + longitude + '>');
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
          //center: new kakao.maps.LatLng(37.55309928363025, 126.91986870947545), // 테스트용
          level: 3, // 지도의 확대 레벨
        };

      // 지도를 생성합니다
      var map = new kakao.maps.Map(mapContainer, mapOption);

      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
        infowindow = new kakao.maps.InfoWindow({ zindex: 1 }); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

      // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
      searchAddrFromCoords(map.getCenter(), displayCenterInfo);

      // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
        searchDetailAddrFromCoords(mouseEvent.latLng, function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            var detailAddr = !!result[0].road_address
              ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>'
              : '';
            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';

            var content = '<div class="bAddr">' + '<span class="title">법정동 주소정보</span>' + detailAddr + '</div>';

            // 마커를 클릭한 위치에 표시합니다
            marker.setPosition(mouseEvent.latLng);
            marker.setMap(map);

            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
            infowindow.setContent(content);
            infowindow.open(map, marker);
          }
        });
      });

      // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(map, 'idle', function () {
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);
      });

      function searchAddrFromCoords(coords, callback) {
        // 좌표로 행정동 주소 정보를 요청합니다
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
      }

      function searchDetailAddrFromCoords(coords, callback) {
        // 좌표로 법정동 상세 주소 정보를 요청합니다
        geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
      }

      // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
      function displayCenterInfo(result, status) {
        if (status === kakao.maps.services.Status.OK) {
          var infoDiv = document.getElementById('town_info');

          for (var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            if (result[i].region_type === 'H') {
              infoDiv.innerHTML = result[i].address_name;
              break;
            }
          }
        }
      }
    });
  }
}
