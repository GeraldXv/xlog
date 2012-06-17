<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/message.css" />" />
<script type="text/javascript" src="<c:url value="/resources/js/messageC.js" />"></script>

<span><button id="nmsg" onclick="newmsg()">New Message</button></span>
<div class="mess">
	<ul class="frielist">
		<input id="cli" type="hidden" value="1" />
		<c:forEach var="friend" items="${friendList}">
			<c:if test="${friend.fromName == fromUser}">
				<li id="li1" class="sel">
			</c:if>
			<c:if test="${friend.fromName != fromUser}">
				<li id="li1">
			</c:if>
			<a id="p1" for="li1" href="<c:url value="/message/${friend.fromName}" />" title="1">
				<ul class="pic">
					<c:if test="${not empty friend.fromProfileImage}">
						<img src="<c:url value="${friend.fromProfileImage}" />" />
					</c:if>
					<c:if test="${ empty friend.fromProfileImage}">
						<s:message code="default.png" var="defaultUrl" />
						<img src="<c:url value="${defaultUrl}" />" />
					</c:if>
				</ul>
				<ul>
					<li id="na1" class="name"><c:out value="${friend.fromName}"></c:out></li>
					<li><c:out value="${friend.createdDate}"></c:out></li>
				</ul>
				<ul class="source">
					<s:message code="${friend.serviceProvider}.png" var="iconUrl" />
					<img src="<c:url value="${iconUrl}" />" />

				</ul>
			</a>
			</li>
		</c:forEach>


	</ul>
	<ul class="spl"></ul>
	<ul class="meslist">
		<li class="send">Send <label id="cselp"><c:out value="${fromUser}"></c:out></label> a message <br /> <input type="text" />
			<button>Send</button>
		</li>

		<c:forEach var="message" items="${messages}">
			<li><a href="javascript:void(0)" onclick="expmsg(this)">
					<ul class="pic">
						<c:if test="${not empty message.fromProfileImage}">
							<img src="<c:url value="${message.fromProfileImage}" />" />
						</c:if>
						<c:if test="${ empty message.fromProfileImage}">
							<s:message code="default.png" var="defaultUrl" />
							<img src="<c:url value="${defaultUrl}" />" />
						</c:if>
					</ul>
					<ul>
						<li class="name"><c:out value="${message.fromName}"></c:out><label><c:out value="${message.createdDate}"></c:out></label></li>
						<li class="clo"><c:out value="${message.content}"></c:out></li>
					</ul>
			</a></li>
		</c:forEach>
	</ul>
</div>
<c:url var="sendMessageUrl" value="/message/send/twitter" />
<sf:form action="${sendMessageUrl}" method="post" modelAttribute="messageForm">
	<div id="msgb1" class="msgb">
		<ul>
			<li>Message Via: <input class="hidrdo" type="checkbox" id="gmm" name="service" onchange="selsrv2()" /><label id="ptw" for="twm"></label><label
				id="glgm" for="gmm"></label><input type="hidden" id="csels" value="t" />
			</li>
			<li>To:<sf:input path="to" type="text" />
			</li>
			<li class="splt"></li>
			<li><sf:textarea path="text"></sf:textarea></li>
			<li>
				<button type="submit" onclick="sendmsg()">Send</button>
				<button onclick="cancelmsg()">Cancel</button>
			</li>
		</ul>
	</div>
</sf:form>

<c:url var="sendMessageUrl" value="/message/send/gmail" />
<sf:form action="${sendMessageUrl}" method="post" modelAttribute="messageForm">
	<div id="msgb2" class="msgb">
		<ul>
			<li>Message Via: <input class="hidrdo" type="checkbox" id="twm" name="service" onchange="selsrv1()" /><label id="gltw" for="twm"></label><label
				id="pgm" for="gmm"></label>
			</li>
			<li>To:<sf:input type="text" path="to" />
			</li>
			<li id="gmsubject">Subject:<sf:input type="text" path="subject" />
			</li>
			<li class="splt"></li>
			<li><sf:textarea path="text"></sf:textarea></li>
			<li>
				<button type="submit" onclick="sendmsg()">Send</button>
				<button type="button" onclick="cancelmsg()">Cancel</button>
			</li>
		</ul>
	</div>
</sf:form>

</div>
