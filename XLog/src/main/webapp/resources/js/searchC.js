// JavaScript Document

function categchg(id)
{
	var t = document.getElementById("catg").value;
		if(t != id)
		{
			//alert(document.getElementById("c"+t).style.color);
			document.getElementById(id).style.color = "#F00";
			document.getElementById(t).style.color = "#000";
			document.getElementById("catg").value = id;
		}
}

function timchg(id)
{
	var t = document.getElementById("tims").value;
		if(t != id)
		{
			//alert(document.getElementById("c"+t).style.color);
			document.getElementById(id).style.color = "#F00";
			document.getElementById(t).style.color = "#000";
			document.getElementById("tims").value = id;
		}
}

function advs()
{
		if(document.getElementById("advsr").style.display == "none")
		document.getElementById("advsr").style.display = "block";
		else 
		document.getElementById("advsr").style.display = "none";
}