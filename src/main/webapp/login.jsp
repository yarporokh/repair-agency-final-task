<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <fmt:bundle basename="i18n">
        <fmt:message key="register.button.signin" var="sign_in"/>
        <fmt:message key="login.error" var="error"/>
        <fmt:message key="register.button.register" var="sign_up"/>
        <fmt:message key="register.email" var="email"/>
        <fmt:message key="register.password" var="password"/>
    </fmt:bundle>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Sign in</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        <%@include file="/WEB-INF/css/login.css" %>
    </style>
</head>
<body>
<%@include file="navbar.jsp" %>
<% if (user != null) response.sendRedirect("profile"); %>
<div class="login-form">
    <form action="login" method="post">
        <h2 class="text-center">${sign_in}</h2>
        <% if (request.getAttribute("logerror") != null) {%>
        <div class="alert alert-danger" role="alert">
            <span>${error}</span>
        </div>
        <%}%>
        <div class="form-group">
            <input type="email" class="form-control" placeholder="${email}" name="email" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="${password}" name="password" required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">${sign_in}</button>
        </div>
    </form>
    <p class="text-center"><a href="registration.jsp">${sign_up}</a></p>
</div>
<my:footer/>
</body>
</html>
