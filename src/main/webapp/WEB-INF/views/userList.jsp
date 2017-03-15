<%@include file="urlHeader.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User list</title>
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

        <h1 align="center" >User List</h1>

    <center>
        <a href="addUser"> Add new User </a>
    </center>

    <c:if test="${not empty userList}"> 
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <td>User ID</td> <td>Given Name</td> <td>Surname</td> <td>Address</td> <td>email</td> <td>Meter ID</td> 
                        <td>Latitude</td> <td>Longitude</td> <td></td>
                    </tr>
                </thead> 
                <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${user.userID}</td> <td>${user.givenName}</td> <td>${user.surName}</td> <td>${user.address}</td><td>${user.email}</td> 
                            <td>${user.meterID}</td><td>${user.locationLatitude}</td><td>${user.locationLongitude}</td>
                            <td><a href="editUser?userID=${user.userID}">Edit</a>  </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </c:if>
</body>
</html>
