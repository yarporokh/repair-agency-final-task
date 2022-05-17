<%--
  Created by IntelliJ IDEA.
  User: Yarik
  Date: 15.05.2022
  Time: 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Balance</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<h2 class="text-center">Balance</h2>
<div class="d-flex justify-content-center">
    <form action="balance" method="post">
        <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Your balance: <%= user.getBalance() %> $
                </li>
                <li class="list-group-item"> <input type="number" name="add-balance" min="0" step="0.01" placeholder="Amount">
                </li>
                <button type="submit" class="btn btn-primary btn-block">Top up</button>
            </ul>
        </div>
    </form>
</div>

</body>
</html>
