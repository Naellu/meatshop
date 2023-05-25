$("#product").click(function() {
	var category = $(this).data("category");
	faqByCategory(category);
});

function faqByCategory(category) {
	const data = {category}
	
	$.ajax("/faq/list",{
		method: "get",
		contentType: "application/json",
		data: JSON.stringify(category),
		success: function(response) {
			updateFaqList(response);
		}
	});
}

function updateFaqList(list) {
	var faqList = $("#list");
	faqList.empty();
	
	for (var i = 0; i < list.length; i++) {
		var faq = faqList[i];
		var faqItem = $("<div>").text(faq.question);
		faqList.append(faq);
	}
}