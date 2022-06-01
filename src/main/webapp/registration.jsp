<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fmt:bundle basename="i18n">
        <fmt:message key="register.head" var="head"/>
        <fmt:message key="register.firstname" var="firstname"/>
        <fmt:message key="register.lastname" var="lastname"/>
        <fmt:message key="register.email" var="email"/>
        <fmt:message key="register.password" var="pass"/>
        <fmt:message key="register.cpassword" var="cpass"/>
        <fmt:message key="register.button.register" var="reg_b"/>
        <fmt:message key="register.button.signin" var="sign_in_b"/>
        <fmt:message key="register.error" var="error"/>
    </fmt:bundle>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Registration</title>
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
    <form action="register" method="post">
        <h2 class="text-center">${head}</h2>
        <% if (request.getAttribute("regerror") != null) {%>
        <div class="alert alert-danger" role="alert">
            <span>${error}</span>
        </div>
        <%}%>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="${firstname}" name="firstname" required="required"
                   pattern="^[A-Z][a-z]+|[А-ЯІЇҐЄ][а-яіїєґ]+$">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="${lastname}" name="lastname" required="required"
                   pattern="^[A-Z][a-z]+|[А-ЯІЇҐЄ][а-яіїєґ]+$">
        </div>
        <div class="form-group">
            <input type="email" class="form-control" placeholder="${email}" name="email" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="${pass}" name="password1" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="${cpass}" name="password2"
                   required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">${reg_b}</button>
        </div>
    </form>
    <p class="text-center"><a href="login.jsp">${sign_in_b}</a></p>
</div>
<my:footer/>
</body>
</html>
