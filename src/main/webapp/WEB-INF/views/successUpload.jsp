<%-- 
    Document   : successUpload
    Created on : Jan 16, 2017, 12:19:44 PM
    Author     : JimmyHome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="readQrCode" method="post" > 
        <h1>Image information : ${stringOutput}</h1>
        <h2>The image name is: <input type="text"  name="imageName" value="${imageName}"/> </h2>
        <input type="submit" value="Scan QR Code" />
        </form>
    </body>
</html>
