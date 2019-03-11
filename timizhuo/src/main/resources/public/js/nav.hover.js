var nav = document.getElementById("nav");
var links =nav.getElementsByTagName("li");
var lilen = nav.getElementsByTagName("a");
var currenturl = document.location.href;
var last = 0;
for (var i=0;i<links.length;i++)
{
var linkurl = lilen[i].getAttribute("href");
if(currenturl.indexOf(linkurl)!=-1)
{
last = i;
}
}
lilen[last].className = "hover";