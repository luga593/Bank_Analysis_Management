<%--
  Created by IntelliJ IDEA.
  User: Mohamed Waleed
  Date: 6/8/2021
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<div id="fileUpload">
    <form action="/Topicus_war_exploded/upload" method="post" enctype="multipart/form-data">
        <p>
            Select a file : <input type="file" name="fileToUpload" size="45" accept=".940"/>
        </p>

        <input type="submit" value="Upload .940" />
    </form>
</div>
</body>
</html>
