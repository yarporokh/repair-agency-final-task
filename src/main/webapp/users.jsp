<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fmt:bundle basename="i18n">
        <fmt:message key="users.head" var="head"/>
        <fmt:message key="users.error" var="error"/>
        <fmt:message key="users.firstname" var="first_name"/>
        <fmt:message key="users.lastname" var="last_name"/>
        <fmt:message key="users.email" var="email"/>
        <fmt:message key="users.balance" var="balance"/>
        <fmt:message key="users.role" var="role"/>
        <fmt:message key="users.modal.head" var="modal_head"/>
        <fmt:message key="users.modal.fullname" var="modal_fullname"/>
        <fmt:message key="users.modal.email" var="modal_email"/>
        <fmt:message key="users.modal.balance" var="modal_balance"/>
        <fmt:message key="users.modal.role" var="modal_role"/>
        <fmt:message key="users.modal.button.close" var="modal_b_close"/>
        <fmt:message key="users.modal.button.save" var="modal_b_save"/>
    </fmt:bundle>

    <title>Users</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
    <script type="text/javascript"
            src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript"
            src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="navbar.jsp" %>

<h2 class="text-center">All users</h2>
<jsp:useBean id="list" scope="request" type="java.util.List"/>
<jsp:useBean id="userEmail" scope="request" type="java.lang.String"/>
<c:choose>
    <c:when test="${empty list}">
        <span>No registered users.</span>
    </c:when>
    <c:otherwise>
        <table id="userTable" class="table table-hover" style="user-select: none;">
            <thead>
            <tr>
                <th scope="col">${first_name}</th>
                <th scope="col">${last_name}</th>
                <th scope="col">${email}</th>
                <th scope="col">${balance}</th>
                <th scope="col">${role}</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="count" value="0" scope="page"/>
            <c:forEach items="${list}" var="item">
                <tr data-bs-toggle="modal" data-bs-target="#userModal${count}" style="cursor: pointer;">
                    <td>${item.getFirstName()}</td>
                    <td>${item.getLastName()}</td>
                    <td>${item.getEmail()}</td>
                    <td>${item.getBalance()}</td>
                    <td>${item.getRole()}</td>
                </tr>

                <!-- Modal -->
                <div class="modal fade" id="userModal${count}" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <form action="userUpdate">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">${modal_head}</h5>
                                    <button type="reset" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">${modal_fullname} ${item.getFirstName()} ${item.getLastName()}
                                        </li>
                                        <li class="list-group-item">${modal_email} ${item.getEmail()}
                                        </li>
                                        <li class="list-group-item">${modal_balance}
                                                ${item.getBalance()} $
                                            <input type="number" name="add-balance" step="0.01"
                                                   min="${item.getBalance()}" value="${item.getBalance()}">
                                        </li>
                                        <li class="list-group-item">${modal_role} ${item.getRole()}
                                            <c:if test="${!userEmail.equals(item.getEmail())}">
                                                <select name="change-role">
                                                    <option>${item.getRole()}</option>
                                                    <c:forTokens items="MANAGER,SERVICEMAN,USER" delims="," var="role">
                                                        <c:if test="${role != item.getRole()}">
                                                            <option>${role}</option>
                                                        </c:if>
                                                    </c:forTokens>
                                                </select>
                                            </c:if>
                                        </li>
                                    </ul>
                                </div>
                                <div class="modal-footer">
                                    <input type="hidden" name="email" value="${item.getEmail()}">
                                    <button type="reset" class="btn btn-secondary" data-bs-dismiss="modal">${modal_b_close}
                                    </button>
                                    <button type="submit" class="btn btn-primary">${modal_b_save}</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <c:set var="count" value="${count + 1}" scope="page"/>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
<my:footer/>
</body>
</html>


<script>
    $(document).ready(function () {
        $('#userTable').dataTable();
    });
</script>