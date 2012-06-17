// JavaScript Document

function newmsg()
{
	if(document.getElementById("msgb1").style.display != "block"&&document.getElementById("msgb2").style.display != "block")
		document.getElementById("msgb1").style.display = "block";
	else
	{
		document.getElementById("msgb1").style.display = "none";
		document.getElementById("msgb2").style.display = "none";
	}
}

function sendmsg()
{
	document.getElementById("msgb").style.display = "none";
}

function cancelmsg()
{
	document.getElementById("msgb1").style.display = "none";
	document.getElementById("msgb2").style.display = "none";
}

function chgps(id)
{
	if(document.getElementById("cselp").innerHTML != document.getElementById("na"+id).innerHTML)
	{
		document.getElementById("li"+id).className = "sel";
		document.getElementById("li"+document.getElementById("cli").value).className = "";
		document.getElementById("cli").value = id;
		document.getElementById("cselp").innerHTML = document.getElementById("na"+id).innerHTML;
	}
}

function selsrv1()
{
	document.getElementById("msgb2").style.display = "none";
	document.getElementById("msgb1").style.display = "block";
	document.getElementById("msgb1").style.height = "205px";
}

function selsrv2()
{
	document.getElementById("msgb1").style.display = "none";
	document.getElementById("msgb2").style.display = "block";
	document.getElementById("msgb2").style.height = "235px";	
}

function expmsg(ob)
{
	obj = ob.childNodes[3].childNodes[3];
	if(obj.className == "clo")
	obj.className = "exp";
	else 
	obj.className = "clo";
}