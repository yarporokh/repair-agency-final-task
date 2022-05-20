<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All applications</title>
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
<h2 class="text-center">All applications</h2>
<jsp:useBean id="applicationList" scope="request" type="java.util.List"/>
<jsp:useBean id="u" scope="request" type="org.example.models.User"/>
<jsp:useBean id="emails" scope="request" type="java.util.List"/>
<c:choose>
    <c:when test="${empty applicationList}">
        <span>No one left any applications.</span>
    </c:when>
    <c:otherwise>
        <table id="applicationTable" class="table table-hover" style="user-select: none;">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">User email</th>
                <th scope="col">Text</th>
                <th scope="col">Date</th>
                <th scope="col">Serviceman</th>
                <th scope="col">Price</th>
                <th scope="col">Payment status</th>
                <th scope="col">Progress</th>
                <th scope="col">Response</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="count" value="0" scope="page"/>
            <c:forEach items="${applicationList}" var="item">
                <tr data-bs-toggle="modal" data-bs-target="#applicationModal${count}" style="cursor: pointer;">
                    <td>${item.getApplicationId()}</td>
                    <td>${item.getEmail()}</td>
                    <td>${item.getText()}</td>
                    <td>${item.getDate()}</td>
                    <td>${item.getServicemanEmail()}</td>
                    <td>${item.getPrice()}</td>
                    <td>${item.getPaymentStatus()}</td>
                    <td>${item.getProgress()}</td>
                    <td>${item.getResponseText()}</td>
                </tr>


                <!-- Modal -->
                <div class="modal fade" id="applicationModal${count}" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <form action="allApplications" method="post"> <%----%>
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">
                                        Application ${item.getApplicationId()}</h5>
                                    <button type="reset" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">
                                            Text: ${item.getText()}
                                        </li>
                                        <li class="list-group-item">
                                            Price: ${item.getPrice()}
                                            <c:if test="${u.getRole() == Role.MANAGER && item.getPrice() == 0.0}">
                                                <c:if test="${!item.getPaymentStatus().equals('Paid')}">
                                                    <input type="number" name="set-price" step="0.01"
                                                           min="${item.getPrice()}" value="${item.getPrice()}">
                                                </c:if>
                                            </c:if>
                                        </li>
                                        <li class="list-group-item">
                                            Payment status: ${item.getPaymentStatus()}
                                            <c:if test="${u.getRole() == Role.MANAGER}">
                                                <select name="change-payment-status">
                                                    <option>${item.getPaymentStatus()}</option>
                                                    <c:forTokens items="Payment expected,Canceled" delims=","
                                                                 var="stat">
                                                        <c:if test="${!item.getPaymentStatus().equals(stat)}">
                                                            <option>${stat}</option>
                                                        </c:if>
                                                    </c:forTokens>
                                                </select>
                                            </c:if>
                                        </li>
                                        <li class="list-group-item">
                                            Serviceman: ${item.getServicemanEmail()}
                                            <c:choose>
                                                <c:when test="${u.getRole() == Role.MANAGER && item.getPaymentStatus().equals('Paid') && !item.getProgress().equals('Done')}">
                                                    <select name="change-serviceman">
                                                        <option>${item.getServicemanEmail()}</option>
                                                        <c:forEach items="${emails}" var="em">
                                                            <c:if test="${!item.getServicemanEmail().equals(em)}">
                                                                <option>${em}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                </c:when>
                                                <c:when test="${item.getServicemanEmail().equals('None') && item.getPrice() != 0.0 && item.getPaymentStatus().equals('Paid')}">
                                                    <input type="submit" name="take-application" value="Take application">
                                                </c:when>
                                            </c:choose>
                                        </li>
                                        <li class="list-group-item">
                                            Progress: ${item.getProgress()}
                                            <c:if test="${u.getEmail().equals(item.getServicemanEmail())}">
                                                <select name="change-progress">
                                                    <option>${item.getProgress()}</option>
                                                    <c:forTokens items="Not started,At work,Done" delims="," var="prog">
                                                        <c:if test="${!item.getProgress().equals(prog)}">
                                                            <option>${prog}</option>
                                                        </c:if>
                                                    </c:forTokens>
                                                </select>
                                            </c:if>
                                        </li>
                                    </ul>
                                </div>
                                <div class="modal-footer">
                                    <input type="hidden" name="id" value="${item.getApplicationId()}">
                                    <input type="hidden" name="default-price" value="${item.getPrice()}">
                                    <input type="hidden" name="default-email" value="${item.getServicemanEmail()}">
                                    <input type="hidden" name="default-payment-status" value="${item.getPaymentStatus()}">
                                    <input type="hidden" name="default-progress" value="${item.getProgress()}">

                                    <button type="reset" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <c:if test="${u.getRole() == Role.MANAGER
                                    || (item.getServicemanEmail().equals('None') && item.getPaymentStatus().equals('Paid'))
                                    || item.getServicemanEmail().equals(u.getEmail())}">
                                    <button type="submit" class="btn btn-primary">Save changes</button>
                                    </c:if>
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
</body>
</html>


<script>
    $(document).ready(function () {
        $('#applicationTable').dataTable();
    });
</script>