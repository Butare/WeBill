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
        <title>Success</title>
    </head>
    <body>
        <form method = "POST" action="fileUpload" enctype="multipart/form-data">
            <h3>${msgGreeting}</h3>
        <h1>Session success name: ${successName}</h1>
        <h1>Welcome ${role}</h1>
        <h4>You are welcome <i>${givenName}</i> </h4>
        <h3>Meter ID : <i>${meterID}</i></h3>
         Select a file upload:<input type="file" name="file" id="file" accept="image/*"> <br>
        <input type="submit" value="Upload" name="upload" id="upload"/>
        </form>
    </body>
</html>
