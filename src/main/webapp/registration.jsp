<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Registration</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style><%@include file="/WEB-INF/css/login.css"%></style>
</head>
<body>
<%@include file="navbar.jsp" %>
<% if (user != null) response.sendRedirect("profile"); %>
<div class="login-form">
    <form action="register" method="post">
        <h2 class="text-center">Registration</h2>
        <% if (request.getAttribute("regerror") != null) {%>
        <div class="alert alert-danger" role="alert">
            <span>Passwords do not match or the user is registered.</span>
        </div>
        <%}%>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="First name" name="firstname" required="required" pattern="^[A-Z][a-z]+|[А-ЯІЇҐЄ][а-яіїєґ]+$">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Last name" name="lastname" required="required" pattern="^[A-Z][a-z]+|[А-ЯІЇҐЄ][а-яіїєґ]+$">
        </div>
        <div class="form-group">
            <input type="email" class="form-control" placeholder="Email" name="email" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Password" name="password1" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Confirm password" name="password2" required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Create an Account</button>
        </div>
    </form>
    <p class="text-center"><a href="login.jsp">Sign in</a></p>
</div>
</body>
</html>
