$(document).ready(function() {
	// jQuery 코드: 각 링크 클릭 시 현재 URL의 파라미터 유지하고 category 파라미터 추가
	const currentUrl = window.location.href; // 현재 URL 가져오기

	const cateLink = addQueryParam(currentUrl, 'category', 'all');
	$('#allLink').attr('href', cateLink);

	const beefLink = addQueryParam(currentUrl, 'category', '1');
	$('#beefLink').attr('href', beefLink);

	const porkLink = addQueryParam(currentUrl, 'category', '2');
	$('#porkLink').attr('href', porkLink);

	const chickenLink = addQueryParam(currentUrl, 'category', '3');
	$('#chickenLink').attr('href', chickenLink);

	// jQuery 함수: 현재 URL에 파라미터 추가 및 수정
	function addQueryParam(url, paramName, paramValue) {
		const urlObj = new URL(url);
		if (paramValue === 'all') {
			urlObj.searchParams.delete(paramName);
		} else {
			urlObj.searchParams.set(paramName, paramValue);
		}
		urlObj.searchParams.delete('page'); // 페이지 파라미터를 항상 1로 설정
		return urlObj.toString();
	}
});
