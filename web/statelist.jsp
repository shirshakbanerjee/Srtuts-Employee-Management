<%-- 
    Document   : statelist
    Created on : 13-Feb-2023, 2:59:22 pm
    Author     : SHIRSHAK
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<option value="">Select a State</option>
<c:forEach var="state" items="${StateList}">
    <option value='${state.getStateCode()}'<c:if test="${state.getStateCode()== User.getStateCode()}"> selected  </c:if>> 
        ${state.getStateName()}  
    </option>
</c:forEach>


