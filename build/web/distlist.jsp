<%-- 
    Document   : distlist
    Created on : 13-Feb-2023, 3:03:28 pm
    Author     : SHIRSHAK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<option value="">Select a District</option>
<c:forEach var="dist" items="${DistList}">
    <option value='${dist.getDistCode()}'> <c:if test="${dist.getDistCode() == User.getDistCode()}" > selected</c:if> 
        ${dist.getDistName()}  
    </option>
</c:forEach>
