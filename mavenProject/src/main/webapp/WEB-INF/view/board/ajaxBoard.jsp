<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajax Board</title>
<!-- js, css include(S)-->
<%@ include file="/WEB-INF/include/include.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxBoard.js"></script>
<!-- js, css include(E)-->

<script language="javascript">
jQuery(document).ready(function(){
	fn_callBoard("NOTICE","board1");
	fn_callBoard("FREEBOARD","board2");
	
	$('#save').click(function(){
		$("#ajaxBoardForm").ajaxSubmit({
			url : "insertAjaxBoard.do",
			dataType: "html",
			success: function(data) {
				var boardName = jQuery(':input:radio[name=boardName]:checked').val();
				if(boardName == "NOTICE"){
					fn_callBoard("NOTICE","board1");
				}
				else if(boardName == "FREEBOARD"){
					fn_callBoard("FREEBOARD","board2");
				}
				alert("저장이 완료되었습니다.");
			},
			error : function(){
				alert("정보를 저장 하지 못했습니다.");
			}
		});
	});
	
	$("#modify").click(function(){
		$("#ajaxBoardForm").ajaxSubmit({
			url : "modifyAjaxBoard.do",
			dataType: "html",
			data : {
				boardNo : global_boardNo
			},
			success: function(data) {
				fn_callBoard(global_boardName, global_innerBoardName, global_currentPage);
				alert("수정되었습니다.");
			},
			error : function(){
				alert("정보를 저장 하지 못했습니다.");
			}
		});
	});
	
	$("#delete").click(function(){
		$.ajax({
			url: 'deleteAjaxBoard.do',
			type: 'post',
			data : {
				boardName :global_boardName
				, boardNo : global_boardNo
			},
			async : false,
			datatype: 'json',
			success : function(result){
				fn_callBoard(global_boardName, global_innerBoardName, global_currentPage);
				alert("삭제되었습니다.");
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
<div id="wrap">
	<div style="float: left;">
		<div id="board1"></div>
		
		<div style="height: 30px;"></div>
		
		<div id="board2"></div>
	</div>
	
	<div id="boardView" style="float:left; padding:0 0 0 50px; width: 530px;">
		<div id="tableForm">
			<form id="ajaxBoardForm">
			<table border="1">
				<colgroup>
					<col width="100"/>
					<col width="400"/>
				</colgroup>
				<tr>
					<td>게시판 선택</td>
					<td>
						<input type="radio" name="boardName" id="NOTICE" value="NOTICE" checked="checked"/><label>공지사항</label>
						<input type="radio" name="boardName" id="FREEBOARD" value="FREEBOARD" /><label>자유게시판</label>
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" id="title" name="title" style="width:350px;" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea id="contents" name="contents" style="width:350px; height: 100px;"></textarea></td>
				</tr>
			</table>
			</form>
		</div>
		<div id="tableButton" style="padding:10px 0 0 0; text-align: center;">
			<input type="button" id="save" value="저장" />
			<input type="button" id="modify" value="수정" />
			<input type="button" id="delete" value="삭제" />
		</div>
	</div>
</div>
</body>
</html>