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

 <body>
     <div id="main">
         <div id="fileUpload">
             <form action="/Topicus_war/upload" method="post" enctype="multipart/form-data">
                 <p>
                     Select a file : <input type="file" name="fileToUpload" size="45" accept=".940"/>
                 </p>

                 <input type="submit" class = "loginButton" value="Upload .940" />
             </form>
         </div>
     </div>
 </body>

<style>
    <jsp:include page="WEB-INF/CSS/baseStyle.css"/>

    #fileUpload {
        margin-left: 20px;
        margin-top: 20px;
        width: 420px;
        height: 150px;
        background-color: var(--header-color);
        border: transparent;
        border-radius: 10px;
        justify-content: space-evenly;
        align-items: center;
        padding: 2px;
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
</script>
</html>
