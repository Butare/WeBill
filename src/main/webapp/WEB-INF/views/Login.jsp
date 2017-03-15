<%@include file="urlHeader.jsp" %>   

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
        <form:form modelAttribute="userLogin" method="post"  action="login">
           <!-- <form:errors path="*" cssClass="errorblock" element="div" />-->
            <h5> <font color="red">${errorMessage}</font> </h5>
            <table class="table table-responsive table-hover">
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
                    <td>
                        <form:radiobutton path="userRole" value="Admin"/>Admin 
                        <form:radiobutton path="userRole" value="Customer"/>Customer
                    </td>
                    <td><form:errors path="userRole" cssClass="errorblock" /></td>
                </tr>
            </table>
            
           
            <center>
                 
                     <input type="submit" value="Login" class="btn btn-primary btn-lg" id="btnLogin"/>
     
            </center>
              
        </form:form>
    </body>
</html>
