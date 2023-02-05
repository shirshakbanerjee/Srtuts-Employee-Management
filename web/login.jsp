<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" >
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Employee Management">
        
        <title>Employee Management</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        
        
        <meta name="theme-color" content="#712cf9">

        <!-- Custom styles for this template -->
        <link href="css/login.css" rel="stylesheet">
    </head>
    <body class="text-center">
        <main class="form-signin w-100 m-auto">
            
                    <img class="mb-4" src="images/flower-logo.jpg" alt="" width="200" height="200">
                </div>
                <c:if test="${not empty sessionScope.LoginErrorMsg}">
                    <div class="alert alert-danger" role="alert">
                        <c:out value="${sessionScope.LoginErrorMsg}"/>
                    </div>
                </c:if>

                <div class="card-body">
                    <h1 class="card-title">Sign in </h1>
                    <form action="Login" method="Post">


                        <div class="form-floating">
                            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="emailAddress">
                            <label for="floatingInput">Email address</label>
                        </div>
                        <div class="form-floating">
                            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password">
                            <label for="floatingPassword">Password</label>
                        </div>
                        <div >
                            <label>
                                <input type="checkbox" value="remember-me"> Remember me
                            </label>
                        </div>
                        <button class="w-50 btn btn btn-primary" type="submit">Log in</button>
                    </form>
                    <div class="card-footer">
                        <a href="signup.jsp">Create new account</a>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
