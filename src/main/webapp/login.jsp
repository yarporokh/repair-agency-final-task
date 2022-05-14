<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Sign in</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style><%@include file="/WEB-INF/css/login.css"%></style>
</head>
<body>
<div class="login-form">
    <form action="login" method="post">
        <h2 class="text-center">Sign in</h2>
        <div class="form-group">
            <input type="email" class="form-control" placeholder="Email" name="email" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Password" name="password" required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Sign in</button>
        </div>
    </form>
    <p class="text-center"><a href="registration.jsp">Create an Account</a></p>
</div>
</body>
</html>
