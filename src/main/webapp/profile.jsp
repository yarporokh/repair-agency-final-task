<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Profile</title>
    <link href="css/login.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        <%@include file="/WEB-INF/css/profile.css" %>
    </style>
</head>
<body>
<%@include file="navbar.jsp" %>
<h2 class="text-center">Profile</h2>
<div class="d-flex justify-content-center">
    <div class="card" style="width: 18rem;">
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Full name: <%= user.getFirstName() %> <%= user.getLastName() %>
            </li>
            <li class="list-group-item">Email: <%= user.getEmail() %>
                <%if (user.getRole().equals("User")) {%>
            </li>
            <li class="list-group-item">Balance: <%= user.getBalance() %> $
            </li>
            <%}%>
            <li class="list-group-item">Role: <%= user.getRole() %>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
