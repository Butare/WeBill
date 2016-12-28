<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h2>WeBill Login Page Great</h2>
        <form action="login" method="post">
            <h5> <font color="red">${errorMessage}</font> </h5>
            <table>
                <tr><td>Username</td><td><input type="text" name="userName"/></td></tr> 
                <tr><td>Password</td> <td><input type="password" name="passWord"/></td></tr>  
                <tr> <td>Role</td>
                    <td align="right"><input type="radio" name="userRole" value="Admin"/>Admin 
                    <input type="radio" name="userRole" value="Customer"/>Customer</td>
                </tr>
                <tr align="center"><td><input type="submit" value="Login" /></td></tr>
            </table>
        </form>
    </body>
</html>
