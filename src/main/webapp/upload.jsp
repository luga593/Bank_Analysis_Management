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

         <div class = "column">
             <h2>Description</h2>
             <p>Topicus now allows you to upload any file of type MT940 of any arbitrary size, when you
             choose to upload you will be directed to the "Table view" section where you can view your
             parsed file. Additionally, you can also choose to view any file that you have uploaded previously
             without having to upload it again.</p>
         </div>

		 <div id = "center">
		    <div id = "leftcenter">
		        <label style = "color: white" for= "files">Choose a file:</label>
		        <select id = "Selector"></select>
		        <div id = "buttonForPastFile">
		            <button id = "SelectFilebutton" onclick = "selectFile()">Select file</button>
		        </div>
		    </div>


             <div id="fileUpload">
                 <form action="/Topicus/upload" method="post" enctype="multipart/form-data">
                     <p>
                         Select a file : <input type="file" name="fileToUpload" size="auto" accept=".940" />
                     </p>

                     <button type="submit" value="Upload File">upload file</button>
                 </form>
             </div>

         </div>


     </div>
 </body>

<style>
    <jsp:include page="baseStyle.css"/>

    h2{
        color: white;
        text-align: center;
        letter-spacing: 3px;
        /*margin-bottom: 200px;*/
        margin-top: 20px;
    }

    .column {
        margin: auto;
        justify-content: space-evenly;
        text-align: center;
        color: white;
        float: none;
        width: 50%;
        padding: 5px;
        border: 3px solid #F9E805;
        border-radius: 10px;
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
        width: 430px;
        background-color: var(--bkg-color);
        border: 3px solid #F9E805;
        border-radius: 10px;
        justify-content: space-evenly;
        align-items: center;
        padding: 5px;
        text-align: center;
   		 border-radius: 10px;
    }

    #leftcenter{
        height: 100px;
        margin: auto;
        margin-top: 20px;
        width: 420px;
        background-color: var(--bkg-color);
        border: 3px solid #F9E805;
        border-radius: 10px;
        justify-content: space-evenly;
        align-items: center;
        padding: 5px;
        text-align: center;
    }

    #SelectFilebutton {
        background-color: #F9E805;
        color: black;
        border-width: 2px;
        border-radius: 10px;
        margin-top : 5px;
        border: #3b4465;
    }

    /*#leftcenter:hover{*/
    /*    transform: scale(1.5);*/
    /*}*/

    /*#fileUpload:hover{*/
    /*    transform: scale(1.5);*/
    /*}*/

    #center {
        display: flex;
        justify-content : center;
    }

	#Selector {
        color: white;
        border: 3px solid black;
        margin: auto;
        margin-top: 10px;
        width: 420px;
        background-color: var(--bkg-color);
        /* border: transparent; */
        border-radius: 10px;
        border: 1px solid #F9E805;
        /* justify-content: space-evenly; */
        /* align-items: center; */
        padding: 5px;
        text-align: center;
	}

    #fileUpload input[type="submit"] {
        /*width: 200px;*/
        /*height: 25px;*/
        /*font-family: "Calibri";*/
        /*font-size: 18px;*/
        /*margin: auto;*/
        width: 200px;
        height: 25px;
        background-color: #F9E805;
        color: black;
        border-width: 2px;
        border-radius: 10px;
        /*margin-top : 5px;*/
        border: #3b4465;
    }

    #fileUpload input[type="file"] {
        color: white;
        border: 1px solid #F9E805;
        padding: 5px;
        width: 420px;
        /*font-family: "Calibri";*/
        font-size: 13px;
        align-content: center;
        margin: auto;
    }

    #fileUpload p{
        color: white;
        margin: auto;
    }

    button {
        background-color: #F9E805;
        color: black;
        border-width: 2px;
        border-radius: 10px;
        margin-top : 5px;
        border: #3b4465;
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
		xmlhttp.open("GET", "http://localhost:8080/Topicus/ListOfFilesServlet",	true);
		// xmlhttp.open("GET", "http://topicus-bank1.paas.hosted-by-previder.com/Topicus/ListOfFilesServlet",
		//		true);
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
   		console.log(document.getElementById("Selector").value);
   		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (this.readyState === 4 && this.status === 200) {
				window.location.replace("http://localhost:8080/Topicus/TableSingular.jsp");
				// window.location.replace("http://topicus-bank1.paas.hosted-by-previder.com/Topicus/TableSingular.jsp");
			}
		};
		xmlhttp.open("Get", "http://localhost:8080/Topicus/RequestServlet", true);
		// xmlhttp.open("Post", "http://topicus-bank1.paas.hosted-by-previder.com/Topicus/RequestServlet",
		//		true);

		//xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");  not relevant anymore
		xmlhttp.send(document.getElementById("Selector").value);


   	}
</script>
</html>