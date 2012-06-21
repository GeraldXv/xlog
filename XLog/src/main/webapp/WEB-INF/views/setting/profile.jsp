<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>


<div class="Settings">
	<ul>
		<li><a href="<c:url value="/source/" />">SOURCES</a> <a href="<c:url value="/account/" />">ACCOUNT</a> <a id="sprofile"
			href="<c:url value="/profile/" />">&nbsp;PROFILE&nbsp;</a></li>
		<li class="splitline"></li>
	</ul>
</div>

<!-- end of setting title -->

<div id="sprofile1" class="sprofile">
	<table>
		<tr>
			<td width="160px"></td>
			<td width="180px"></td>
			<td colspan="2" width="560px"></td>
		</tr>
		<tr height="20px">
			<td></td>
			<td></td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td><b>First Name:</b></td>
			<td colspan="3"><input type="text" /></td>
		</tr>
		<tr>
			<td><b>Last Name:</b></td>
			<td colspan="3"><input type="text" /></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="3">
				<button type="button">Save Changes</button>
			</td>
		</tr>
		<tr height="20px">
			<td></td>
			<td></td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td><b>New Password:</b></td>
			<td><input id="psw1" type="password" onkeyup="vpsw1()" /></td>
			<td width="20px"><img id="pswp1" /></td>
			<td width="540px"><label>If you would like to change the password, type a new one. Otherwise, leave this blank.</label></td>
		</tr>
		<tr>
			<td></td>
			<td><input id="psw2" type="password" onkeyup="vpsw2()" /></td>
			<td><img id="pswp2" /></td>
			<td><label>Type your new password again.</label></td>
		</tr>
		<tr>
			<td></td>
			<td><label id="sind">0%</label></td>
			<td></td>
			<td><label>(Hint: The password should be at least seven characters long. To make it stronger, user upper and lower case
					letters, numbers and symbols like ! $ # &.) </label></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="3">
				<button type="button">Save Changes</button>
			</td>
		</tr>
		<tr height="40px">
			<td></td>
			<td></td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="4"><a id="delacc" href="javascript:void(0)" onclick="delacc()"><b>Delete your X-Log Account</b></a></td>
		</tr>
	</table>
</div>

<!-- window of delete confirm -->
<div id="delcon" class="delcon" onmouseup="up()" onmousemove="movepos(ev)" onmousedown="down()">
	<input type="hidden">
	<ul>
		<li>Are you sure you want to delete your X-Log account <b>totally</b>?
		</li>
		<li>
			<button type="button" onclick="concli('ca')">
				<b>Cancel</b>
			</button>
			<button type="button" onclick="concli('co')">
				<b>Confirm</b>
			</button>
		</li>
	</ul>
</div>

<!-- end of profile edit -->
