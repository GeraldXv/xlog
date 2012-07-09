<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<ul class="pages">
	<li><a id="prep" href="<c:url value="/search/${type}?query=${query}&page=${currentPage-1}&range=none" />">Prev&lt;</a> <c:forEach
			var="i" begin="1" end="${totalPage }" step="1" varStatus="status">
			<c:if test="${i==currentPage }">
				<a><c:out value="${i}" /> </a>
			</c:if>
			<c:if test="${i!=currentPage }">
				<a href="<c:url value="/search/${type}?query=${query}&page=${i}&range=none" />"><c:out value="${i}" /> </a>
			</c:if>
		</c:forEach> <a id="nxtp" href="<c:url value="/search/${type}?query=${query}&page=${currentPage+1}&range=none" />">&gt;Next</a></li>
</ul>