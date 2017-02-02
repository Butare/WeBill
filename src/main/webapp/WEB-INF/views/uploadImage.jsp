<%-- 
    Document   : welcome
    Created on : Dec 24, 2016, 10:44:45 PM
    Author     : JimmyHome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Image</title>
    </head>
    <style>
        .right {
            position: absolute;
            right: 5px;
            width: 90px;
            border: 3px solid #73AD21;
            padding: 5px;
        }
        .error {
            color: #ff0000;
        }
    </style>

    <body>
        <p class="right"> <a href="logout">Logout</a> </p>
        <form method = "POST" action="fileUpload" enctype="multipart/form-data">
            <h1>Your name is : ${userName}</h1>
            <h1>Welcome ${role}</h1>
            <h4>You are welcome <i>${givenName}</i> </h4>
            <h3>Meter ID : <i>${meterID}</i></h3>
            <input type="hidden" name="userID" value="${userName}"/>
            Select a file upload:<input type="file" name="file" id="file" accept="image/*"> <p class="error">${emptyUpload}</p> <br>
            <input type="submit" value="Upload" name="upload" id="upload"/>
        </form>
    </body>
</html>
