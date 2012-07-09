<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<title>XLOG</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<s:message code="favicon.ico" var="icoUrl" />
<link rel="shortcut icon" href="<c:url value="${icoUrl}" />" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/index.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/footer.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/searchresult.css" />" />

<script type="text/javascript" src="<c:url value="/resources/js/searchC.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/My97DatePicker/WdatePicker.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/ddsmoothmenu.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/header.js" />"></script>


</head>
<body>
	<div id="content">
		<div id=header>
			<tiles:insertTemplate template="header.jsp" />
		</div>
		<div class="SRcontent">
			<ul>
				<tiles:insertTemplate template="filter.jsp" />
				<tiles:insertAttribute name="content" />
			</ul>
			<ul>
				<tiles:insertTemplate template="page.jsp" />
			</ul>
		</div>
		<tiles:insertTemplate template="advance.jsp" />
	</div>
	<div id="foot">
		<tiles:insertTemplate template="foot.jsp" />
	</div>
</body>
</html>
