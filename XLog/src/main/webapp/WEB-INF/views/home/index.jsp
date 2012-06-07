<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>


<div class="status">
	<ul>
		<li><textarea></textarea></li>
		<!--end of input-->
	</ul>
	<ul>
		<div class="searchoption">
			<table>
				<tr>
					<td><a href="#" onclick="picsel()"> <!-- <img id="pics" name="unselect" src="image/picture.png" />-->
					</a></td>
					<td><input id="cfb" type="checkbox" /> <input id="cgp" type="checkbox" /> <input id="ctw" type="checkbox" /></td>
					<td><label id="lfb" for="cfb" onclick="chk(this)"></label></td>
					<td><label id="lgp" for="cgp" onclick="chk(this)"></label></td>
					<td><label id="ltw" for="ctw" onclick="chk(this)"></label></td>
					<td>
						<button type="submit" class="postsearch">Send</button>
					</td>
				</tr>
			</table>
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
		<!--end of search option-->
	</ul>
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
					<!-- start of a message-->

					<c:forEach var="status" items="${statusList}" varStatus="s">

						<li id="msg${s.index }">
							<table width="390px">
								<tr>
									<td class="picc" width="50px"><c:if test="${not empty status.userImage}">
											<img src="<c:url value="${status.userImage}" />" />
										</c:if> <c:if test="${ empty status.userImage}">
											<s:message code="default.png" var="defaultUrl" />
											<img src="<c:url value="${defaultUrl}" />" />
										</c:if></td>
									<td id="shortc1" class="words" width="300px"><span> ${status.content } </span> <c:if test="${not empty status.picture}">
											<img src="<c:url value="${status.picture}" />" />
										</c:if></td>
									<c:if test="${status.stared==true }">
										<td width="25px"><a class="marked"></a></td>
									</c:if>
									<c:if test="${status.stared==false }">
										<td width="25px"><a class="mark"></a></td>
									</c:if>
									<c:if test="${s.index==0}">
										<td id="arro${s.index}" class="arrowed" width="15px" rowspan="2">
									</c:if>
									<c:if test="${s.index!=0}">
										<td id="arro${s.index}" class="arrow" width="15px" rowspan="2">
									</c:if>
									<a onclick="showdetail(${s.index})"></a>
									</td>
								</tr>
								<tr>
									<td class="state" colspan="2">${status.fromUser}. ${status.createdTime}. <a href="">share</a>.<a href="">reply</a>.<a
										href="javascript:void(0)" id="m${s.index}" onclick="showtagf('m${s.index}')">tag</a>.<a href="">delete</a>.
									</td>
									<td class="source"><a href="#"> <s:message code="${status.serviceProvider}.png" var="iconUrl" /> <img
											src="<c:url value="${iconUrl}" />" />
									</a></td>
								</tr>
							</table>
						</li>
					</c:forEach>



					<!--end of a message-->

					<li>
						<!-- use to be the border-->
					</li>
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
<div id="tagdiv" class="tagfunc">
	<input type="hidden" value="no" id="lastid">
	<li>Commonly used tags: <a>Apple</a> <a>Brother</a></li>
	
	<li class="spiltline"></li>
	<li>Tags you wants: <input type="text" />
	</li>
</div>
<!-- end of hidden tag div -->

</div>