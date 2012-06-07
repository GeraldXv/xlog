<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Your GooglePlus Profile</h3>
<div class="row">
			<div class="span8 columns">
				<div class="clearfix">
					<a href="${profile.link}" target="_blank">Google Profile Page</a>
				</div>
				<form>
					<div class="clearfix">
						<label>Profile ID:</label>
						<div class="input">
							<span class="uneditable-input">${profile.id}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>E-mail:</label>
						<div class="input">
							<span class="uneditable-input">${profile.email}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>Display Name:</label>
						<div class="input">
							<span class="uneditable-input">${profile.name}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>First Name:</label>
						<div class="input">
							<span class="uneditable-input">${profile.firstName}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>Last Name:</label>
						<div class="input">
							<span class="uneditable-input">${profile.lastName}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>Gender:</label>
						<div class="input">
							<span class="uneditable-input">${profile.gender}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>Locale:</label>
						<div class="input">
							<span class="uneditable-input">${profile.locale}</span>
						</div>
					</div>
				</form>
			</div>
			<div class="span8 columns">
				<div>Profile Picture</div>
				<img src="${profile.profilePictureUrl}" />
			</div>
		</div>
	</div>
