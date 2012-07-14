<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<div class="header">
	<table width="950px">
		<tr>
			<td width="20px"></td>
			<td width="160px"><a href="<c:url value="/" />"> <img id="title" src="<c:url value="/resources/image/title.png" />"></a></td>
			<td width="15px"><a href="#" onclick="popunr()"> <img src="<c:url value="/resources/image/down.png" />" />
			</a></td>
			<td width="40px"><img id="unrpic" src="<c:url value="/resources/image/bubble.png" />" /><b id="unrno">${messageNotification.allMessage}</b>
			</td>
			<td width="80px"></td>
			<td width="430px"><c:url var="searchUrl" value="/search/" /> <sf:form action="${searchUrl}" method="post"
					modelAttribute="searchForm">
					<input type="hidden" name="type" value="${type}"></input>
					<sf:input type="text" path="query" value="${query}"></sf:input>
					<button type="submit" name="search">
						<img src="<c:url value="/resources/image/search.png" />" />
					</button>
				</sf:form></td>


			<td width="50px"></td>
			<td width="100px"><a id="friend" href="<c:url value="/friend/" />"> Friends </a> <a id="message" href="<c:url value="/message/" />">
					Message </a></td>
			<td width="60px"><a href="#"> <c:if test="${not empty profileImage }">
						<img id="portrait" src="<c:url value="${profileImage}" />" />
					</c:if> <c:if test="${ empty profileImage }">
						<s:message code="default.png" var="defaultUrl" />
						<img id="portrait" src="<c:url value="${defaultUrl}" />" />
					</c:if>
			</a></td>
			<td width="10px"><a href="#" onclick="popsetting()"> <img src="<c:url value="/resources/image/down.png" />" />
			</a></td>
			<td width="20px"></td>
		</tr>
	</table>
</div>

<!-- popup unread message window-->
<div id="unrwin" class="unrwin">
	<table width="90px">
		<tr height="8"></tr>
		<tr height="21">
			<td width="70px"><a href="<c:url value="/message/" />"> Gmail </a></td>
			<td class="nofunr" width="20px">${messageNotification.gmailMessage}</td>
		</tr>
		<tr height="1"></tr>
		<tr height="21">
			<td width="70px"><a href="<c:url value="/message/" />"> Twitter </a></td>
			<td class="nofunr" width="20px">${messageNotification.twitterMessage}</td>
		</tr>
	</table>
</div>

<!-- popup setting window-->
<div id="setwin" class="setwin">
	<table>
		<tr height="7">
		</tr>
		<tr height="25">
			<td><a href="<c:url value="/source/" />"> Setting </a></td>
		</tr>
		<tr height="1">
		</tr>
		<tr height="25">
			<td><a href="<c:url value="/signout" />"> Logout </a></td>
		</tr>
		<tr height="1">
		</tr>
		<tr height="26">
			<td><a href="#"> Help </a></td>
		</tr>
	</table>
</div>
