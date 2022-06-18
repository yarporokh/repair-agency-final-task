<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fmt:bundle basename="i18n">
        <fmt:message key="allapp.head" var="head"/>
        <fmt:message key="allapp.error" var="error"/>
        <fmt:message key="allapp.id" var="id"/>
        <fmt:message key="allapp.email" var="email"/>
        <fmt:message key="allapp.text" var="text"/>
        <fmt:message key="allapp.date" var="date"/>
        <fmt:message key="allapp.serviceman" var="serviceman"/>
        <fmt:message key="allapp.price" var="price"/>
        <fmt:message key="allapp.paymentstatus" var="paument_status"/>
        <fmt:message key="allapp.progress" var="progress"/>
        <fmt:message key="allapp.response" var="response"/>
        <fmt:message key="allapp.modal.head" var="modal_head"/>
        <fmt:message key="allapp.modal.button.close" var="modal_close"/>
        <fmt:message key="allapp.modal.button.save" var="modal_save"/>
    </fmt:bundle>

    <title>All applications</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
    <script type="text/javascript"
            src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
</head>
<body>
<%@include file="navbar.jsp" %>
<h2 class="text-center">${head}</h2>
<jsp:useBean id="applicationList" scope="request" type="java.util.List"/>
<jsp:useBean id="u" scope="request" type="org.example.models.User"/>
<jsp:useBean id="emails" scope="request" type="java.util.List"/>
<c:choose>
    <c:when test="${empty applicationList}">
        <span>${error}</span>
    </c:when>
    <c:otherwise>
        <table id="applicationTable" class="table table-hover" style="user-select: none;">
            <thead>
            <tr>
                <th scope="col">${id}
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="idASC"><button type="submit"><i class="bi bi-arrow-up"></i></button></form>
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="idDESC"><button type="submit"><i class="bi bi-arrow-down"></i></button></form></th>
                <th scope="col">${email}
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="emailASC"><button type="submit"><i class="bi bi-arrow-up"></i></button></form>
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="emailDESC"><button type="submit"><i class="bi bi-arrow-down"></i></button></form></th>
                <th scope="col">${text}
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="textASC"><button type="submit"><i class="bi bi-arrow-up"></i></button></form>
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="textDESC"><button type="submit"><i class="bi bi-arrow-down"></i></button></form></th>
                <th scope="col">${date}
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="dateASC"><button type="submit"><i class="bi bi-arrow-up"></i></button></form>
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="dateDESC"><button type="submit"><i class="bi bi-arrow-down"></i></button></form>
                </th>
                <th scope="col">${serviceman}
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="servicemanASC"><button type="submit"><i class="bi bi-arrow-up"></i></button></form>
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="servicemanDESC"><button type="submit"><i class="bi bi-arrow-down"></i></button></form></th>
                <th scope="col">${price}
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="priceASC"><button type="submit"><i class="bi bi-arrow-up"></i></button></form>
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="priceDESC"><button type="submit"><i class="bi bi-arrow-down"></i></button></form>
                </th>
                <th scope="col">${paument_status}
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="pstatusASC"><button type="submit"><i class="bi bi-arrow-up"></i></button></form>
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="pstatusDESC"><button type="submit"><i class="bi bi-arrow-down"></i></button></form>
                </th>
                <th scope="col">${progress}
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="progressASC"><button type="submit"><i class="bi bi-arrow-up"></i></button></form>
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="progressDESC"><button type="submit"><i class="bi bi-arrow-down"></i></button></form></th>
                <th scope="col">${response}
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="responseASC"><button type="submit"><i class="bi bi-arrow-up"></i></button></form>
                    <form action="sortedApplications"><input type="hidden" name="sortingBy" value="responseDESC"><button type="submit"><i class="bi bi-arrow-down"></i></button></form></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="count" value="0" scope="page"/>
            <c:forEach items="${applicationList}" var="item">
                <tr data-bs-toggle="modal" data-bs-target="#applicationModal${count}" style="cursor: pointer;">
                    <td>${item.getApplicationId()}
                        <form action="post"></form>
                    </td>
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
                                            ${modal_head} ${item.getApplicationId()}</h5>
                                    <button type="reset" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">
                                                ${text}: ${item.getText()}
                                        </li>
                                        <li class="list-group-item">
                                                ${price}: ${item.getPrice()}
                                            <c:if test="${u.getRole() == Role.MANAGER && item.getPrice() == 0.0}">
                                                <c:if test="${!item.getPaymentStatus().equals('Paid')}">
                                                    <input type="number" name="set-price" step="0.01"
                                                           min="${item.getPrice()}" value="${item.getPrice()}">
                                                </c:if>
                                            </c:if>
                                        </li>
                                        <li class="list-group-item">
                                                ${paument_status}: ${item.getPaymentStatus()}
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
                                                ${serviceman}: ${item.getServicemanEmail()}
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
                                                    <input type="submit" name="take-application"
                                                           value="Take application">
                                                </c:when>
                                            </c:choose>
                                        </li>
                                        <li class="list-group-item">
                                                ${progress}: ${item.getProgress()}
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
                                    <input type="hidden" name="default-payment-status"
                                           value="${item.getPaymentStatus()}">
                                    <input type="hidden" name="default-progress" value="${item.getProgress()}">

                                    <button type="reset" class="btn btn-secondary"
                                            data-bs-dismiss="modal">${modal_close}
                                    </button>
                                    <c:if test="${u.getRole() == Role.MANAGER
                                    || (item.getServicemanEmail().equals('None') && item.getPaymentStatus().equals('Paid'))
                                    || item.getServicemanEmail().equals(u.getEmail())}">
                                        <button type="submit" class="btn btn-primary">${modal_save}</button>
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
<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${numberOfPages}">
            <li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
        </c:forEach>
    </ul>
</nav>

<my:footer/>
</body>
</html>

