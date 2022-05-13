<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Registration</title>
<link href="css/login.css" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="login-form">
    <form action="/examples/actions/confirmation.php" method="post">
        <h2 class="text-center">Registration</h2>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="First name" name="firstname" required="required">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Last name" name="lastname" required="required">
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