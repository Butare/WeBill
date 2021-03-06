<%@include file="urlHeader.jsp" %>  
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
            .right {
                position: absolute;
                right: 5px;
                width: 90px;
                border: 3px solid #73AD21;
                padding: 5px;
            }
        </style>
    </head>
    <body>
        <p class="right"> <a href="logout">Logout</a> </p>
        <h1>Add new User </h1>
        <form:form modelAttribute="userForm" action="addUser" method="post">
            <div class="table-responsive">
                <table class="table table-hover">
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
                <c:if test="${not empty userDetail}">
                    <table class="table table-hover">
                        <tr>
                            <td>User ID</td> <td><form:input path="${userDetail.userID}" /></td>
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
                </c:if>
            </div>
        <center>
            <input class="btn btn-lg" type="submit" value="Add new User"/>
        </center>
    </form:form>
</body>
</html>
