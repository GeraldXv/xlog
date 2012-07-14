<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/friend.css" />" />
<script type="text/javascript" src="<c:url value="/resources/js/friend.js" />"></script>

<div class="service">
	<ul>
		<li>
        <a id="sel" href="<c:url value="/facebook?page=1" />" >Facebook</a>
        <a href="<c:url value="/twitter?page=1" />" >Twitter</a>
        <a href="<c:url value="/gmail?page=1" />" >Gmail</a>
        <a href="<c:url value="/google?page=1" />" >Google+</a>
        </li>
		<li class="splitline">
		</li>
	</ul>
    <ul class="flist">
    	<li>
        	<input type="text" /><button><img src="image/Search.png" width="25" height="25" /></button>
        </li>
        <c:forEach var="friend" items="${friends }">
        	<li>
        		<a><img src="<c:url value="${friend.imageUrl }" />" /></a><a>${friend.name }</a><c:if test="${friend.gender == 'female' }"><img src="image/female.png" /></c:if><c:if test="${friend.gender == 'male' }"><img src="image/male.png" /></c:if>
        	</li>
        </c:forEach>
    </ul>
</div>

<!-- end of setting title -->