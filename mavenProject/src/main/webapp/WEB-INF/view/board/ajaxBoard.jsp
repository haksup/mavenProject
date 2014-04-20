<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajax Board</title>
<!-- js, css include(S)-->
<%@ include file="/WEB-INF/include/include.jsp"%>
<!-- js, css include(E)-->

<script language="javascript">
jQuery(document).ready(function(){
	callBoard("NOTICE","board2");
});

/**
 * 게시판 호출
 * @param boardName		 : 게시판 명
 * @param innerBoardName : 게시판 위치 inner 명
 */
function callBoard(boardName, innerBoardName){
	var param = new Object();
	param.boardName = boardName;
	
	$.ajax({
		url: 'callBoard.do',
		type: 'post',
		data : param,
		async : false,
		datatype: 'json',
		success : function(result){
			$('#' + innerBoardName).html(result.title + result.pagingHtml);
// 			if(result.ret == "false"){
// 				alert("아이디 또는 패스워드를 확인해 주시기 바랍니다.");
// 			}
// 			else if(result.ret == "true"){
// 				alert("로그인이 완료되었습니다.");
// 			}
		},
		error : function(){
			alert("error");
		}
		
	});
}
</script>
</head>
<body>
<div id="wrap">
	<div id="board1">
		aaa
	</div>
	
	
	<div id="board2">
		bbb
	</div>
</div>
</body>
</html>