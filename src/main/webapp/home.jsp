
<%@ page import="java.sql.*, java.lang.System.*, com.DB.DBConnect" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page - FlyMoney</title>
        <%@include file="Public/indexStyle.jsp" %>
    </head>
    <body>
        <%@include file="Components/header.jsp"%>
        <c:if test="${not empty success}">
            <h1 class="text-green-500 text-2xl bg-clifford">vous etes connecte et bien eregistrer!</h1>
            <p>${success}</p>
        </c:if>
        <h1 class="text-red-500 text-5xl bg-clifford">Hello World!</h1>

    </body>
</html>

