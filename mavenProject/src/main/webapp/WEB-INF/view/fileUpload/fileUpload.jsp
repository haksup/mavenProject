<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일 업로드 페이지</title>
<!-- js, css include(S)-->
<%@ include file="/WEB-INF/include/include.jsp"%>
<!-- js, css include(E)-->

<script language="javascript">
	var count = 1;
	var addCount;
	
	//행추가
	function addInputBox() {
		for(var i=1; i<=count; i++) {
			if(!document.getElementsByName("file_add"+i)[0]) {
   				addCount = i;
   				break;
			}
  			else addCount = count;
		}
 		
		var addStr = "<tr><td width=40><input type=checkbox name=checkList value="+addCount+" size=40 ></td><td width=140><input type=file name=file_add"+addCount+" size=40></td></tr>";
 
		var table = document.getElementById("dynamic_table");
		var newRow = table.insertRow();
		var newCell = newRow.insertCell();
		newCell.innerHTML = addStr;
		count++;
	}
	
	//행삭제
	function subtractInputBox() {
		var table = document.getElementById("dynamic_table");
	 	//var max = document.gForm.checkList.length;
	 	//alert(max);
	 	var rows = dynamic_table.rows.length;
	 	var chk = 0;
		
	 	if(rows > 1){
	  		for (var i=0; i<document.gForm.checkList.length; i++) {
	   			if (document.gForm.checkList[i].checked == true) {
	    			table.deleteRow(i);
	    			i--;
	    			count--;
	    			chk++;
	   			}
	  		}
		  	if(chk <= 0){
				alert("삭제할 행을 체크해 주세요.");
		  	}
	   }else{
			alert("더이상 삭제할 수 없습니다.");
	   }
	}
	 
	// 저장
	function submitbutton() {
		var gform = document.gForm;
	 	gform.count.value = eval(count);
	 	//alert(count);
		gForm.submit();
	 	return;
	}
	
	// 파일 다운로드
	function fn_download(f_group, f_order){
		var inputs = '';
		inputs += '<input type="hidden" name="f_group" value="'+ f_group +'" />';
		inputs += '<input type="hidden" name="f_order" value="'+ f_order +'" />';
		
		//send request
		$('<form action="/fileDownload.do" method="post">'+inputs+'</form>').appendTo('body').submit().remove();
	}
	
	// 삭제 데이터 선택
	function fn_delSelect(f_order){
		var dbox=document.gForm.delbox;
		len = dbox.length;
	  	for(var i=0; i<len; i++) {
	    	if(dbox.delbox[i].value == f_order)
	    		dbox.delbox[i].checked=true;
	  	}
	}
	
	// 삭제 선택 취소
	function fn_delCancel(f_order){
		var dbox=document.gForm.delbox;
		len = dbox.length;
	  	for(var i=0; i<len; i++) {
	    	if(dbox.delbox[i].value == f_order)
	    		dbox.delbox[i].checked=false;
	  	}
	}
</script>
</head>
<body>

	<form name="gForm" action="upload.do" enctype="multipart/form-data" method="post" >
		<table>
		<c:forEach items="${fileList}" var="list" >   
			<tr>
				<td>
					<a href="#" onclick="fn_download(<c:out value="${list.F_GROUP}"/>,<c:out value="${list.F_ORDER}"/>)"><c:out value="${list.F_NAME_ORG}"/></a>
				</td>
				<td>
					<c:out value="${list.F_SIZE}"/>
				</td>
				<td>
					<a href="#" onclick="fn_delSelect(<c:out value="${list.F_ORDER}"/>)">삭제</a>
					<a href="#" onclick="fn_delCancel(<c:out value="${list.F_ORDER}"/>)">취소</a>
					<input type="checkbox" name="delbox" value="<c:out value="${list.F_ORDER}"/>" style="display: none;" />
				</td>
			</tr>
		</c:forEach>
		</table>
		
		<input type="button" value="행 추가" onclick="javascript:addInputBox();"> : 
		<input type="button" value="행 삭제" onclick="javascript:subtractInputBox();">
		<input type="button" value="저장" onclick="javascript:submitbutton();">
  		<input type="hidden" name="count" >
		<table  cellpadding=0 cellspacing=0 id="" border="1">
			<tr>
				<td width="40">체크</td>
				<td width="160">내용</td>
			</tr>
			<tr>
				<td colspan="2">
					<table cellpadding=0 cellspacing=0 id="dynamic_table" border="1"></table>
				</td>
			</tr>
		</table>
 		<input type="hidden" id="f_group" name="f_group" value="<c:out value="${fileList[0].F_GROUP}"/>" />
	</form>
</body>
</html>