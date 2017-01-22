<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <style>
            .error {
                color: #ff0000;
            }
            
            .errorblock {
                color: #000;
                background-color: #ffEEEE;
                border: 2px solid #ff0000;
                padding: 3px;
                margin: 5px;      
            }
        </style>
        
        <title>Login Page</title>
    </head>
    <body>
        <h1 align="center">WeBill Login Page</h1>
        <form:form commandName="userLogin" method="post" >
           <!-- <form:errors path="*" cssClass="errorblock" element="div" />-->
            <h5> <font color="red">${errorMessage}</font> </h5>
            <table>
                <tr>
                    <td>Username</td>
                    <td><form:input path="userID"/></td>
                    <td><form:errors path="userID" cssClass="errorblock"/></td>
                </tr> 
                <tr>
                    <td>Password</td> 
                    <td><form:password path="passWord" /></td>
                    <td><form:errors path="passWord" cssClass="errorblock" /></td>
                </tr>  
                <tr> <td>Role</td>
                    <td align="right">
                        <form:radiobutton path="userRole" value="Admin"/>Admin 
                        <form:radiobutton path="userRole" value="Customer"/>Customer
                    </td>
                    <td><form:errors path="userRole" cssClass="errorblock" /></td>
                </tr>
            </table>
            <center>
                <input type="submit" value="Login" style="height:30px; width:100px"/>
            </center>
            
        </form:form>
    </body>
</html>
