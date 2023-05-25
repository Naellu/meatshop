$(document).ready(function() {
	$("#list1").show();
	$("#list2").hide();
	$("#list3").hide();
	$("#list4").hide();
	$("#list5").hide();
	$("#list6").hide();
})

$("#product").click(function() {
	$("#list1").show();
	$("#list2").hide();
	$("#list3").hide();
	$("#list4").hide();
	$("#list5").hide();
	$("#list6").hide();
});

$("#order").click(function() {
	$("#list1").hide();
	$("#list2").show();
	$("#list3").hide();
	$("#list4").hide();
	$("#list5").hide();
	$("#list6").hide();
});

$("#deliver").click(function() {
	$("#list1").hide();
	$("#list2").hide();
	$("#list3").show();
	$("#list4").hide();
	$("#list5").hide();
	$("#list6").hide();
});

$("#cancel").click(function() {
	$("#list1").hide();
	$("#list2").hide();
	$("#list3").hide();
	$("#list4").show();
	$("#list5").hide();
	$("#list6").hide();
});

$("#profile").click(function() {
	$("#list1").hide();
	$("#list2").hide();
	$("#list3").hide();
	$("#list4").hide();
	$("#list5").show();
	$("#list6").hide();
});

$("#service").click(function() {
	$("#list1").hide();
	$("#list2").hide();
	$("#list3").hide();
	$("#list4").hide();
	$("#list5").hide();
	$("#list6").show();
});
