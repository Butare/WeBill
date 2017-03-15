<%@include file="urlHeader.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success Upload</title>

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
            .right {
                position: absolute;
                right: 5px;
                width: 90px;
                border: 3px solid #73AD21;
                padding: 5px;
            }
        </style>
    </head>
    <body>
        <p class="right"> <a href="logout">Logout</a> </p>
        <form action="readQrCode" method="post" > 
            <div class="outer">
                <h1>Image information : ${stringOutput}</h1>

            </div>
            <h3 class="success"> ${meterIDFound}</h3>
            <input type="hidden" name="userID" value="${userID}"/>
            <input type="submit" value="Scan QR Code" />
        </form>
    <center>
        <a href="showUploaded" > View uploaded image</a>
    </center>
</body>
</html>
