<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>

<li>
	<ul>
		<li class="reslt"><c:forEach var="message" items="${messageList}">
				<table>
					<tr class="person">
						<td rowspan="2" width="50"><span><c:if test="${not empty message.fromProfileImage}">
									<img src="<c:url value="${message.fromProfileImage}" />" />
								</c:if> <c:if test="${ empty message.fromProfileImage}">
									<s:message code="default.png" var="defaultUrl" />
									<img src="<c:url value="${defaultUrl}" />" />
								</c:if></span></td>
						<td colspan="2"><a class="pname">${message.fromName}</a><b>${message.createdDate}</b></td>
						<td><a><s:message code="${message.serviceProvider}.png" var="iconUrl" /> <img src="<c:url value="${iconUrl}" />" /></a></td>
					</tr>
					<tr class="person">
						<td colspan="4"><label onclick="expcont(this)">${message.content} </label></td>
					</tr>
					<tr height="1px"></tr>
					<!-- end of a item -->
				</table>
			</c:forEach></li>
	</ul>
</li>