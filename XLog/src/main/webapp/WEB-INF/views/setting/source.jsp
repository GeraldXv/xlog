<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>

<div class="Settings">
	<ul>
		<li><a id="ssource" href="<c:url value="/source/" />">SOURCES</a> <a href="<c:url value="/account/" />">ACCOUNT</a> <a
			href="<c:url value="/profile/" />">&nbsp;PROFILE&nbsp;</a></li>
		<li class="splitline"></li>
	</ul>
</div>
<div id="ssource1" class="ssource">
	<ul>
		<li>Add Source<br />
			<ul>

				<c:forEach var="providerId" items="${providerIds}">
					<li><form name="${providerId}form" method="POST" action="<c:url value="/connect/${providerId}" />">
							<c:if test="${providerId=='google'}">
								<input type="hidden" name="scope"
									value="https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/tasks https://www-opensocial.googleusercontent.com/api/people  https://mail.google.com/mail/feed/atom/ https://www.google.com/m8/feeds/ https://www-opensocial.googleusercontent.com/api/people" />
							</c:if>
							<c:if test="${providerId=='facebook'}">
								<input type="hidden" name="scope" value="publish_stream,user_photos,offline_access,read_stream" />
							</c:if>
							<c:set var="connections" value="${connectionMap[providerId]}" />
							<s:message code="${providerId}.displayName" var="providerDisplayName" />
							<s:message code="${providerId}.png" var="iconUrl" />
							<c:if test="${empty connections}">
								<img src="<c:url value="${iconUrl}" />" />
								<br />
								<a href="javascript:document.${providerId}form.submit()"> <img src="<c:url value="/resources/image/addbutton.png" />" />
								</a>
							</c:if>
						</form></li>
				</c:forEach>

				<c:if test="${isGmailconnected==false}">
					<s:message code="gmail.png" var="iconUrl" />
					<li><img src="<c:url value="${iconUrl}" />" /><br /> <a href="#" onclick="popaut()"><img
							src="<c:url value="/resources/image/addbutton.png" />" /> </a></li>
				</c:if>

			</ul>
		</li>
		<li>Delete Source<br />
			<ul>
				<c:forEach var="providerId" items="${providerIds}">
					<li><form name="${providerId}formdelete" method="POST" action="<c:url value="/connect/${providerId}" />">
							<input type="hidden" name="_method" value="delete" />
							<c:set var="connections" value="${connectionMap[providerId]}" />
							<s:message code="${providerId}.displayName" var="providerDisplayName" />
							<s:message code="${providerId}.png" var="iconUrl" />
							<c:if test="${not empty connections}">
								<img src="<c:url value="${iconUrl}" />" />
								<br />
								<a href="javascript:document.${providerId}formdelete.submit()"> <img src="<c:url value="/resources/image/delbutton.png" />" />
								</a>
							</c:if>
						</form></li>
				</c:forEach>
				<c:if test="${isGmailconnected==true}">
					<s:message code="gmail.png" var="iconUrl" />
					<li><img src="<c:url value="${iconUrl}" />" /><br /> <a href="#" onclick="delacc()"> <img
							src="<c:url value="/resources/image/delbutton.png" />" /></a></li>
				</c:if>
			</ul>
		</li>
	</ul>
</div>


<!-- popup window for authorization-->

<div id="autbg" class="lyly"></div>
<div id="autwin" class="autwin">
	<form>
		<ul>
			<li style="height: 1px"></li>
			<li>To authorize your account, please enter your information.</li>
			<li class="spl"></li>
			<li><label><b>User Name:</b></label><input type="text" /></li>
			<li><label><b>Password:</b></label><input type="password" /></li>
			<li>
				<button type="submit">
					<b>Confirm</b>
				</button>
				<button type="button" onclick="closep()">
					<b>Cancel</b>
				</button>
			</li>
		</ul>
	</form>
</div>

<!-- window of delete confirm -->
<div id="delcon" class="delcon" onmouseup="up()" onmousemove="movepos(ev)" onmousedown="down()">
	<form>
		<input type="hidden">
		<ul>
			<li>Are you sure you want to delete this source from your X-Log?</li>
			<li>
				<button type="button" onclick="concli('ca')">
					<b>Cancel</b>
				</button>
				<button type="submit" onclick="concli('co')">
					<b>Confirm</b>
				</button>
			</li>
		</ul>
	</form>
</div>
