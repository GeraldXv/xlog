//setingc// JavaScript Document

function chgrange()
{	
	value = document.getElementById("range").value;
	if(value >= 10)
		document.getElementById("ragval").innerHTML = value;	
	else 
		document.getElementById("ragval").innerHTML = "&nbsp"+value;	
}

function selpic(ob)
{
	document.getElementsByClassName("seled").item(0).className = "";
	ob.className = "seled";	
}

function tagchg(id)
{
	var t = document.getElementById("tagg").value;
		if(t != id)
		{
			//alert(document.getElementById(id).value);
			document.getElementById(id).style.backgroundColor = "#0066FF";
			document.getElementById(t).style.backgroundColor = "#99CCFF";
			document.getElementById(document.getElementById("tagg").value+'1').style.display = "none";
			document.getElementById(id+'1').style.display = "block";
			document.getElementById("tagg").value = id;
		}
}

function lagsel(id)
{
		document.getElementById("lagc").className = "radioOff";
		document.getElementById("lage").className = "radioOff";
		document.getElementById("lag"+id).className = "radioOn";
}

function picsel(id)
{
		document.getElementById("picff").className = "radioOff";
		document.getElementById("picuu").className = "radioOff";
		document.getElementById("pic"+id).className = "radioOn";
}

function selpicfin(obj)
{
		if(obj) 
            { 
                //ie 
                if (window.navigator.userAgent.indexOf("MSIE")>=1) 
                { 
                    obj.select(); 
                    document.getElementById("picpath").value = document.selection.createRange().text; 
                } 
                //firefox 
                else if(window.navigator.userAgent.indexOf("Firefox")>=1) 
                { 
                    if(obj.files) 
                    { 
                        document.getElementById("picpath").value =  obj.files.item(0).getAsDataURL(); 
                    } 
                    document.getElementById("picpath").value =  obj.value; 
                } 
                document.getElementById("picpath").value =  obj.value; 
			}
}

function openl()
{
	alert("adf");
	document.getElementById("ddl").style.display = "block";
}

function selnum(no)
{
	document.getElementById("curnum").innerHTML = id;
	document.getElementById("ddl").style.display = "none";
}

function concli(t)
{
	if(t == "ca")
		document.getElementById("delcon").style.display = "none";
	if(t == "co")
		document.getElementById("delcon").style.display = "none";
}

function delacc()
{
		document.getElementById("delcon").style.display = "block";
}

function movepos()
{
	var point = {
		x:0,
		y:0
	};
 
	if(typeof window.pageYOffset != 'undefined') {
		point.x = window.pageXOffset;
		point.y = window.pageYOffset;
	}
	
	else if(typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
		point.x = document.documentElement.scrollLeft;
		point.y = document.documentElement.scrollTop;
	}
	
	else if(typeof document.body != 'undefined') {
		point.x = document.body.scrollLeft;
		point.y = document.body.scrollTop;
	}
	
	point.x += ev.clientX;
	point.y += ev.clientY;
}

function up()
{
	document.getElementById("delcon").style.left
}

function down()
{
	
}


function popaut()
{
	document.all.autbg.style.width = document.body.clientWidth;
	document.all.autbg.style.height = document.body.clientHeight;
	document.all.autbg.style.display = "block";
	document.all.autwin.style.display = "block";
}

function closep()
{
	document.all.autwin.style.display = "none";
	document.all.autbg.style.display = "none";
}
