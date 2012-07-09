<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>

<li>
	<ul>
		<li class="reslt">
			<table>
				<c:forEach var="friend" items="${friends}">
					<tr class="person">
						<td rowspan="1" width="50"><span><c:if test="${not empty friend.imageUrl}">
									<img src="<c:url value="${friend.imageUrl}" />" />
								</c:if> <c:if test="${ empty friend.imageUrl}">
									<s:message code="default.png" var="defaultUrl" />
									<img src="<c:url value="${defaultUrl}" />" />
								</c:if></span></td>
						<td colspan="2"><a class="pname">${friend.screenName }</a> <c:if test="${empty friend.gender }">
								<img class="pgender" src="<c:url value="/resources/image/nogender.png" />" />
							</c:if> <c:if test="${not empty friend.gender }">
								<img class="pgender" src="<c:url value="/resources/image/${friend.gender}.png" />" />
							</c:if> <b></b></td>
						<td><a><ul class="source">
									<s:message code="${friend.serviceProvider}.png" var="iconUrl" />
									<img src="<c:url value="${iconUrl}" />" />

								</ul></a></td>
					</tr>
					<tr height="20px"></tr>
					<!-- end of a item -->
				</c:forEach>
			</table>
		</li>
	</ul>
</li>
