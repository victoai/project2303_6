<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Get User Geolocation</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c56a5ac8208747818bdaee7eb60e05ea&libraries=services"
    ></script>

    <style>
      /* 모달 스타일 */
      .modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.4);
      }

      .modal-content {
        background-color: #fefefe;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        padding: 20px;
        border: 1px solid #888;
        width: 600px;
        height: 600px;
        text-align: center;
      }

      .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
      }

      .close:hover,
      .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <!-- 모달 요소 -->
    <div id="myModal" class="modal">
      <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <div id="modal-content"></div>
      </div>
    </div>

    <button id="find-me">Show my location</button><br />
    <p id="status"></p>
    <a id="map-link" target="_blank"></a>
    <div id="map" style="width: 500px; height: 500px; margin-left: 50px"></div>
    <script>
      function success(position) {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;
        const status = document.querySelector("#status");
        const mapLink = document.querySelector("#map-link");

        status.textContent =
          "현재 위치의 위도: " + latitude + " °, 경도: " + longitude + " °";

        mapLink.href =
          "https://www.openstreetmap.org/#map=18/" + latitude + "/" + longitude;
        mapLink.textContent =
          "Latitude: " + latitude + " °, Longitude: " + longitude + " °";

        var mapContainer = document.getElementById("map"),
          mapOption = {
            center: new kakao.maps.LatLng(latitude, longitude),
            level: 3,
          };

        var map = new kakao.maps.Map(mapContainer, mapOption);

        var markerPosition = new kakao.maps.LatLng(latitude, longitude);

        var marker = new kakao.maps.Marker({
          position: markerPosition,
        });

        marker.setMap(map);
        var geocoder = new kakao.maps.services.Geocoder();

        var infowindow = new kakao.maps.InfoWindow({ zindex: 1 });

        kakao.maps.event.addListener(map, "click", function (mouseEvent) {
          searchDetailAddrFromCoords(
            mouseEvent.latLng,
            function (result, status) {
              if (status === kakao.maps.services.Status.OK) {
                var detailAddr = !!result[0].road_address
                  ? "<div>도로명주소 : " +
                    result[0].road_address.address_name +
                    "</div>"
                  : "";
                detailAddr +=
                  "<div>지번 주소 : " +
                  result[0].address.address_name +
                  "</div>";

                var content =
                  '<div class="bAddr">' +
                  '<span class="title">현재 주소정보</span>' +
                  detailAddr +
                  "</div>";

                marker.setPosition(mouseEvent.latLng);
                marker.setMap(map);

                infowindow.setContent(content);
                infowindow.open(map, marker);
              }
            }
          );
        });

        kakao.maps.event.addListener(map, "click", function (mouseEvent) {
          const newLatitude = mouseEvent.latLng.getLat();
          const newLongitude = mouseEvent.latLng.getLng();

          const newLocation = checkLocation(newLatitude, newLongitude);

          if (newLocation !== "해당 지역이 아닙니다.") {
            const newLocationElement = document.createElement("div");
            newLocationElement.textContent = "인증되었습니다. 현재위치:"+newLocation+". 클릭한 위치의 위도는 "+ newLatitude + "이고, 경도는 "+ newLongitude + "입니다.";
            const modalContent = document.getElementById("modal-content");
            modalContent.appendChild(newLocationElement);
          } else {
            const newLocationElement = document.createElement("div");
            newLocationElement.textContent = newLocation;
            // 또는 newLocationElement.innerHTML = newLocation;
            const modalContent = document.getElementById("modal-content");
            modalContent.appendChild(newLocationElement);
          }
        });

        kakao.maps.event.addListener(map, "idle", function () {
          searchAddrFromCoords(map.getCenter(), displayCenterInfo);
        });

        function searchAddrFromCoords(coords, callback) {
          geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
        }

        function searchDetailAddrFromCoords(coords, callback) {
          geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
        }

        function displayCenterInfo(result, status) {
          if (status === kakao.maps.services.Status.OK) {
            var infoDiv = document.getElementById("centerAddr");

            for (var i = 0; i < result.length; i++) {
              if (result[i].region_type === "H") {
                infoDiv.innerHTML = result[i].address_name;
                break;
              }
            }
          }
        }
      }

      function error() {
        const status = document.querySelector("#status");
        status.textContent = "확인되지 않은 위치입니다.";
      }

      function geoFindMe() {
        const status = document.querySelector("#status");
        const mapLink = document.querySelector("#map-link");

        mapLink.href = "";
        mapLink.textContent = "";

        if (!navigator.geolocation) {
          status.textContent = "";
        } else {
          status.textContent = "";
          navigator.geolocation.getCurrentPosition(success, error);
        }
      }

      function getDistanceFromLatLonInKm(lat1, lon1, lat2, lon2) {
        const R = 6371;
        const dLat = deg2rad(lat2 - lat1);
        const dLon = deg2rad(lon2 - lon1);
        const a =
          Math.sin(dLat / 2) * Math.sin(dLat / 2) +
          Math.cos(deg2rad(lat1)) *
            Math.cos(deg2rad(lat2)) *
            Math.sin(dLon / 2) *
            Math.sin(dLon / 2);
        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        const d = R * c;
        return d;
      }

      function deg2rad(deg) {
        return deg * (Math.PI / 180);
      }

      function checkLocation(latitude, longitude) {
        let minDistance = Infinity;
        let closestLocations = [];
        const locationsWithinRange = [];
        const locations = [
          // 서울시
          {
            locCode: "서울시",
            detailLoc: "강남구",
            latitude: 37.5172,
            longitude: 127.0473,
          },
          {
            locCode: "서울시",
            detailLoc: "강서구",
            latitude: 37.5509,
            longitude: 126.8497,
          },
          {
            locCode: "서울시",
            detailLoc: "강동구",
            latitude: 37.5618,
            longitude: 127.0371,
          },
          {
            locCode: "서울시",
            detailLoc: "강북구",
            latitude: 37.646995,
            longitude: 127.014716,
          },
          {
            locCode: "서울시",
            detailLoc: "관악구",
            latitude: 37.4782,
            longitude: 126.9517,
          },
          {
            locCode: "서울시",
            detailLoc: "광진구",
            latitude: 37.5485,
            longitude: 127.0859,
          },
          {
            locCode: "서울시",
            detailLoc: "구로구",
            latitude: 37.4952,
            longitude: 126.8581,
          },
          {
            locCode: "서울시",
            detailLoc: "금천구",
            latitude: 37.4573,
            longitude: 126.9019,
          },
          {
            locCode: "서울시",
            detailLoc: "노원구",
            latitude: 37.6542,
            longitude: 127.0568,
          },
          {
            locCode: "서울시",
            detailLoc: "도봉구",
            latitude: 37.6688,
            longitude: 127.0471,
          },
          {
            locCode: "서울시",
            detailLoc: "동대문구",
            latitude: 37.5812,
            longitude: 127.0547,
          },
          {
            locCode: "서울시",
            detailLoc: "동작구",
            latitude: 37.4964,
            longitude: 126.9443,
          },
          {
            locCode: "서울시",
            detailLoc: "마포구",
            latitude: 37.5665,
            longitude: 126.9018,
          },
          {
            locCode: "서울시",
            detailLoc: "서대문구",
            latitude: 37.5791,
            longitude: 126.9368,
          },
          {
            locCode: "서울시",
            detailLoc: "서초구",
            latitude: 37.4837,
            longitude: 127.0324,
          },
          {
            locCode: "서울시",
            detailLoc: "성동구",
            latitude: 37.5635,
            longitude: 127.0365,
          },
          {
            locCode: "서울시",
            detailLoc: "성북구",
            latitude: 37.6066,
            longitude: 127.0237,
          },
          {
            locCode: "서울시",
            detailLoc: "송파구",
            latitude: 37.5145,
            longitude: 127.1058,
          },
          {
            locCode: "서울시",
            detailLoc: "양천구",
            latitude: 37.5169,
            longitude: 126.866,
          },
          {
            locCode: "서울시",
            detailLoc: "영등포구",
            latitude: 37.5255,
            longitude: 126.8974,
          },
          {
            locCode: "서울시",
            detailLoc: "용산구",
            latitude: 37.5326,
            longitude: 126.9905,
          },
          {
            locCode: "서울시",
            detailLoc: "은평구",
            latitude: 37.6171,
            longitude: 126.928,
          },
          {
            locCode: "서울시",
            detailLoc: "종로구",
            latitude: 37.5704,
            longitude: 126.9922,
          },
          {
            locCode: "서울시",
            detailLoc: "중구",
            latitude: 37.5642,
            longitude: 126.9975,
          },
          {
            locCode: "서울시",
            detailLoc: "중랑구",
            latitude: 37.6066,
            longitude: 127.0927,
          },

          // 제주도
          {
            locCode: "제주도",
            detailLoc: "제주시",
            latitude: 33.4996,
            longitude: 126.5312,
          },
          {
            locCode: "제주도",
            detailLoc: "서귀포시",
            latitude: 33.2542,
            longitude: 126.5603,
          },

          // 경기도
          {
            locCode: "경기도",
            detailLoc: "수원",
            latitude: 37.2636,
            longitude: 127.0286,
          },
          {
            locCode: "경기도",
            detailLoc: "성남",
            latitude: 37.4386,
            longitude: 127.1378,
          },
          {
            locCode: "경기도",
            detailLoc: "용인",
            latitude: 37.241086,
            longitude: 127.177555,
          },
          {
            locCode: "경기도",
            detailLoc: "부천",
            latitude: 37.5032,
            longitude: 126.7667,
          },
          {
            locCode: "경기도",
            detailLoc: "안산",
            latitude: 37.3217,
            longitude: 126.8309,
          },
          {
            locCode: "경기도",
            detailLoc: "고양",
            latitude: 37.658,
            longitude: 126.8326,
          },
          {
            locCode: "경기도",
            detailLoc: "의정부",
            latitude: 37.7437,
            longitude: 127.0413,
          },
          {
            locCode: "경기도",
            detailLoc: "광명",
            latitude: 37.4794,
            longitude: 126.8646,
          },

          // 충청도
          {
            locCode: "충청도",
            detailLoc: "대전",
            latitude: 36.3504,
            longitude: 127.3845,
          },
          {
            locCode: "충청도",
            detailLoc: "천안",
            latitude: 36.8151,
            longitude: 127.1135,
          },
          {
            locCode: "충청도",
            detailLoc: "아산",
            latitude: 36.7926,
            longitude: 127.0018,
          },
          {
            locCode: "충청도",
            detailLoc: "공주",
            latitude: 36.5633,
            longitude: 127.2564,
          },
          {
            locCode: "충청도",
            detailLoc: "논산",
            latitude: 36.1977,
            longitude: 127.1002,
          },
          {
            locCode: "충청도",
            detailLoc: "보령",
            latitude: 36.3494,
            longitude: 126.6032,
          },
          {
            locCode: "충청도",
            detailLoc: "서산",
            latitude: 36.7763,
            longitude: 126.4509,
          },

          // 경상도
          {
            locCode: "경상도",
            detailLoc: "부산",
            latitude: 35.1796,
            longitude: 129.0756,
          },
          {
            locCode: "경상도",
            detailLoc: "대구",
            latitude: 35.8714,
            longitude: 128.6014,
          },
          {
            locCode: "경상도",
            detailLoc: "울산",
            latitude: 35.5384,
            longitude: 129.3114,
          },
          {
            locCode: "경상도",
            detailLoc: "창원",
            latitude: 35.2322,
            longitude: 128.6811,
          },
          {
            locCode: "경상도",
            detailLoc: "포항",
            latitude: 36.0199,
            longitude: 129.341,
          },
          {
            locCode: "경상도",
            detailLoc: "진주",
            latitude: 35.1795,
            longitude: 128.1076,
          },
          {
            locCode: "경상도",
            detailLoc: "김해",
            latitude: 35.2282,
            longitude: 128.8812,
          },

          // 전라도
          {
            locCode: "전라도",
            detailLoc: "광주",
            latitude: 35.1595,
            longitude: 126.8526,
          },
          {
            locCode: "전라도",
            detailLoc: "전주",
            latitude: 35.8468,
            longitude: 127.1297,
          },
          {
            locCode: "전라도",
            detailLoc: "목포",
            latitude: 34.8124,
            longitude: 126.3922,
          },
          {
            locCode: "전라도",
            detailLoc: "순천",
            latitude: 34.9477,
            longitude: 127.5047,
          },
          {
            locCode: "전라도",
            detailLoc: "여수",
            latitude: 34.7604,
            longitude: 127.6622,
          },
          {
            locCode: "전라도",
            detailLoc: "나주",
            latitude: 35.0128,
            longitude: 126.7193,
          },
          {
            locCode: "전라도",
            detailLoc: "군산",
            latitude: 35.9756,
            longitude: 126.945,
          },

          // 강원도
          {
            locCode: "강원도",
            detailLoc: "춘천",
            latitude: 37.8856,
            longitude: 127.7342,
          },
          {
            locCode: "강원도",
            detailLoc: "원주",
            latitude: 37.3447,
            longitude: 127.9209,
          },
          {
            locCode: "강원도",
            detailLoc: "강릉",
            latitude: 37.7519,
            longitude: 128.876,
          },
          {
            locCode: "강원도",
            detailLoc: "속초",
            latitude: 38.207,
            longitude: 128.591,
          },
          {
            locCode: "강원도",
            detailLoc: "동해",
            latitude: 37.7499,
            longitude: 129.3328,
          },
          {
            locCode: "강원도",
            detailLoc: "태백",
            latitude: 37.1665,
            longitude: 128.9886,
          },
          {
            locCode: "강원도",
            detailLoc: "삼척",
            latitude: 37.4449,
            longitude: 129.165,
          },
        ];

        const desiredRange = 3; // 변경하고자 하는 거리 범위 (단위: km)

        for (let location of locations) {
          const distance = getDistanceFromLatLonInKm(
            latitude,
            longitude,
            location.latitude,
            location.longitude
          );

          if (distance <= desiredRange) {
            ////    0   <= 1
            locationsWithinRange.push(location); //   ok
          }

          if (distance < minDistance) {
            //
            minDistance = distance; //  처음
            closestLocations = [location];
          } else if (distance === minDistance) {
            closestLocations.push(location);
          }
        }
        
        console.log( locationsWithinRange );

        if (locationsWithinRange.length > 0) {
          if (locationsWithinRange.length === 1) {
            return locationsWithinRange[0].locCode  - locationsWithinRange[0].detailLoc ;
          } else {
            let userChoice = prompt(            		 
              "여러 지역이" + desiredRange +"km 이내에 있습니다. 아래에서 선택해주세요:\n " +
              
              locationsWithinRange .map(   (loc, index) =>    (index + 1) +" . "+ loc.locCode  -  loc.detailLoc     )
              
              
              
            );

            userChoice = parseInt(userChoice);
            if (userChoice && userChoice <= locationsWithinRange.length) {
              return  locationsWithinRange[userChoice - 1].locCode  - 
                locationsWithinRange[userChoice - 1].detailLoc  ;
            }
          }
        }

        return "해당 지역이 아닙니다.";
      }

      document.querySelector("#find-me").addEventListener("click", geoFindMe);
    </script>

    <script>
      // 모달 열기
      function openModal() {
        document.getElementById("myModal").style.display = "block";
      }

      // 모달 닫기
      function closeModal() {
        document.getElementById("myModal").style.display = "none";
      }

      // "Show my location" 버튼 클릭 시 모달 열기
      document.getElementById("find-me").addEventListener("click", function () {
        const statusContent = document.getElementById("status").outerHTML;
        const mapLinkContent = document.getElementById("map-link").outerHTML;
        const mapContent = document.getElementById("map").outerHTML;

        const content = statusContent + mapLinkContent + mapContent;
        document.getElementById("modal-content").innerHTML = content;
        openModal();
        geoFindMe();
      });
    </script>
  </body>
</html>
