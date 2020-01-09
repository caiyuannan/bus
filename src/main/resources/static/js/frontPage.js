

var divNode;
var x=0;
var y=0;
var flag=true;
var IntervalID;
var s;
var s1;
var s3;
var s4;
var flag1=true;
var flag2=true;
window.onload=function () {

	divNode=document.getElementById("mydiv");
	function func() {
		if (flag===true) {
			if (flag1===true) {  y++;}else{y--;}
			if (flag2===true) {  x++;}else{x--;}
			// x++;
			divNode.style.top=y+"px";
			divNode.style.left=x+"px";
			if (x===(innerWidth-100)){
				flag2=false;
				// s=Math.ceil(Math.random()*1300);
				// s3=Math.ceil(Math.random()*10);

			}
			// else if (x===s1||y===s1){
			//     flag = false;
			//     s=Math.ceil(Math.random()*1300);
			//     s3=Math.ceil(Math.random()*10);
			//
			// }
			if (y===(innerHeight-100)){
				s4=Math.ceil(Math.random()*10);

				if (s4<6) {
					flag1 = false;

				}else if (5<s4<11) {
					flag2 = false;
					flag1=false;
				}

			}
			if (y===0){
				flag2 = true;
				flag1 = true;
			}


		}




	};
	// var itl= setInterval("d()", delay)
	// divNode.onmouseover=function(){clearInterval(itl)}
	// divNode.onmouseout=function(){itl=setInterval("d()", delay)}
	// divNode.onmouseover=function(){clearInterval(IntervalID)};
	// divNode.onmouseout=function(){setInterval(IntervalID)}
	var interval = setInterval(func, 1); //启动,func不能使用括号
// 停止定时器
// 	clearInterval(interval );
//重新启动即可
// 	interval = setInterval(func, 1);
	divNode.onmouseover=function(){clearInterval(interval)};
	divNode.onmouseout=function(){interval = setInterval(func, 1);}
	divNode.visibility = "visible";

// 新闻公告栏
	// alert(2);
	var time = setInterval('timer(".apple")', 2000); //新闻列表滚动
	// var mit = setInterval('timer(".maquee")', 3000); //中奖名单-停顿滚动
	$('.apple ul').find('li').mousemove(function() {
		clearInterval(time);
	}).mouseout(function() {
		time = setInterval('timer(".apple")', 3000);
	});

}
// 新闻公告栏
function timer(opj) {
	// alert(1);
	$(opj).find('ul').animate({
		marginTop: "-45px"
	}, 500, function() {
		$(this).css({
			marginTop: "0px"
		}).find("li:first").appendTo(this);
	})
}
// $(function() {
//
// });
