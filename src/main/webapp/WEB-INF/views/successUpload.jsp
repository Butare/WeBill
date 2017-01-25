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

        <style>

            .success {
                width: 960px;
                color: #ff0000;
                border: 2px solid blue;
            }
            .outer {
                width: 960px;
                color: navy;
                background-color: pink;
                border: 2px solid darkblue;
                padding: 5px;
            }
        </style>
    </head>
    <body>
        <form action="readQrCode" method="post" > 
            <div class="outer">
            <h1>Image information : ${stringOutput}</h1>
            
            </div>
            <h3 class="success"> ${meterIDFound}</h3>
            <input type="hidden" name="userID" value="${userID}"/>
            <input type="submit" value="Scan QR Code" />
        </form>
    </body>
</html>
