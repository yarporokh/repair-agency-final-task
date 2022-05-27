<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:bundle basename="i18n">
        <fmt:message key="balance.head" var="head"/>
        <fmt:message key="balance.yourbalance" var="your_balance"/>
        <fmt:message key="balance.amount" var="amount"/>
        <fmt:message key="balance.button.topup" var="top_up"/>

    </fmt:bundle>

    <title>Balance</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<h2 class="text-center">${head}</h2>
<div class="d-flex justify-content-center">
    <form action="balance" method="post">
        <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
                <li class="list-group-item">${your_balance} <%= user.getBalance() %> $
                </li>
                <li class="list-group-item"> <input type="number" name="add-balance" min="0" step="0.01" placeholder="${amount}">
                </li>
                <button type="submit" class="btn btn-primary btn-block">${top_up}</button>
            </ul>
        </div>
    </form>
</div>
<my:footer/>
</body>
</html>
