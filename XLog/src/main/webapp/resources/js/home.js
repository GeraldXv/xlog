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

function showtagf(id,idas,statusid,fromuser)
{
	var pos = getElementPos(id);
	document.all.tagdiv.style.pixelTop = (pos.y + 13);
	document.all.tagdiv.style.pixelLeft = (pos.x - 100);
	
	if(document.getElementById("lastid").value == id&&document.all.tagdiv.style.display == "block")	
	{
		document.all.tagdiv.style.display="none";
	}
	else 
	{
		
		var xmlhttp = xmlhttpgenerator();
		
		xmlhttp.onreadystatechange = function () {
			if(xmlhttp.readyState == 4){
				if(xmlhttp.status == 200){
					var json = eval('(' + xmlhttp.responseText + ')');
					document.getElementById("sug1").innerHTML = json.tag1;
					document.getElementById("sug2").innerHTML = json.tag2;
					document.getElementById("tagbutton").id = statusid;
				}
			}
		}
		
		xmlhttp.open("GET","/XLog/status/showTag?idAtService="+idas+"&fromUser="+fromuser,true);
		
		xmlhttp.send(null);
		
		document.all.tagdiv.style.display="block";
	}
	document.getElementById("lastid").value = id;
}

function tagit(ob)
{
	id = ob.id;

	var xmlhttp = xmlhttpgenerator();
	
	xmlhttp.onreadystatechange = function () {
		if(xmlhttp.readyState == 4){
			if(xmlhttp.status == 200){
				if(xmlhttp.responseText == "true")
				{
					document.getElementById(id).id = "tagbutton";
					document.all.tagdiv.style.display="none";
				}
			}
		}
	}

	xmlhttp.open("GET","/XLog/status/addTag?statusId="+id+"&tag="+document.getElementById("taginput").value,true);
	
	xmlhttp.send(null);
	
	//document.all.tagdiv.style.display="none";
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
{
	t = document.getElementById("currentexp").name;
	if(id != t||t == "none")
	{
		document.getElementById("arro"+id).className = "arrowed";
		if(t != "none")
			document.getElementById("arro"+t).className = "arrow";
		document.getElementById("currentexp").name = id;
		document.getElementById("detailp").innerHTML = document.getElementById("msg"+id).innerHTML;
		
		
		
		document.getElementById("dcont").innerHTML = "<h5>Sent</h5>:"+document.getElementById("time"+id).innerHTML+"<br /><h5>To</h5>: "+document.getElementById("name"+id).innerHTML+" <br />"+document.getElementById("shortc"+id).innerHTML;
	}
} 

function chgmark(ob)
{
	var xmlhttp = xmlhttpgenerator();
	
	if(ob.className == "marked")
	{
		wname = "mark";
		ismarked = "true";
	}
	else
	{
		wname = "marked";
		ismarked = "false";
	}
	sid = ob.parentNode.parentNode.parentNode.parentNode.parentNode.title;
	
	xmlhttp.onreadystatechange = function () {
		if(xmlhttp.readyState == 4){
			if(xmlhttp.status == 200){
				var resbonseText = xmlhttp.responseText;
				if(resbonseText == "true") {
					ob.className = wname;
				}
			}
		}
		else document.getElementById("test").innerHTML = xmlhttp.readyState;
		
	}
	
	xmlhttp.open("GET","/XLog/status/mark?statusId="+sid+"&isMarked="+ismarked,true);
	
	xmlhttp.send(null);
}

function filterSpaceNode(nodes) 
{  
    var arr = [];  
    for(var i=0;i<nodes.length;i++) {
            if (nodes[i].nodeType ==3 && /^\s/.test(nodes[i].nodeValue)) {  
            	continue;  
            }  
        if(nodes[i].tagName == "LI")
        	arr.push(nodes[i]);
    }  alert(arr.length);
    return arr;  
} 

function xmlhttpgenerator()
{
	var xmlhttp;
	if(window.XMLHttpRequest)
	{
	      //FireFox,Mozillar,Opera,Safari,IE7,IE8
	       xmlhttp = new XMLHttpRequest();
	       
	       //mozillar bug
	       if(xmlhttp.overrideMineType){
	          xmlhttp.overrideMineType("text/xml");
	       }
	}
	else if(window.ActiveXObject)
	{
	       //IE5，IE5.5，IE6
	       
	       var activexName = ["MSXML2.XMLHTTP","Microsoft.XMLHTTP"];
	       for(var i = 0; i<activeName.length; i++){
	           try{
	              xmlhttp = new ActiveXObject(activexName[i]);
	              break;
	           }catch(e){}
	       }
	}
	return xmlhttp;
}

function delmsg(ob)
{
	obj = ob.parentNode.parentNode.parentNode.parentNode.parentNode;
	mainbody = obj.parentNode;
	oid = obj.id.substring(3);
	oname = obj.title;
	
	var xmlhttp = xmlhttpgenerator(); 
	
	xmlhttp.onreadystatechange = function () {
		if(xmlhttp.readyState == 4){
			if(xmlhttp.status == 200){
				var resbonseText = xmlhttp.responseText;
				
				if(resbonseText == "true") {
					mainbody.removeChild(obj);
					nodes = filterSpaceNode(mainbody.childNodes);
					if(oid == document.getElementById("currentexp").name)
					{
						id = nodes[0].id.substring(3);
						document.getElementById("arro"+id).className = "arrowed";
						document.getElementById("currentexp").name = id;
						document.getElementById("detailp").innerHTML = document.getElementById("msg"+id).innerHTML;
						document.getElementById("dcont").innerHTML = document.getElementById("shortc"+id).innerHTML;
					}
					if(nodes.length == 1)
					{
						document.getElementById("nomsg").style.display = "block";
					}
					else alert(nodes.length);
				}
			}
		}
		
	}
	
	xmlhttp.open("GET","/XLog/status/delete?statusId="+oname,true);
	
	xmlhttp.send(null);
	
}

function chgsize(ob)
{
	image = new Image();
	image.src = ob.src;
	if(ob.className == "")
	{
		if(image.width > 270)
		{
			ob.className = "max";
		}
		else 
		{
			ob.className = "exp";
		}
	}
	else
	{
		ob.className = "";
	}
}