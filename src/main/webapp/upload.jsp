<%--
  Created by IntelliJ IDEA.
  User: Mohamed Waleed
  Date: 6/8/2021
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:include page="base.jsp"/>
<!DOCTYPE html>
<html>
 <head>
     <title>
         upload
     </title>
 </head>

 <body onload = "getFiles()">
     <div id="main">
         <h2>BANK STATEMENT UPLOAD</h2>
		 <div id = "center">
		 <div id = "leftcenter">
		 <label for="files">Choose a file:</label>
		 <select id = "Selector"></select>
		 <div id = "buttonForPastFile">
		 <button id = "SelectFilebutton" onclick = "selectFile()">Select file</button>
		</div>
		 </div>
         <div id="fileUpload">
             <form action="/Topicus/upload" method="post" enctype="multipart/form-data">
                 <p>
                     Select a file : <input type="file" name="fileToUpload" size="45" accept=".940"/>
                 </p>

                 <input type="submit" class = "loginButton" value="Upload .940" />
             </form>
         </div>
         </div>
     </div>
 </body>

<style>
    <jsp:include page="WEB-INF/CSS/baseStyle.css"/>

    h2{
        color: white;
        text-align: center;
        letter-spacing: 3px;
        /*margin-bottom: 200px;*/
        margin-top: 20px;
    }
	#option {
	width:45px;
	}
    #fileUpload {
        /*margin-left: 20px;*/
        /*margin-top: 20px;*/
        /*width: 420px;*/
        /*height: 150px;*/
		height: 100px;
        margin: auto;
        margin-top: 20px;
        width: 420px;
        background-color: var(--header-color);
        border: transparent;
        border-radius: 10px;
        justify-content: space-evenly;
        align-items: center;
        padding: 5px;
        text-align: center;
        border: 3px solid black
		border: solid;
   		 border-radius: 10px;
    	border-width: 2px;

    }
    #SelectFilebutton {
    border-width: 2px;
    border-radius: 10px;
    margin-top : 5px;'
    }
    #leftcenter{
      margin: auto;
        margin-top: 20px;
        width: 420px;
        background-color: var(--header-color);
        border: transparent;
        border-radius: 10px;
        justify-content: space-evenly;
        align-items: center;
        padding: 5px;
        text-align: center;
       /* border: 3px solid black */
    }
    #center {
    display: flex;
    justify-content : center;
    }
	#Selector {
	margin: auto;
    margin-top: 10px;
    width: 420px;
    background-color: var(--header-color);
    /* border: transparent; */
    border-radius: 10px;
    border: solid;
    border-width: 2px;
    /* justify-content: space-evenly; */
    /* align-items: center; */
    padding: 5px;
    text-align: center;
	
	}
    #fileUpload input[type="submit"] {
        width: 200px;
        height: 25px;
        font-family: "Calibri";
        font-size: 18px;
        margin: auto;
    }

    #fileUpload input[type="file"] {
        width: 300px;
        font-family: "Calibri";
        font-size: 15px;
        align-content: center;
        margin: auto;
    }
</style>

<script>
    <jsp:include page="WEB-INF/JS/darkTheme.js"/>
    <jsp:include page="WEB-INF/JS/sideNav.js"/>
    	function getFiles() {
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (this.readyState === 4 && this.status === 200) {
				var files = this.responseText.split("\n");
				select(files);
				//for(i = 0; i < files.length; i++) {
				//	console.log(files[i]);		
				//	document.getElementById("ListOfFiles").innerHTML = this.responseText;
				//}
			}
		};
		xmlhttp.open("GET", "http://localhost:8080/Topicus/ListOfFilesServlet",
				true);
		xmlhttp.send();
	}
    function select(files) {
    	var x = document.getElementById("Selector");
		for(i = 0; i < files.length; i++) {
			//console.log(files[i]);		
			var option = document.createElement("option");
			option.value = files[i];
			option.text = files[i];
			x.add(option,null);
   		}
    }
   	function selectFile() {
   		console.log(1);
   	}
</script>
</html>
