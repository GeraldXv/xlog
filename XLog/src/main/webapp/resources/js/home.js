// JavaScript Document
//indexcontrol


function clicktofinish(id)
{
	document.getElementById("taginput").value = document.getElementById(id).innerHTML;
}

function picsel()
{
	if(document.getElementById("pics").name == "selected") 
	{
		document.getElementById("pics").src = "icons/picture.png";
		document.getElementById("pics").name = "unselect";
	}
	else
	{
		document.getElementById("pics").src = "icons/image.png";
		document.getElementById("pics").name = "selected";
	}
}

function overff(id)
{
	document.getElementById(id).src = "icons/fastforward.png";
}

function backun(id)
{
	if(document.getElementById("currentexp").name != id)
	document.getElementById(id).src = "icons/fastforwardun.png";
}

function mark(id)
{
	if(document.getElementById(id).name == "like") 
	{
		document.getElementById(id).src = "icons/unmark.png";
		document.getElementById(id).name = "unlike";
	}
	else
	{
		document.getElementById(id).src = "icons/marked.png";
		document.getElementById(id).name = "like";
	}
}

function chgstate(id)
{
	t = document.getElementById(id).src.substr(-5,1);
	if(t == 1)
	{
		document.getElementById(id).src = document.getElementById(id).src.slice(0,-5)+"0.png";
		document.getElementById("c1").src = document.getElementById("c1").src.slice(0,-5)+"1.png";
		document.getElementById("currentcategory").name = "c1";
	}
	else
	{ 
		document.getElementById(id).src = document.getElementById(id).src.slice(0,-5)+"1.png";
		document.getElementById(document.getElementById("currentcategory").name).src = document.getElementById(document.getElementById("currentcategory").name).src.slice(0,-5)+"0.png";
		document.getElementById("currentcategory").name = id;
	}
}

function showtagf(id)
{
	var pos = getElementPos(id);
	document.all.tagdiv.style.pixelTop = (pos.y + 13);
	document.all.tagdiv.style.pixelLeft = (pos.x - 100);
	
	if(document.getElementById("lastid").value == id&&document.all.tagdiv.style.display == "block")	
	{
		document.all.tagdiv.style.display="none";
	}
		else document.all.tagdiv.style.display="block";
	document.getElementById("lastid").value = id;
}

function getElementPos(elementId) {
 var ua = navigator.userAgent.toLowerCase();
 var isOpera = (ua.indexOf('opera') != -1);
 var isIE = (ua.indexOf('msie') != -1 && !isOpera); // not opera spoof
 var el = document.getElementById(elementId);
 if(el.parentNode === null || el.style.display == 'none') {
  return false;
 }      
 var parent = null;
 var pos = [];     
 var box;     
 if(el.getBoundingClientRect)    //IE
 {         
  box = el.getBoundingClientRect();
  var scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
  var scrollLeft = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);
  return {x:box.left + scrollLeft, y:box.top + scrollTop};
 }else if(document.getBoxObjectFor)    // gecko    
 {
  box = document.getBoxObjectFor(el); 
  var borderLeft = (el.style.borderLeftWidth)?parseInt(el.style.borderLeftWidth):0; 
  var borderTop = (el.style.borderTopWidth)?parseInt(el.style.borderTopWidth):0; 
  pos = [box.x - borderLeft, box.y - borderTop];
 } else    // safari & opera    
 {
  pos = [el.offsetLeft, el.offsetTop];  
  parent = el.offsetParent;     
  if (parent != el) { 
   while (parent) {  
    pos[0] += parent.offsetLeft; 
    pos[1] += parent.offsetTop; 
    parent = parent.offsetParent;
   }  
  }   
  if (ua.indexOf('opera') != -1 || ( ua.indexOf('safari') != -1 && el.style.position == 'absolute' )) { 
   pos[0] -= document.body.offsetLeft;
   pos[1] -= document.body.offsetTop;         
  }    
 }              
 if (el.parentNode) { 
    parent = el.parentNode;
   } else {
    parent = null;
   }
 while (parent && parent.tagName != 'BODY' && parent.tagName != 'HTML') { // account for any scrolled ancestors
  pos[0] -= parent.scrollLeft;
  pos[1] -= parent.scrollTop;
  if (parent.parentNode) {
   parent = parent.parentNode;
  } else {
   parent = null;
  }
 }
 return {x:pos[0], y:pos[1]};
}

function chk(ob)
{ 
	j = ob.id.substr(-1,1);
	if (j == 'c')
	ob.id = ob.id.substring(0,3);
	else if(j != "c")
	ob.id += "c";
}

function showdetail(id)
{alert(id);
	t = document.getElementById("currentexp").name;
	if(id != t||t == "none")
	{
		document.getElementById("arro"+id).className = "arrowed";
		if(t != "none")
			document.getElementById("arro"+t).className = "arrow";
		document.getElementById("currentexp").name = id;
		document.getElementById("detailp").innerHTML = document.getElementById("msg"+id).innerHTML;
		document.getElementById("dcont").innerHTML = document.getElementById("shortc"+id).innerHTML;
	}
}

function delmsg(ob)
{
	obj = ob.parentNode.parentNode.parentNode.parentNode.parentNode;
	mainbody = obj.parentNode;
	//obj.style.display = "none";
	oid = obj.id.substring(3);
	mainbody.removeChild(obj);
	if(oid == document.getElementById("currentexp").name)
	{
		id = mainbody.childNodes[4].id.substring(3);
		document.getElementById("arro"+id).className = "arrowed";
		document.getElementById("currentexp").name = id;
		document.getElementById("detailp").innerHTML = document.getElementById("msg"+id).innerHTML;
		document.getElementById("dcont").innerHTML = document.getElementById("shortc"+id).innerHTML;
	}
}