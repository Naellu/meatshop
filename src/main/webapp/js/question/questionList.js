$(document).ready(function() {
    // 이전에 선택된 값 가져오기
    var selectedValue = localStorage.getItem("selectedOption");
    if (selectedValue) {
        $("#searchSelect").val(selectedValue);
    }

    // 선택한 값이 변경되었을 때 localStorage에 저장
    $("#searchSelect").change(function() {
        var selectedOption = $(this).val();
        localStorage.setItem("selectedOption", selectedOption);
    });
});