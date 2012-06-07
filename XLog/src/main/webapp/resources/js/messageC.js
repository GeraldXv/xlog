// JavaScript Document

function newmsg()
{
	if(document.getElementById("msgb").style.display != "block")
		document.getElementById("msgb").style.display = "block";
	else 
		document.getElementById("msgb").style.display = "none";
}

function sendmsg()
{
	document.getElementById("msgb").style.display = "none";
}

function cancelmsg()
{
	document.getElementById("msgb").style.display = "none";
}

function chgps(id)
{
	if(document.getElementById("cselp").innerHTML != document.getElementById("na"+id).innerHTML){
		document.getElementById("li"+id).className = "sel";
		document.getElementById("li"+document.getElementById("cli").value).className = "";
		document.getElementById("cli").value = id;
		document.getElementById("cselp").innerHTML = document.getElementById("na"+id).innerHTML;}
}

function selsrv(ob)
{
	j = ob.id.substr(0,1);
	if(j == 't')
	{
		document.getElementById("gltw").id = "ptw";
		document.getElementById("pgm").id = "glgm";
		document.getElementById("gmsubject").style.display = "none";
		document.getElementById("msgb").style.height = "205px";
	}
	else if (j == 'g')
	{
		document.getElementById("glgm").id = "pgm";
		document.getElementById("ptw").id = "gltw";
		document.getElementById("gmsubject").style.display = "block";
		document.getElementById("msgb").style.height = "235px";	
	}
}