<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>여러개 마커 표시하기</title>
</head>
<body>
<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c2c80cc59eb12ce43927bf1f9cfe0196"></script>
<script>
var mapContainer = document.getElementById('map');
var mapOption = {
    center: new kakao.maps.LatLng(35.190165, 126.808852),
    level: 2
};

var map = new kakao.maps.Map(mapContainer, mapOption);

 

let positions = ${item2}  ;

/* 
var positions = [
    {
        title: 'ddfdf',
        latlng: new kakao.maps.LatLng(35.190165, 126.808852)
    },
    {
        title: '77777',
        latlng: new kakao.maps.LatLng(35.139558, 126.793159)
    }
];
*/


 
var imageSrc = "https://png.pngtree.com/png-clipart/20190516/original/pngtree-vector-shop-icon-png-image_3762863.jpg";
var imageSize = new kakao.maps.Size(35, 35);
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

for (var i = 0; i < positions.length; i++) {
	  let position = positions[i];
	  
	  console.log( position);
	  
	  let   latlng  =  new  kakao.maps.LatLng( position.la , position.lg);
    var marker = new kakao.maps.Marker({
        map: map,
        position: latlng,
        title: positions[i].title,
        image: markerImage
    });
}
 


 
</script>
</body>
</html>
