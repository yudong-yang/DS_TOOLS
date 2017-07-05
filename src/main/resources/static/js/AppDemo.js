$(document).ready(function(){
	$("#geturl").click(function(){
		if($("#appname").val()==""){
			alert("应用名称不能为空")
		}else if($("#appcode").val()==""){
			alert("应用标示不能为空")
		} else if($("#appkey").val()==""){
			alert("应用appkey不能为空")
		} else if($("#appsecret").val()==""){
			alert("应用appsecret不能为空")
		} else {
		$.ajax({
		    cache: true,
		    type: "post",
		    url:"/appurl",
		    data:$('#myform').serialize(),// 你的formid
		    async: false,
		    error: function(request) {
		        alert("Connection error");
		    },
		    success: function(result) {
		    	  var URL = eval('(' + result + ')'); 
					 /* var person = result.parseJSON(); */
					$("#creditsurl").html(URL.creditsurl);
					$("#notifyurl").html(URL.notifyurl);
					$("#redirecturl").html(URL.redirecturl);
					$("#virtualurl").html(URL.virtualurl);
		    	
		    	/*var strs= new Array(); //定义一数组 
		    	strs=result.split("=="); //字符分割 
		    	if(strs.length==3){
		        	$("#creditsurl").html(strs[0]);
		        	$("#notifyurl").html(strs[1]);
		        	$("#redirecturl").html(strs[2]);}else{
		        	$("#msg").html(result);}	
		        		*/
		        	}
		    
		});
		}
	});		

	
	$("#redirect").click(function(){
		var url = $("#redirecturl").html();
		window.open(url);
	});


});