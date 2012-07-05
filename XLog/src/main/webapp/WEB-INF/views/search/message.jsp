<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<div class="SRcontent">
	<ul>
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
							<li><a id="c2" href="<c:url value="tag }&page=1&range=none" />" class="seled">Tag</a></li>
						</c:if> <c:if test="${type!='tag'}">
							<li><a id="c2" href="<c:url value="/search/tag?query=${query }&page=1&range=none" />">Tag</a></li>
						</c:if></td>
				</tr>
				<tr>
					<td>
						<li class="spl"></li> <c:if test="${range=='none' }">
							<li><a id="t1" href="<c:url value="/search/message?query=${query }&page=1&range=none" />" class="seled" onclick="timchg('t1')">Anytime</a></li>
						</c:if> <c:if test="${range!='none' }">
							<li><a id="t1" href="<c:url value="/search/message?query=${query }&page=1&range=none" />" onclick="timchg('t1')">Anytime</a></li>
						</c:if> <c:if test="${range=='hour' }">
							<li><a id="t2" href="<c:url value="/search/message?query=${query }&page=1&range=hour" />" class="seled" onclick="timchg('t2')">Past
									hour</a></li>
						</c:if> <c:if test="${range!='hour' }">
							<li><a id="t2" href="<c:url value="/search/message?query=${query }&page=1&range=hour" />" onclick="timchg('t2')">Past hour</a></li>
						</c:if> <c:if test="${range=='day' }">
							<li><a id="t3" href="<c:url value="/search/message?query=${query }&page=1&range=day" />" class="seled" onclick="timchg('t3')">Past
									24 hour</a></li>
						</c:if> <c:if test="${range!='day' }">
							<li><a id="t3" href="<c:url value="/search/message?query=${query }&page=1&range=day" />" onclick="timchg('t3')">Past 24 hour</a></li>
						</c:if> <c:if test="${range=='week' }">
							<li><a id="t4" href="<c:url value="/search/message?query=${query }&page=1&range=week" />" class="seled" onclick="timchg('t4')">Past
									week</a></li>
						</c:if> <c:if test="${range!='week' }">
							<li><a id="t4" href="<c:url value="/search/message?query=${query }&page=1&range=week" />" onclick="timchg('t4')">Past week</a></li>
						</c:if> <c:if test="${range=='month' }">
							<li><a id="t5" href="<c:url value="/search/message?query=${query }&page=1&range=month" />" class="seled" onclick="timchg('t5')">Past
									month</a></li>
						</c:if> <c:if test="${range!='month' }">
							<li><a id="t5" href="<c:url value="/search/message?query=${query }&page=1&range=month" />" onclick="timchg('t5')">Past month</a></li>
						</c:if> <c:if test="${range=='year' }">
							<li><a id="t6" href="<c:url value="/search/message?query=${query }&page=1&range=year" />" class="seled" onclick="timchg('t6')">Past
									year</a></li>
						</c:if> <c:if test="${range!='year' }">
							<li><a id="t6" href="<c:url value="/search/message?query=${query }&page=1&range=year" />" onclick="timchg('t6')">Past year</a></li>
						</c:if> <input type="hidden" id="tims" value="t1">
					</td>
				</tr>
			</table>
		</li>
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
	</ul>
	<ul class="pages">
		<li><a id="prep" href="<c:url value="/search/message?query=${query}&page=${currentPage-1}&range=none" />">Prev&lt;</a> <c:forEach
				var="i" begin="1" end="${totalPage }" step="1" varStatus="status">
				<c:if test="${i==currentPage }">
					<a><c:out value="${i}" /> </a>
				</c:if>
				<c:if test="${i!=currentPage }">
					<a href="<c:url value="/search/message?query=${query}&page=${i}&range=none" />"><c:out value="${i}" /> </a>
				</c:if>
			</c:forEach> <a id="nxtp" href="<c:url value="/search/message?query=${query}&page=${currentPage+1}&range=none" />">&gt;Next</a></li>
	</ul>
</div>


<!-- popup advanced window -->
<div id="advsr" class="advsrch">
	<table height="160" width="400">
		<tr>
			<td width="20"></td>
			<td width="100"></td>
			<td width="80"></td>
			<td width="80"></td>
			<td width="120"></td>
			<td width="20"></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2">Narrow results by:</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>Service:</td>
			<td colspan="4" class="bnn"><input type="checkbox">FB</input> <input type="checkbox">Google+</input> <input type="checkbox">Twitter</input>
				<input type="checkbox">Gmail</input></td>
		</tr>
		<tr>
			<td></td>
			<td>File Type:</td>
			<td colspan="4" class="bnn"><input type="checkbox">Received</input> <input type="checkbox">Sent</input></td>
		</tr>
		<tr>
			<td></td>
			<td>File Format:</td>
			<td colspan="4"><select>
					<option value="">Any Format</option>
					<option value="">Picture</option>
					<option value="">File</option>
			</select></td>
		</tr>
		<tr>
			<td></td>
			<td>Last Update:</td>
			<td colspan="4"><input class="Wdate" type="text" onClick="WdatePicker()"> --<input class="Wdate" type="text"
				onClick="WdatePicker()"></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2"></td>
			<td colspan="3">
				<button type="button" onclick="">Advanced Search</button>
			</td>
		</tr>
	</table>
</div>
<!-- end of popup advanced window-->



<!-- end of content -->
