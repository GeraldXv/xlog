<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
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
			<td colspan="4" class="bnn"><input type="checkbox">FB</input> <input type="checkbox">Google+</input> <input type="checkbox">Twitter</input>
				<input type="checkbox">Gmail</input></td>
		</tr>
		<tr>
			<td></td>
			<td>File Type:</td>
			<td colspan="4" class="bnn"><input type="checkbox">Received</input> <input type="checkbox">Sent</input></td>
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
			<td colspan="4"><input class="Wdate" type="text" onClick="WdatePicker()"> --<input class="Wdate" type="text"
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