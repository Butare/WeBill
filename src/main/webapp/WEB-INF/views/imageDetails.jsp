<%-- 
    Document   : imageDetails
    Created on : Jan 25, 2017, 5:23:32 PM
    Author     : JimmyHome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Details</title>
    </head>
    <body>
        <h1 align="center" >Image List</h1>
        <c:if test="${not empty listImageDetails}"> 
            <table>
                <thead>
                    <tr>
                        <td>Month</td> <td>Meter Reading</td> <td>Consumption</td> <td>Image Name </td> <td>Bill file Name</td>
                    </tr>
                </thead> 
                <tbody>
                    <c:forEach var="detail" items="${listImageDetails}">
                        <tr>
                            <td>${detail.month}</td> <td>${detail.meterReading}</td> <td>${detail.consumption}</td> <td>${detail.meterImageName}</td><td>${detail.billFileName}</td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
              
              </c:if>
    <center>
        <a href="fileUpload"> Upload </a>
    </center>
    </body>
</html>
