<%--
  Created by IntelliJ IDEA.
  User: Tariq
  Date: 17/06/2021
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="upload.jsp"/>


<div id="main">
    <div id="fileUpload">
        <form action="/Topicus/upload" method="post" enctype="multipart/form-data">
            <p>
                Select a file : <input type="file" name="fileToUpload" size="45" accept=".940"/>
            </p>

            <input type="submit" class = "loginButton" value="Upload .940" /></br>
            Please only upload mt940 files
        </form>
    </div>
</div>

</body>

<style></style>
<script></script>

</html>