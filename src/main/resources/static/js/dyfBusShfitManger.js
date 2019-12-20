
$(function () {
	var AArrnote = document.getElementsByTagName("a");
	for (var i = 0; i < AArrnote.length; i++) {
		AArrnote[i].setAttribute("onclick", "changgeIframe(this)");

	}
});





function changgeIframe(node) {
	var changeU;
	changeU = $("#myiframe");
	if (node.title!=null&&node.title.length>0){
		changeU.attr("src","busShfit"+"?"+"parm="+node.title);
	}
	// changeU.src = node.title;       //新iframe的链接地址
}