<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<h2 class="text-center">My applications</h2>
<!-- Button trigger modal -->
<div>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        <i class="bi bi-clipboard-plus"></i> New application
    </button>
</div>

<jsp:useBean id="list" scope="request" type="java.util.List"/>
<jsp:useBean id="servicemenNames" scope="request" type="java.util.List"/>
<c:choose>
    <c:when test="${empty list}">
        <span>You didn't leave any applications.</span>
    </c:when>
    <c:otherwise>
        <table class="table table-striped" style="user-select: none;">
            <thead>
            <tr>
                <th>Id</th>
                <th>Text</th>
                <th>Date</th>
                <th>Serviceman</th>
                <th>Price</th>
                <th>Payment status</th>
                <th>Progress</th>
                <th>Response</th>
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
                        <c:if test="${item.getPrice() > 0.0 && !item.getPaymentStatus().equals('Paid')}">
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
                                <h5 class="modal-title" id="responseModalLabel">Response</h5>
                                <button type="reset" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">

                                <form action="response" method="post">
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label for="resp-text" class="col-form-label">Response text:</label>
                                            <textarea class="form-control" id="resp-text" name="response-text" rows="7" maxlength=999
                                                      style="resize: none;" required="required"></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="hidden" name="app-id" value="${item.getApplicationId()}">
                                        <button type="reset" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-primary">Leave response</button>
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
</body>
</html>


<!-- Create Application Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Create application</h5>
                <button type="reset" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                <form action="createApplication" method="post">
                    <div class="form-group">
                        <div class="form-group">
                            <label for="app-text" class="col-form-label">Application text:</label>
                            <textarea class="form-control" id="app-text" name="application-text" rows="7" maxlength=999
                                      style="resize: none;" required="required"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="reset" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


