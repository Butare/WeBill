<%-- 
    Document   : dashboardPage
    Created on : Jan 21, 2017, 4:43:00 PM
    Author     : JimmyHome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User list</title>
    </head>
    <body>
        <h1 align="center" >User List</h1>
        <c:if test="${not empty userList}"> 
            <table>
                <thead>
                    <tr>
                        <td>User ID</td> <td>Given Name</td> <td>Surname</td> <td>Address</td> <td>email</td> <td>Meter ID</td> 
                        <td>Latitude</td> <td>Longitude</td>
                    </tr>
                </thead> 
                <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${user.userId}</td> <td>${user.givenName}</td> <td>${user.surName}</td> <td>${user.address}</td><td>${user.email}</td> 
                            <td>${user.meterID}</td><td>${user.locationLatitude}</td><td>${user.locationLongitude}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
              
              </c:if>
        
    </body>
</html>