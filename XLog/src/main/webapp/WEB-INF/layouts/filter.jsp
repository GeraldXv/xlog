<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<span><b>Search</b><label>About ${totalNum} results</label><a onclick="advs()">Advanced Search</a></span>
<li class="base"></li>
<li class="categ">
	<table>
		<tr>
			<td><c:if test="${type=='status' }">
					<li><a id="c3" href="<c:url value="/search/status?query=${query }&page=1&range=none" />" class="seled">Status</a></li>
				</c:if> <c:if test="${type!='status'}">
					<li><a id="c3" href="<c:url value="/search/status?query=${query }&page=1&range=none" />">Status</a></li>
				</c:if> <c:if test="${type=='message' }">
					<li><a id="c4" href="<c:url value="/search/message?query=${query }&page=1&range=none"/>" class="seled">Message</a></li>
				</c:if> <c:if test="${type!='message'}">
					<li><a id="c4" href="<c:url value="/search/message?query=${query }&page=1&range=none" />">Message</a></li>
				</c:if> <c:if test="${type=='friend' }">
					<li><a id="c2" href="<c:url value="/search/friend?query=${query }&page=1&range=none" />" class="seled">Person</a></li>
				</c:if> <c:if test="${type!='friend'}">
					<li><a id="c2" href="<c:url value="/search/friend?query=${query }&page=1&range=none" />">Person</a></li>
				</c:if> <c:if test="${type=='tag' }">
					<li><a id="c1" href="<c:url value="/search/tag?query=${query }&page=1&range=none" />" class="seled">Tag</a></li>
				</c:if> <c:if test="${type!='tag'}">
					<li><a id="c1" href="<c:url value="/search/tag?query=${query }&page=1&range=none" />">Tag</a></li>
				</c:if></td>
		</tr>
		<tr>
			<td>
				<li class="spl"></li> <c:if test="${range=='none' }">
					<li><a id="t1" href="<c:url value="/search/${type}?query=${query }&page=1&range=none" />" class="seled" onclick="timchg('t1')">Anytime</a></li>
				</c:if> <c:if test="${range!='none' }">
					<li><a id="t1" href="<c:url value="/search/${type}?query=${query }&page=1&range=none" />" onclick="timchg('t1')">Anytime</a></li>
				</c:if> <c:if test="${range=='hour' }">
					<li><a id="t2" href="<c:url value="/search/${type}?query=${query }&page=1&range=hour" />" class="seled" onclick="timchg('t2')">Past
							hour</a></li>
				</c:if> <c:if test="${range!='hour' }">
					<li><a id="t2" href="<c:url value="/search/${type}?query=${query }&page=1&range=hour" />" onclick="timchg('t2')">Past hour</a></li>
				</c:if> <c:if test="${range=='day' }">
					<li><a id="t3" href="<c:url value="/search/${type}?query=${query }&page=1&range=day" />" class="seled" onclick="timchg('t3')">Past
							24 hour</a></li>
				</c:if> <c:if test="${range!='day' }">
					<li><a id="t3" href="<c:url value="/search/${type}?query=${query }&page=1&range=day" />" onclick="timchg('t3')">Past 24 hour</a></li>
				</c:if> <c:if test="${range=='week' }">
					<li><a id="t4" href="<c:url value="/search/${type}?query=${query }&page=1&range=week" />" class="seled" onclick="timchg('t4')">Past
							week</a></li>
				</c:if> <c:if test="${range!='week' }">
					<li><a id="t4" href="<c:url value="/search/${type}?query=${query }&page=1&range=week" />" onclick="timchg('t4')">Past week</a></li>
				</c:if> <c:if test="${range=='month' }">
					<li><a id="t5" href="<c:url value="/search/${type}?query=${query }&page=1&range=month" />" class="seled" onclick="timchg('t5')">Past
							month</a></li>
				</c:if> <c:if test="${range!='month' }">
					<li><a id="t5" href="<c:url value="/search/${type}?query=${query }&page=1&range=month" />" onclick="timchg('t5')">Past month</a></li>
				</c:if> <c:if test="${range=='year' }">
					<li><a id="t6" href="<c:url value="/search/${type}?query=${query }&page=1&range=year" />" class="seled" onclick="timchg('t6')">Past
							year</a></li>
				</c:if> <c:if test="${range!='year' }">
					<li><a id="t6" href="<c:url value="/search/${type}?query=${query }&page=1&range=year" />" onclick="timchg('t6')">Past year</a></li>
				</c:if> <input type="hidden" id="tims" value="t1">
			</td>
		</tr>
	</table>
</li>