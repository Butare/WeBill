<%@include file="urlHeader.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Details</title>
    </head>
    <style>
        .right {
            position: absolute;
            right: 5px;
            width: 90px;
            border: 3px solid #73AD21;
            padding: 5px;
        }
    </style>
    <body>
        <p class="right"> <a href="logout">Logout</a> </p>
        <h1 align="center" >Image List</h1>

        <h3>Your name is : ${userName}</h3>
        <h3 style="color: dodgerblue">Login as: ${role}</h3>

        <c:if test="${not empty listImageDetails}"> 
            <table>
                <thead>
                    <tr>
                        <th>Month</th> <th>Meter Reading</th> <th>Consumption</th> <th>Image Name </th> <th>Bill file Name</th>
                    </tr>
                </thead> 
                <tbody>
                    <c:forEach var="detail" items="${listImageDetails}">
                        <tr>
                            <td> <img src="<c:url value="/photos/${detail.meterImageName}"/>" width="50" height="50"/> </td>
                            <td>${detail.month}</td> <td>${detail.meterReading}</td> <td>${detail.consumption}</td> <td>${detail.meterImageName}</td><td>${detail.billFileName}</td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>

        <c:if test="${empty listImageDetails}"> 
            <h1 style="color: red; border: brown"> Sorry, No image details found</h1>
        </c:if>

    <center>
        <a href="uploadImage"> Upload </a>
    </center>
</body>
</html>
