<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>

		<li>
			<ul>
				<li class="reslt">
					<table>
						<c:forEach var="status" items="${statusList}">
							<tr class="person">
								<td rowspan="3" width="50"><span><c:if test="${not empty status.userImage}">
											<img src="<c:url value="${status.userImage}" />" />
										</c:if> <c:if test="${ empty status.userImage}">
											<s:message code="default.png" var="defaultUrl" />
											<img src="<c:url value="${defaultUrl}" />" />
										</c:if></span></td>
								<td colspan="2"><a class="pname">${status.fromUser}</a><b>${status.createdTime}</b></td>
								<td><a><s:message code="${status.serviceProvider}.png" var="iconUrl" /> <img src="<c:url value="${iconUrl}" />" /></a></td>
							</tr>
							<tr class="person">
								<td colspan="4"><label>${status.content} </label></td>
							</tr>
							<tr>
								<td> Tag:<c:forEach var="tag" items="${status.tags }">
									<a class="tag" href="<c:url value="/search/tag?query=${tag.tagName }&page=1&range=week" />" >${tag.tagName}</a>
								</c:forEach>
								</td>
							</tr>
							<tr height="20px"></tr>
							<!-- end of a item -->
						</c:forEach>
					</table>
				</li>
			</ul>
		</li>
	