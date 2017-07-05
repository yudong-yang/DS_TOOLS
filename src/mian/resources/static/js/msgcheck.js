/**
 * 让按钮30s不能点击
 * @param time
 */

function timer(time) {
    var btn = $("#getcode");
    btn.attr("disabled", true);  //按钮禁止点击
    btn.val(time <= 0 ? "发送动态密码" : ("" + (time) + "秒后可发送"));
    var hander = setInterval(function() {
        if (time <= 0) {
            clearInterval(hander); //清除倒计时
            btn.val("发送动态密码");
            btn.attr("disabled", false);
            return false;
        }else {
            btn.val("" + (time--) + "秒后可发送");
        }
    }, 1000);
}

/**
 * 校验手机号格式
 * @param phone
 * @returns {Boolean}
 */
function checkPhone(phone){ 
    if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){  
        return false; 
    } else{
    	return true;
    	}
}
$(document).ready(function() {
				$("#getcode").click(function() {
							var telephone = $("#telephone").val();
							
							if(telephone==null||telephone==""){alert("手机号不能为空");}
							else if(checkPhone(telephone)){
							$.get("/Msg/sendMessage?telephone=" + telephone,
									function(data, status) {
										if (status == "success") {
											alert("发送成功,验证码为"+data);
											timer(30);
										}
									});
							}else{alert("手机号码有误，请重填");}	
						});
				$("#test").click(function() {
					var code = $("#code").val();
					
					if(code==null||code==""){alert("验证码不能为空");}else{
					$.get("/Msg/checkcode?code=" + code, function(data, status) {
						if (data == "success") {
							alert("验证码输入正确");
							window.location.href="/Msg/checksuccess";
						} else {
							alert("验证输码入有误");
						}
					});
					}
				});
			});