// JavaScript Document

function popupadvs()
{
	document.all.advsr.style.display="block";
}

function closeadvs()
{
	document.all.advsr.style.display="none";
}

function popsetting(e)
{
	document.all.unrwin.style.display="none";
	if(document.all.setwin.style.display=="block")
		document.all.setwin.style.display="none";	
	else
		document.all.setwin.style.display="block";
	
	if ( e && e.stopPropagation )
        e.stopPropagation();
    else
        window.event.cancelBubble = true;

}

function popunr(e)
{
	document.all.setwin.style.display="none";	
	if(document.all.unrwin.style.display=="block")
		document.all.unrwin.style.display="none";	
	else
		document.all.unrwin.style.display="block";
		
	if ( e && e.stopPropagation )
        e.stopPropagation();
    else
        window.event.cancelBubble = true;
}

function closepopup()
{
		document.all.setwin.style.display="none";	
		document.all.unrwin.style.display="none";	
}

function test()
{
	alert("test");
	}