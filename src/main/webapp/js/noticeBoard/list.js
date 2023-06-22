// 가장 긴 제목의 길이 계산
let longestTitleLength = 0;
const titles = document.querySelectorAll(".card-title");
titles.forEach((title) => {
	const titleLength = title.textContent.trim().length;
	if (titleLength > longestTitleLength) {
		longestTitleLength = titleLength;
	}
});

// 가장 긴 제목의 길이에 따라 그리드의 크기 설정
const gridWidth = longestTitleLength * 10 + 50; // 예시로 글자당 10px, 여백 50px로 계산
const gridItems = document.querySelectorAll(".col-xl-3");
gridItems.forEach((item) => {
	item.style.width = gridWidth + "px";
});

