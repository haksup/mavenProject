var global_boardName;
var global_innerBoardName;
var global_currentPage;
var global_boardNo;
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
		url: 'callAjaxBoard.do',
		type: 'post',
		data : param,
		async : false,
		datatype: 'json',
		success : function(result){
			var board = fn_drawBoard(param, result.boardList);
			$('#' + innerBoardName).html(board + "<br/>"+ result.pagingHtml);
		},
		error : function(){
			alert("error");
		}
		
	});
}

/**
 * 게시판 그리기
 * @param boardList
 * @returns {String}
 */
function fn_drawBoard(param, boardList){
	var board = "<table border='1'>";
	board += "<tr>";
	board += "<td width='50'>번호</td>";
	board += "<td width='250'>제목</td>";
	board += "<td width='50'>등록자</td>";
	board += "<td width='150'>등록일</td>";
	board += "</tr>";
	if(boardList.length == 0){
		board += "<tr>";
		board += "	<td colspan='4' align='center'>등록된 게시물이 없습니다</td>";
		board += "</tr>";
	}
	else{
		for(var i = 0; i < boardList.length; i++){
			board += "<tr>";
			board += "	<td>";
			board += boardList[i].BOARD_NUMBER;
			board += "	</td>";
			board += "	<td><a href='#' onClick=\"fn_boardDetail('" + param.boardName + "', '" +
							param.innerBoardName + "', " + param.currentPage + ", " + boardList[i].BOARD_NO + ");\">";
			board += boardList[i].TITLE;
			board += "	</a></td>";
			board += "	<td>";
			board += boardList[i].REG_USER;
			board += "	</td>";
			board += "	<td>";
			board += boardList[i].REG_DATE;
			board += "	</td>";
			board += "</tr>";
		}
	}
	board += "</table>";
	
	return board;
}

/**
 * 게시판 세부 내용 호출
 * @param boardNo
 */
function fn_boardDetail(boardName, innerBoardName, currentPage, boardNo){
	global_boardName = boardName;     
	global_innerBoardName = innerBoardName;
	global_currentPage = currentPage;
	global_boardNo = boardNo;

	var param = new Object();
	param.boardName = boardName;
	param.boardNo = boardNo;
	
	$("#" + boardName).attr("checked", "checked");
	
	$.ajax({
		url: 'boardAjaxDetail.do',
		type: 'post',
		data : param,
		async : false,
		datatype: 'json',
		success : function(result){
			$('#title').val(result.TITLE);
			$('#contents').val(result.CONTENTS);
		},
		error : function(){
			alert("error");
		}
		
	});
}