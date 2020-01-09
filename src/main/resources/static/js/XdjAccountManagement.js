function myaction(node)
{
	var value = $(node).val();
	var account=$("#staid");
	var sunt=$("#conid");
	var str = $("#staid").val();
	if(value === "搜索")
	{
		alert("搜索");
		str=1;
		account.val(str);

	}else if(value === "上一页")
	{
		alert("上一页");
		if(str>1){
			str-=1;
			account.val(str);
		}else{
			alert("当前已是最前一页");

		}
	}else{
		alert("下一页");
		if (str < sunt.val() ){
			str = parseInt(str);
			str+=1;
			account.val(str);
		}else{
			alert("当前已是最后一页");
		}
	}
	$("#serachfrom").submit();
}