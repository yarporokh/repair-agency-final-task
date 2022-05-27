<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fmt:bundle basename="i18n">
        <fmt:message key="userapp.head" var="head"/>
        <fmt:message key="userapp.button.create" var="create_b"/>
        <fmt:message key="userapp.error" var="error"/>
        <fmt:message key="userapp.id" var="id"/>
        <fmt:message key="userapp.email" var="email"/>
        <fmt:message key="userapp.text" var="text"/>
        <fmt:message key="userapp.date" var="date"/>
        <fmt:message key="userapp.serviceman" var="serviceman"/>
        <fmt:message key="userapp.price" var="price"/>
        <fmt:message key="userapp.paymentstatus" var="payment_status"/>
        <fmt:message key="userapp.progress" var="progress"/>
        <fmt:message key="userapp.response" var="response"/>
        <fmt:message key="userapp.modal.response.responsetext" var="modal_response_text"/>
        <fmt:message key="userapp.modal.response.button.close" var="modal_response_b_close"/>
        <fmt:message key="userapp.modal.response.leaveresponse" var="modal_response_b_leaveresponse"/>
        <fmt:message key="userapp.modal.application.head" var="modal_app_head"/>
        <fmt:message key="userapp.modal.application.applicationtext" var="modal_app_application_text"/>
        <fmt:message key="userapp.modal.application.button.close" var="modal_app_b_close"/>
        <fmt:message key="userapp.modal.application.button.add" var="modal_app_b_add"/>
    </fmt:bundle>

    <title>Title</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<h2 class="text-center">${head}</h2>
<!-- Button trigger modal -->
<div>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        <i class="bi bi-clipboard-plus"></i> ${create_b}
    </button>
</div>

<jsp:useBean id="list" scope="request" type="java.util.List"/>
<jsp:useBean id="servicemenNames" scope="request" type="java.util.List"/>
<c:choose>
    <c:when test="${empty list}">
        <span>${error}</span>
    </c:when>
    <c:otherwise>
        <table class="table table-striped" style="user-select: none;">
            <thead>
            <tr>
                <th>${id}</th>
                <th>${text}</th>
                <th>${date}</th>
                <th>${serviceman}</th>
                <th>${price}</th>
                <th>${payment_status}</th>
                <th>${progress}</th>
                <th>${response}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="item" varStatus="status">

                <tr>
                    <td>${item.getApplicationId()}</td>
                    <td>${item.getText()}</td>
                    <td>${item.getDate()}</td>
                    <td>${servicemenNames.get(status.index)}<br/><span
                            style="font-style: italic">Email: ${item.getServicemanEmail()}</span></td>
                    <td>${item.getPrice()} $
                        <c:if test="${item.getPrice() > 0.0 && item.getPaymentStatus().equals('Payment expected')}">
                            <form action="pay" method="post">
                                <input type="hidden" name="id" value="${item.getApplicationId()}">
                                <input type="hidden" name="price" value="${item.getPrice()}">
                                <input class="btn btn-primary" type="submit" value="Pay">
                            </form>
                        </c:if>
                    </td>
                    <td>${item.getPaymentStatus()}</td>
                    <td>${item.getProgress()}</td>
                    <td>
                    <c:choose>
                        <c:when test="${item.getResponseText().equals('') && item.getProgress().equals('Done')}">
                            <div>
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                        data-bs-target="#responseModal${item.getApplicationId()}"> Response
                                </button>
                            </div>
                        </c:when>
                        <c:otherwise>
                            ${item.getResponseText()}
                        </c:otherwise>
                    </c:choose>
                    </td>
                </tr>

                <!-- Response Modal -->
                <div class="modal fade" id="responseModal${item.getApplicationId()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="responseModalLabel">${response}</h5>
                                <button type="reset" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">

                                <form action="response" method="post">
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label for="resp-text" class="col-form-label">${modal_response_text}</label>
                                            <textarea class="form-control" id="resp-text" name="response-text" rows="7" maxlength=999
                                                      style="resize: none;" required="required"></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="hidden" name="app-id" value="${item.getApplicationId()}">
                                        <button type="reset" class="btn btn-secondary" data-bs-dismiss="modal">${modal_response_b_close}</button>
                                        <button type="submit" class="btn btn-primary">${modal_response_b_leaveresponse}</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
<my:footer/>
</body>
</html>


<!-- Create Application Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">${modal_app_head}</h5>
                <button type="reset" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                <form action="createApplication" method="post">
                    <div class="form-group">
                        <div class="form-group">
                            <label for="app-text" class="col-form-label">${modal_app_application_text}</label>
                            <textarea class="form-control" id="app-text" name="application-text" rows="7" maxlength=999
                                      style="resize: none;" required="required"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="reset" class="btn btn-secondary" data-bs-dismiss="modal">${modal_app_b_close}</button>
                        <button type="submit" class="btn btn-primary">${modal_app_b_add}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


