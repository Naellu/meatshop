const mapContainer = document.getElementById('map'); // 지도를 표시할 div 

const x = 37.566208784133494;
const y = 126.90195514818544;
var mapTypeControl = new kakao.maps.MapTypeControl();
mapOption = {
	// 위치할 위도 경도
	center: new kakao.maps.LatLng(x, y), // 지도의 중심좌표
	// 지도의 확대 레벨
	level: 3
};

const map = new kakao.maps.Map(mapContainer, mapOption);

// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);


// 마커 표시 위치 생성
const markerPosition = new kakao.maps.LatLng(x, y);

// 마커 객체 생성
const marker = new kakao.maps.Marker({
	position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

function setCenter() {
	// 이동할 위도 경도 위치
	var moveLatLon = new kakao.maps.LatLng(x, y);

	// 지도 중심을 이동 시킵니다
	map.setCenter(moveLatLon);
}

$("#centerBtn").click(function() {
	setCenter();
});
