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
	fn_callBoard("NOTICE","board1");
	fn_callBoard("NOTICE","board2");
});


// 공통 js로 묶어서 작업(S)

/**
 * 게시판 호출
 * @param boardName		 : 게시판 명
 * @param innerBoardName : 게시판 위치 inner 명
 * @param currentPage	 : 현재 페이지
 */
function fn_callBoard(boardName, innerBoardName, currentPage){
	var param = new Object();
	param.boardName = boardName;
	param.innerBoardName = innerBoardName;
	param.currentPage = currentPage;
	
	$.ajax({
		url: 'callBoard.do',
		type: 'post',
		data : param,
		async : false,
		datatype: 'json',
		success : function(result){
			var board = fn_drawBoard(result.boardList);
			$('#' + innerBoardName).html(board + "<br/>"+ result.pagingHtml);
		},
		error : function(){
			alert("error");
		}
		
	});
}

function fn_drawBoard(boardList){
	var board = "<table border='1'>";
	board += "<tr>";
	board += "<td width='50'>번호</td>";
	board += "<td width='250'>제목</td>";
	board += "<td width='50'>등록자</td>";
	board += "<td width='150'>등록일</td>";
	board += "</tr>";
	for(var i = 0; i < boardList.length; i++){
		board += "<tr>";
		board += "	<td>";
		board += boardList[i].BOARD_NUMBER;
		board += "	</td>";
		board += "	<td>";
		board += boardList[i].TITLE;
		board += "	</td>";
		board += "	<td>";
		board += boardList[i].REG_USER;
		board += "	</td>";
		board += "	<td>";
		board += boardList[i].REG_DATE;
		board += "	</td>";
		board += "</tr>";
	}
	board += "</table>";
	
	return board;
}

//공통 js로 묶어서 작업(E)
</script>
</head>
<body>
<div id="wrap">
	<div id="board1">
	</div>
	
	<div style="height: 30px;"></div>
	
	<div id="board2">
	</div>
</div>
</body>
</html>