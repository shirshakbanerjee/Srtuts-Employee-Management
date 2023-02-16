<%-- 
    Document   : signup
    Created on : 19-Jan-2023, 3:19:26 pm
    Author     : Shirshak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <link href="https://getbootstrap.com/docs/5.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/signin.css" rel="stylesheet">

    </head>
    <script src="https://code.jquery.com/jquery-3.6.3.js">
                
    </script>
    <script>


                function fetchContent(selectedId, targetId)
                        {
            $.ajax({
                url: 'PreSignup',
                                    data: {
                    [selectedId]: $("#" + selectedId).val()
                                    },
                                    success: function (responseText) {
                                            $("#" + targetId).html(responseText);
                                    }
                            });
                        }

    </script>




    <body class="text-center">
        <main class="form-signin w-100 m-auto">
            <c:if test="${not empty sessionScope.AlreadyExist}">
                    <div class="alert alert-danger" role="alert">
                        <c:out value="${sessionScope.AlreadyExist}"/>
                    </div>
                </c:if>
            <h3 class="display-3">Register</h3>

            <img class="mb-4" src="images/flower-logo.jpg" alt="" width="200" height="200">
            <form action="Signup" method="Post" id="signupForm">
                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@domain.com" name="emailAddress" value="${User.getEmailAddress()}" required>
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" value="${User.getPassword()}" required>
                    <label for="floatingPassword">Password</label>
                </div>

                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="First Name" name="firstName" value="${User.getFirstName()}" required>
                    <label for="floatingInput">First Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="Password" name="lastName" value="${User.getLastName()}" required>
                    <label for="floatingInput">Last Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="address" placeholder="address" name="address" value="${User.getAddressLine1()}" required>
                    <label for="floatingInput">Address Line 1</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="address" placeholder="address" name="address" value="${User.getAddressLine2()}" required="true">
                    <label for="floatingInput">Address Line2</label>
                </div>
                <div class="form-floating">      
                    <select name="countryCode" class="form-select" id="countryCode" onchange="fetchContent('countryCode', 'stateCode')" required>
                        <option value="">Select a Country</option>
                        <c:forEach var="country" items="${CountryList}">
                            <option value='${country.getCountryCode()}' <c:if test="${country.getCountryCode()== User.getCountryCode()}"> selected  </c:if>> ${country.getCountryName()}  </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-floating">      
                    <select name="stateCode" class="form-select" id="stateCode" onchange="fetchContent('stateCode', 'distCode')" required>
                        <option value="" >Select a State</option>

                    </select>
                </div>
                <div class="form-floating">      
                    <select name="distCode" class="form-select" id="distCode" required="">
                        <option value="" >Select a District</option>

                    </select>
                </div>
                <button class="w-100 btn btn btn-primary" type="submit">Submit</button>
            </form>
            <a href="login.jsp">Existing User?  Login</a>
        </main>
    </body>
</html>
