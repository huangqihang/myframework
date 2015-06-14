<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<%@include file="/common/jsp/taglib.jsp" %>

<html>
<head>

<%@include file="/common/jsp/header.jsp" %>

<title>Insert title here</title>
</head>
<body>
	<table style="margin-left:20px">
		<c:forEach items="1,2,3,4,5" var="i" varStatus="vStatus">
			<tr><td>${vStatus.index } -> ${ i } </td></tr>
		</c:forEach>
	</table>
</body>
</html>