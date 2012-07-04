<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>X-LOG Login</title>
<s:message code="favicon.ico" var="icoUrl" />
<link rel="shortcut icon" href="<c:url value="${icoUrl}" />" type="image/x-icon">
<link rel="stylesheet" href="<c:url value="/resources/css/register.css" />" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/resources/css/footer.css" />" type="text/css" media="screen" />
<script type="text/javascript" src="<c:url value="/resources/js/headerC.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/register.js" />"></script>
</head>

<body>
	<form id="signin" action="<c:url value="/signin/authenticate" />" method="post">
		<div class="header">
			<table width="950px" height="80px">
				<tr>
					<td width="20px"></td>
					<td width="200px"></td>
					<td width="280px"></td>
					<td width="20px"></td>
					<td width="160px"></td>
					<td width="180px"></td>
					<td width="90px"></td>
				</tr>
				<tr>
					<td rowspan="3"></td>
					<td rowspan="3"><a href="#"> <img id="title" src="<c:url value="/resources/image/title.png" />">
					</a></td>
					<td rowspan="3"></td>
					<td colspan="2">UserName</td>
					<td>Password</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2"><input id="login" name="j_username" type="text" /></td>
					<td><input id="password" name="j_password" type="password" /></td>
					<td>
						<button type="submit">Log In</button>
					</td>
				</tr>
				<tr>
					<td>
						<li><input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true" size="width:20px" /></li>
					</td>
					<td>
						<li><label for="_spring_security_remember_me">Remember Me?</label></li>
					</td>
					<td><a href="#">Forgot your password?</a></td>
					<td></td>
				</tr>
			</table>
		</div>
	</form>
	<div class="rega">
		<ul>
			<li>
				<div class="reser">
					<ul>
						<img src="<c:url value="/resources/image/coverpicture.png"/>" />
					</ul>
				</div>
			</li>
			<li>
				<div class="register">
					<c:url var="singupUrl" value="/signup" />
					<sf:form action="${singupUrl}" method="post" modelAttribute="signupForm">
						<table>
							<tr>
								<td width="130px"><b>Sign Up</b></td>
								<td width="210px"></td>
								<td width="50px"></td>
							</tr>
							<tr>
								<td colspan="3">
									<li id="spl"></li>
								</td>
							</tr>
							<tr>
								<td><label>E-mail:</label></td>
								<td><sf:input path="email" name="email" type="text" onblur="vemail()" /></td>
								<td></td>
							</tr>
							<tr>
								<td><label>Username:</label></td>
								<td><sf:input path="username" id="username" type="text" /></td>
								<td></td>
							</tr>

							<tr>
								<td><label>Password:</label></td>
								<td><sf:input path="password" id="psw1" type="password" onkeyup="vpsw1()" /></td>
								<td><img id="pswp1T" src="<c:url value="/resources/image/T.png" />" /><img id="pswp1X"
									src="<c:url value="/resources/image/X.png" />" /></td>
							</tr>
							<tr>
								<td><label>Re-enter Password:</label></td>
								<td><input id="psw2" type="password" onkeyup="vpsw2()" /></td>
								<td><img id="pswp2T" src="<c:url value="/resources/image/T.png" />" /><img id="pswp2X"
									src="<c:url value="/resources/image/X.png" />" /></td>
							</tr>


							<tr height="50px">
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<button type="submit">Sign Up</button>
								</td>
								<td></td>
							</tr>
						</table>
					</sf:form>
				</div>
			</li>
		</ul>
	</div>

	<div class="bottom">
		<ul>
			<li class="line"></li>
		</ul>
		<ul>
			<li><a href="#">@X-log</a></li>
			<li><a href="#">About</a></li>
			<li><a href="#">Help</a></li>
		</ul>
	</div>
	<!-- end of bottom -->


</body>
</html>
