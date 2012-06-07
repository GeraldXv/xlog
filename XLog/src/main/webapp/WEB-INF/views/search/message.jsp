<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<div class="SRcontent">
	<ul>
		<span><b>Search</b><label>About 96,500,000 results</label><a
			onclick="advs()">Advanced Search</a></span>
		<li class="base"></li>
		<li class="categ">
			<table>
				<tr>
						<td><c:if test="${type=='status' }">
							<li><a id="c3"
								href="<c:url value="/search/status?query=${query }" />"
								class="seled">Status</a></li>
						</c:if> <c:if test="${type!='status'}">
							<li><a id="c3"
								href="<c:url value="/search/status?query=${query }" />">Status</a></li>
						</c:if> <c:if test="${type=='message' }">
							<li><a id="c4"
								href="<c:url value="/search/message?query=${query }"/>"
								class="seled">Message</a></li>
						</c:if> <c:if test="${type!='message'}">
							<li><a id="c4"
								href="<c:url value="/search/message?query=${query }" />">Message</a></li>
						</c:if> <c:if test="${type=='friend' }">
							<li><a id="c2"
								href="<c:url value="/search/friend?query=${query }" />"
								class="seled">Person</a></li>
						</c:if> <c:if test="${type!='friend'}">
							<li><a id="c2"
								href="<c:url value="/search/friend?query=${query }" />">Person</a></li>
						</c:if>
						<li><a id="c4" href="#" onclick="categchg('c5')">Tag</a></li> <input
						type="hidden" id="catg" value="c2"></td>
				</tr>
				<tr>
					<td>
						<li class="spl"></li>
						<li><a id="t1" href="#" class="seled" onclick="timchg('t1')">Anytime</a></li>
						<li><a id="t2" href="#" onclick="timchg('t2')">Past hour</a></li>
						<li><a id="t3" href="#" onclick="timchg('t3')">Past 24
								hour</a></li>
						<li><a id="t4" href="#" onclick="timchg('t4')">Past week</a></li>
						<li><a id="t5" href="#" onclick="timchg('t5')">Past month</a></li>
						<li><a id="t6" href="#" onclick="timchg('t6')">Past year</a></li>
						<input type="hidden" id="tims" value="t1">
					</td>
				</tr>
			</table>
		</li>
		<li>
			<ul>
				<li class="reslt">
					<table>
						<c:forEach var="message" items="${messageList}">
						<tr class="person">
                        	<td rowspan="2" width="50">
                            	<span><c:if
											test="${not empty message.fromProfileImage}">
											<img src="<c:url value="${message.fromProfileImage}" />" />
										</c:if> <c:if test="${ empty message.fromProfileImage}">
											<s:message code="default.png" var="defaultUrl" />
											<img src="<c:url value="${defaultUrl}" />" />
										</c:if></span>
                            </td>
                        	<td colspan="2">
                            	<a class="pname">${message.fromName}</a><b>${message.createdDate}</b>
                            </td>
                            <td>
                            	<a><s:message code="${message.serviceProvider}.png" var="iconUrl" />
											<img src="<c:url value="${iconUrl}" />" /></a>
                            </td>
                        </tr>
                        <tr class="person">
                        	<td colspan="4">
                            	<label>${message.content} </label>
                            </td>
                        </tr>
                        <tr height="20px"></tr><!-- end of a item -->
                       </c:forEach>
					</table>
				</li>
			</ul>
		</li>
	</ul>
	<ul class="pages">
		<li><a id="prep">Prev&lt;</a> 1 <a>2</a> <a>3</a> <a>4</a> <a>5</a>
			<a>6</a> <a>7</a> <a>8</a> <a>9</a> <a>10</a> <a id="nxtp">&gt;Next</a>
		</li>
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
			<td colspan="4" class="bnn"><input type="checkbox">FB</input> <input
				type="checkbox">Google+</input> <input type="checkbox">Twitter</input>
				<input type="checkbox">Gmail</input></td>
		</tr>
		<tr>
			<td></td>
			<td>File Type:</td>
			<td colspan="4" class="bnn"><input type="checkbox">Received</input>
				<input type="checkbox">Sent</input></td>
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
			<td colspan="4"><input class="Wdate" type="text"
				onClick="WdatePicker()"> --<input class="Wdate" type="text"
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
