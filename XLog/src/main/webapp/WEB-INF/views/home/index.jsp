<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>


<div class="status">
	<c:url var="sendStatusUrl" value="/status" />
	<sf:form action="${sendStatusUrl}" method="post" modelAttribute="statusForm">
	<ul>
		<li><sf:textarea path="text"></sf:textarea></li>
		<!--end of input-->
	</ul>
	<ul>
		<div class="searchoption">
				<table>
					<tr>
						<td><a href="#" onclick="picsel()"> 
						</a></td>
						<td><sf:checkbox path="provider" id="cfb" value=" facebook" /> <sf:checkbox path="provider" id="ctw" value=" twitter" /></td>
						<td><label id="lfb" for="cfb" onclick="chk(this)"></label></td>
						<td><label id="lgp" for="cgp" onclick="chk(this)"></label></td>
						<td><label id="ltw" for="ctw" onclick="chk(this)"></label></td>
						<td>
							<button type="submit" class="postsearch">Send</button>
						</td>
					</tr>
				</table>
			
		</div>
		<!--end of search option-->
	</ul>
	</sf:form>
</div>
<!--end of status-->

<br />
<br />

<div class="content">
	<ul>
		<li>
			<div class="category">
				<ul>
					<input type="hidden" id="currentcategory" name="c1">
					<li><a href="<c:url value="/" />" class="all1"></a></li>
					<li><a href="<c:url value="/status/facebook" />" class="fb0"></a></li>
					<li><a href="<c:url value="/status/google" />" class="g0"></a></li>
					<li><a href="<c:url value="/status/twitter" />" class="t0"></a></li>
					<li><a href="<c:url value="/status/gmail" />" class="gmail0"></a></li>
				</ul>
			</div> <!--end of category-->
			<div class="tagcloud">
				<ul id="topcloud"></ul>
				<ul>
					<li><c:forEach var="tag" items="${tags}" begin="6" end="6">
							<a class="style4">${tag.tagName}</a>
						</c:forEach> <c:forEach var="tag" items="${tags}" begin="0" end="0">
							<a class="style1">${tag.tagName}</a>
						</c:forEach> <c:forEach var="tag" items="${tags}" begin="4" end="4">
							<a class="style3">${tag.tagName}</a>
						</c:forEach> <c:forEach var="tag" items="${tags}" begin="2" end="2">
							<a class="style2">${tag.tagName}</a>
						</c:forEach> <c:forEach var="tag" items="${tags}" begin="7" end="7">
							<a class="style4">${tag.tagName}</a>
						</c:forEach> <c:forEach var="tag" items="${tags}" begin="1" end="1">
							<a class="style1">${tag.tagName}</a>
						</c:forEach> <c:forEach var="tag" items="${tags}" begin="5" end="5">
							<a class="style3">${tag.tagName}</a>
						</c:forEach> <c:forEach var="tag" items="${tags}" begin="3" end="3">
							<a class="style2">${tag.tagName}</a>
						</c:forEach></li>
				</ul>
				<ul id="bottomcloud"></ul>
			</div> <!--end of tagcloud-->
		</li>
		<li>
			<div class="contentpreview">
				<input type="hidden" id="currentexp" name="0">
				<ul>

					<c:forEach var="status" items="${statusList}" varStatus="s">

						<li id="msg${s.index }" title="${status.statusId}">
							<table width="390px">
								<tr>
									<td class="picc" width="50px"><c:if test="${not empty status.userImage}">
											<img src="<c:url value="${status.userImage}" />" />
										</c:if> <c:if test="${ empty status.userImage}">
											<s:message code="default.png" var="defaultUrl" />
											<img src="<c:url value="${defaultUrl}" />" />
										</c:if></td>
									<td id="shortc${s.index}" class="words" width="300px"><span> ${status.content } </span> <c:if test="${not empty status.picture}">
											<img src="<c:url value="${status.picture}" />" onclick="chgsize(this)" />
										</c:if></td>
									<c:if test="${status.stared==true }">
										<td width="25px"><a class="marked" onclick="chgmark(this)"></a></td>
									</c:if>
									<c:if test="${status.stared==false }">
										<td width="25px"><a class="mark" onclick="chgmark(this)"></a></td>
									</c:if>
									<c:if test="${s.index==0}">
										<td id="arro${s.index}" class="arrowed" width="15px" rowspan="2">
									</c:if>
									<c:if test="${s.index!=0}">
										<td id="arro${s.index}" class="arrow" width="15px" rowspan="2">
									</c:if>
									<a href="javascript:void(0)" onclick="showdetail(${s.index})"></a>
									</td>
								</tr>
								<tr>
									<td class="state" colspan="2"><span id="name${s.index}">${status.fromUser}</span>. <span id="time${s.index}"><fmt:formatDate value="${status.createdTime}" type="both"
											pattern="MM-dd HH:mm" /></span> <a href="">share</a>.<a href="">reply</a>.<a href="javascript:void(0)" id="m${s.index}" name="${status.idAtService}"
										onclick="showtagf('m${s.index}','${status.idAtService}','${status.statusId}','${status.fromUser}')">tag</a>.<a href="javascript:void(0)" onclick="delmsg(this)">delete</a>.
									</td>
									<td class="source"><a href="#"> <s:message code="${status.serviceProvider}.png" var="iconUrl" /> <img
											src="<c:url value="${iconUrl}" />" />
									</a></td>
								</tr>
							</table>
						</li>
					</c:forEach>


					<li>
						<!-- use to be the border-->
					</li>
			          <span id="nomsg" class="nomsg">
			          No message currently,
			          You may try to:
			          	<button><img src="<c:url value="/resources/image/user_search.png" />" />Search for new friends</button>
			          	<button><img src="<c:url value="/resources/image/comment_edit.png" />" />Post a new status</button>
			          	<button><img src="<c:url value="/resources/image/mail_edit.png" />" />Check the private message</button> 
			          </span>
			          <span id="noser" class="noser">
			          The service <label>Google+</label> haven't been connected, click <a href="">here</a> to add the service
			          </span>
				</ul>
			</div> <!--end of contentpreview-->
		</li>
		<li>
			<div class="detailcontent">
				<c:forEach var="status" items="${statusList}" begin="0" end="0">
					<ul>
						<li id="detailp">
							<!-- start of a message-->
							<table width="378px">
								<tr>
									<td class="picc" width="50px"><c:if test="${not empty status.userImage}">
											<img src="<c:url value="${status.userImage}" />" />
										</c:if> <c:if test="${ empty status.userImage}">
											<s:message code="default.png" var="defaultUrl" />
											<img src="<c:url value="${defaultUrl}" />" />
										</c:if></td>

									<td class="words" width="265px"><span><c:out value="${status.content}"></c:out></span></td>
									<c:if test="${status.stared==true }">
										<td width="25px"><a class="marked"></a></td>
									</c:if>
									<c:if test="${status.stared==false }">
										<td width="25px"><a class="mark"></a></td>
									</c:if>
								</tr>
								<tr>
									<td class="state" colspan="2">${status.fromUser}. ${status.createdTime}. <a href="">share</a>.<a href="">reply</a>.<a
										href="javascript:void(0)" id="m1" onclick="showtagf('m1')">tag</a>.<a href="">delete</a>.
									</td>
									<td class="source"><a href="#"><s:message code="${status.serviceProvider}.png" var="iconUrl" /> <img
											src="<c:url value="${iconUrl}" />" /></a></td>
								</tr>
							</table>
						</li>
						<li id="dcont" class="contents">
							<h5>Sent</h5>:${status.createdTime}<br />
							<h5>To</h5>: ${status.fromUser} <br /> ${status.content}
						</li>
						<li></li>
					</ul>
				</c:forEach>
			</div> <!--end of detail-->
		</li>
	</ul>
</div>
<!-- end of content -->

<!-- hidden tag div -->
<form>
<div id="tagdiv" class="tagfunc">
	<ul id="leftborder">
	</ul>
	<ul id="lefthalf">
		<li>Commonly used tags:</li>
		<li>Tags you wants:</li>
	</ul>
	<ul id="righthalf">
		<li><a id="sug1" onclick="clicktofinish('sug1')">Apple</a> <a id="sug2" onclick="clicktofinish('sug2')">Brother</a></li>
		<br>
		<li><input id="taginput" type="text" /><button type="button" id="tagbutton" onclick="tagit(this)">Tag it</button></li>
	</ul>
	<ul id="rightborder">
	</ul>
	<input type="hidden" value="no" id="lastid">
</div>
</form>

<div class="suctag">
	<ul><img src="/resources/image/accept.png" />Your tag has been successfully added!</ul>
	<li></li>
</div>
<!-- end of hidden tag div -->
</div>
