$(function () {
	var AArrnote = document.getElementsByTagName("a");
	for (var i = 0; i < AArrnote.length; i++) {
		AArrnote[i].setAttribute("onclick", "changgeURL(this)");

	}
});


function changgeURL(node) {
	var changeU;
	changeU = $("#myiframe");
	changeU.attr("src",node.title);
	// changeU.src = node.title;       //新iframe的链接地址
}