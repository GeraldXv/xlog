// JavaScript Document

function popaut()
{
	document.all.autbg.style.width = document.body.clientWidth;
	document.all.autbg.style.height = document.body.clientHeight;
	document.all.autbg.style.display = "block";
	document.all.autwin.style.display = "block";
}


function    locking(){
//document.all.ly.style.display="block";
document.getElementById("ly").style.cssText = "display:block;background-color:#777;width:"+document.body.clientWidth+"px;height:"+document.body.clientHeight+"px;";
//document.getElementById("ly").style.width=document.body.clientWidth;
//document.all.ly.style.height=document.body.clientHeight;alert(document.getElementById("ly").style.width);

}

function closep()
{
	document.all.autwin.style.display = "none";
	document.all.autbg.style.display = "none";
}
