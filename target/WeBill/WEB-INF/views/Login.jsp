<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h2>WeBill Login Page</h2>
        <form action="/login.htm" method="post">
            <table>
                <tr><td>Username</td><td><input type="text" name="username"/></td></tr> 
                <tr><td>Password</td> <td><input type="password" name="password"/></td></tr>  
                <tr align="center">
                    <td align="right"><input type="radio" name="userRole"/>Admin</td> 
                    <td><input type="radio" name="userRole"/>Customer</td>
                </tr>
                <tr align="center"><td><input type="submit" value="Login" /></td></tr>
            </table>
        </form>
    </body>
</html>
