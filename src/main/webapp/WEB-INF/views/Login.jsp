<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h2>WeBill Login Page Great</h2>
        <form:form commandName="userLogin" method="post">
            <h5> <font color="red">${errorMessage}</font> </h5>
            <table>
                <tr><td>Username</td><td><form:input path="userID"/></td></tr> 
                <tr><td>Password</td> <td><form:password path="passWord" /></td></tr>  
                <tr> <td>Role</td>
                    <td align="right">
                        <form:radiobutton path="userRole" value="Admin"/>Admin 
                        <form:radiobutton path="userRole" value="Customer"/>Customer
                    </td>
                </tr>
                <tr align="center"><td><input type="submit" value="Login" /></td></tr>
            </table>
        </form:form>
    </body>
</html>
