<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<!-- js, css include(S)-->
<%@ include file="/WEB-INF/include/include.jsp"%>
<!-- js, css include(E)-->

<script language="javascript">
jQuery(document).ready(function(){ 
		
	$('#btnLogin').click(function(){
		var param = new Object();
		param.userId = $('#userId').val();
		param.userPw = $('#userPw').val();
		
		$.ajax({
			url: 'loginCheck.do',
			type: 'post',
			data : param,
			async : false,
			datatype: 'json',
			success : function(result){
				if(result.ret == "false"){
					alert("아이디 또는 패스워드를 확인해 주시기 바랍니다.");
				}
				else if(result.ret == "true"){
					alert("로그인이 완료되었습니다.");
				}
			},
			error : function(){
				alert("error");
			}
			
		});
	});		

	$('#btnLoginConfirm').click(function(){
		$.ajax({
			url: 'btnLoginConfirm.do',
			type: 'post',
			async : false,
			datatype: 'json',
			success : function(result){
				if(result.loginYn == "N"){
					alert("로그인이 되어 있지 않습니다.");
				}
				else if(result.loginYn == "Y"){
					alert("로그인이 되어 있습니다.");
				}
			},
			error : function(){
				alert("error");
			}
			
		});
	});		
});
	
</script>

</head>
<body>
id : <input id="userId" name="userId" /></br/>
pw : <input id="userPw" name="userPw" /><br/>
<input type="submit" name="btnLogin" id="btnLogin" value="로그인" />
<input type="submit" name="btnLoginConfirm" id="btnLoginConfirm" value="로그인 여부 체크" />
${message}
${name}
</body>
</html>