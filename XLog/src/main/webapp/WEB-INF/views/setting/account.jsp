<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>


<div class="Settings">
	<ul>
		<li>
        <a href="addsources.html" >SOURCES</a>
        <a id="saccount" href="account.html" >ACCOUNT</a>
        <a href="Profile.html" >&nbsp;PROFILE&nbsp;</a>
		</li>
		<li class="splitline"></li>
	</ul>
</div>

<!-- end of setting title -->

<div id="saccount1" class="saccount">
	<table>
    	<tr>
        	<td width="140px">
            	<b>Picture</b>
            </td>
            <td width="760px">
            	<ul class="seled" onclick="selpic(this)">
                	<li>
                    	<img src="blue-16.png" />
                    </li>
                    <li>Facebook</li>
                </ul>
            	<ul onclick="selpic(this)">
                	<li>
                    	<img src="blue-16.png" />
                    </li>
                    <li>Twitter</li>
                </ul>
            	<ul onclick="selpic(this)">
                	<li>
                    	<img src="blue-16.png" />
                    </li>
                    <li>Google+</li>
                </ul>
            </td>
        </tr>
    	<tr>
        	<td>
            	<b>Time Zone</b>
            </td>
            <td>
            	<li>
                	<label id="lage" class="radioOn" for="radioe" onclick="lagsel('e')">English</label><input id="radioe" type="radio" name="lag" value="english" checked="checked" />
                </li>
                <li>
                	<label id="lagc" class="radioOff" for="radioc" onclick="lagsel('c')">汉语</label><input id="radioc" type="radio" name="lag" value="chinese" />
                </li>
                <li>
                	<button type="button">Save Changes</button>
                </li>
            </td>
        </tr>
    	<tr>
        	<td>
            	<b>Page Form</b>
            </td>
            <td>
            	<li>A page show at most <input id="range" type="range" min="5" max="25" value="5" onchange="chgrange()"> <span id="ragval">5</span> posts.
               	</li>
                <li>
                	<button type="button" onclick="test()">Save Changes</button>
                </li>
            </td>
        </tr>
    </table>
</div>

<!-- end of acount edit -->

<!-- window of delete confirm -->
<div id="delcon" class="delcon" onmouseup="up()" onmousemove="movepos(ev)" onmousedown="down()">
	<input type="hidden">
	<ul>
    	<li>
        	Are you sure you want to delete your X-Log account <b>totally</b>? 
        </li>
    	<li>
        	<button type="button" onclick="concli('ca')"><b>Cancel</b></button><button type="button" onclick="concli('co')"><b>Confirm</b></button>
        </li>
    </ul>
</div>

<!-- end of profile edit -->

