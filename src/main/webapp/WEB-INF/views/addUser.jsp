<%-- 
    Document   : addUser
    Created on : Jan 22, 2017, 1:16:36 AM
    Author     : JimmyHome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Add</title>
        <style>
            .errorblock {
                color: #000;
                background-color: #ffEEEE;
                border: 2px solid #ff0000;
                padding: 3px;
                margin: 5px;      
            }
        </style>
    </head>
    <body>
        <h1>Add new User </h1>
        <form:form commandName="addUser" method="post">
            <table>
                <tr>
                    <td>User ID</td> <td><form:input path="userID" /></td>
                    <td><form:errors path="userID" cssClass="errorblock" /></td>
                </tr>
                <tr>
                    <td>Given Name</td> <td><form:input path="givenName" /></td>
                    <td><form:errors path="givenName" cssClass="errorblock" /></td>
                </tr>
                <tr>
                    <td>Surname</td> <td><form:input path="surName" /></td>
                    <td><form:errors path="surName" cssClass="errorblock" /></td>
                </tr>
                <tr>
                    <td>Secret word</td> <td><form:input path="passWord" /></td>
                    <td><form:errors path="passWord" cssClass="errorblock" /></td>
                </tr> 
                <tr>
                    <td>User Role</td> 
                    <td> 
                        <form:select path="userRole">
                            <form:option value="NONE" label="Select..."/>
                            <form:options items="${roleList}" />
                        </form:select>
                    </td>
                    <td><form:errors path="userRole" cssClass="errorblock" /></td>
                </tr>
                <tr>
                    <td>Address</td> <td><form:input path="address" /></td>
                    <td><form:errors path="address" cssClass="errorblock" /></td>
                </tr> 
                <tr>
                    <td>Email</td> <td><form:input path="email" /></td>
                    <td><form:errors path="email" cssClass="errorblock" /></td>
                </tr>
                <tr>
                    <td>Meter ID</td> <td><form:input path="meterID" /></td>
                    <td><form:errors path="meterID" cssClass="errorblock" /></td>
                </tr> 
            </table>
        <center>
            <input  style="height:30px; width:100px" type="submit" value="Add new User"/>
        </center>
    </form:form>
</body>
</html>
