<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script>




    function content(method)
            {
    $.ajax({
    url: method,
                            success: function (responseText) {
                            $("#tableShow").html(responseText);
                        }
            });
    }
</script>
<!--<meta name="theme-color" content="#712cf9">-->

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
        <img src="images/flower-logo.jpg" width="100" height="30" alt="Logo">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>


    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
        <li><a href="home.jsp" class="nav-link px-2 text-secondary">Home</a></li>
        <li><a style="cursor:pointer;" onclick="content('addemployee.jsp')" class="nav-link px-2 text-white">Add Employee</a></li>
        <li><a style="cursor:pointer;" onclick="content('showemployee.jsp')" class="nav-link px-2 text-white"  >Show Employee</a></li>
        <li><a style="cursor:pointer;" onclick="content('searchemployee.jsp')" class="nav-link px-2 text-white">Search Employee</a></li>
        <li><a href="editemployee.jsp" class="nav-link px-2 text-white">Update Employee</a></li>
        <li><a href="ConsumeAPI" class="nav-link px-2 text-white">Fetch API Data</a></li>
    </ul>

    <form class="form-inline my-2 my-lg-0">
        <a class="nav-link" href="#">
            <c:if test="${not empty sessionScope.User}">
                Welcome: 

                <c:set var="user" value="${sessionScope.User}"/>
                <c:out value = "${user.getFirstName()}"/>
                <c:out value = "${user.getLastName()}"/>
            </c:if>
        </a>
    </form>
    <div class="text-end">

        <c:choose>
            <c:when test="${empty sessionScope.User}">
                <a href="login.jsp">
                    <button type="button" class="btn btn-outline-light me-2" >Login</button>
                </a>
                <a href="PreSignup">
                    <button type="button" class="btn btn-warning">Sign-up</button>
                </a>
            </c:when>
            <c:otherwise>

                <a href="Logout">
                    <button type="button" class="btn btn-outline-light me-2" >Log Out</button>
                </a>
            </c:otherwise>

        </c:choose>
    </div>

</nav>