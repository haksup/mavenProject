<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<colgroup>
	<col width="200">
</colgroup>
<tr>
	<th>제목</th>
</tr>
<c:forEach items="${BOARD_LIST}" var="list" >   
	<tr>
		<td>
			<c:out value="${list.TITLE}"/>
		</td>
	</tr>
</c:forEach>
</table>
<div>${pagingHtml}</div>
</body>
</html>