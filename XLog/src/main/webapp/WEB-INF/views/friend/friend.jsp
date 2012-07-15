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
					<c:if test="${ providerId == 'facebook' || empty providerId }">
						<a id="sel" href="<c:url value="/friend/facebook?page=1" />" >Facebook</a>
					</c:if>
					<c:if test="${ providerId != 'facebook' }">
						<a href="<c:url value="/friend/facebook?page=1" />" >Facebook</a>
					</c:if>
					<c:if test="${ providerId != 'twitter' }">					
						<a href="<c:url value="/friend/twitter?page=1" />" >Twitter</a>
					</c:if>
					<c:if test="${ providerId == 'twitter' }">					
						<a id="sel"  href="<c:url value="/friend/twitter?page=1" />" >Twitter</a>
					</c:if>
					<c:if test="${ providerId != 'gmail' }">				
        				<a href="<c:url value="/friend/gmail?page=1" />" >Gmail</a>
					</c:if>
					<c:if test="${ providerId == 'gmail' }">				
        				<a id="sel" href="<c:url value="/friend/gmail?page=1" />" >Gmail</a>
					</c:if>
					<c:if test="${ providerId != 'google' }">				
        				<a href="<c:url value="/friend/google?page=1" />" >Google+</a>
					</c:if>
					<c:if test="${ providerId == 'google' }">				
        				<a id="sel" href="<c:url value="/friend/google?page=1" />" >Google+</a>
					</c:if>		
        </li>
		<li class="splitline">
		</li>
	</ul>
    <ul class="flist">
    	<li>
        	<input type="text" /><button><img src="<c:url value="/resources/image/search.png" />" width="25" height="25" /></button>
        </li>
        <c:forEach var="friend" items="${friends }">
        	<li>
        		<a><img src="<c:url value="${friend.imageUrl }" />" /></a><a>${friend.screenName }</a><c:if test="${friend.gender == 'female' }"><img src="<c:url value="/resources/image/female.png" />" /></c:if><c:if test="${friend.gender == 'male' }"><img src="<c:url value="/resources/image/male.png" />" /></c:if>
        	</li>
        </c:forEach>
    </ul>
</div>

<!-- end of setting title -->