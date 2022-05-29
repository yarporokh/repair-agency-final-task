<%@ page import="org.example.models.User" %>
<%@ page import="org.example.models.Role" %>

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<html>
<fmt:bundle basename="i18n">
    <fmt:message key="navbar.menu" var="n_menu"/>
    <fmt:message key="navbar.profile" var="n_profile"/>
    <fmt:message key="navbar.balance" var="n_balance"/>
    <fmt:message key="navbar.logout" var="n_logout"/>
    <fmt:message key="navbar.allapplications" var="n_all_app"/>
    <fmt:message key="navbar.users" var="n_users"/>
    <fmt:message key="navbar.myapplications" var="n_my_app"/>
</fmt:bundle>

<nav class="navbar navbar-expand-lg bg-light">
    <%User user = (User) session.getAttribute("user");%>
    <div class="container-fluid">
        <span class="navbar-brand" style="user-select: none;">Repair Agency</span>
        <%if (user != null) {%>
        <div class="collapse navbar-collapse" id="navbarScroll">
            <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" href="#" id="navbarScrollingDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        ${n_menu}
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                        <li><a class="dropdown-item" href="profile"><i class="bi bi-person-circle"></i> ${n_profile}</a>
                        </li>
                        <%if (user.getRole() == Role.USER) {%>
                        <li><a class="dropdown-item" href="balance.jsp"><i class="bi bi-cash-stack"></i> ${n_balance}
                        </a>
                        </li>
                        <%}%>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="logout">${n_logout}</a></li>
                    </ul>
                </li>
                <%if (user.getRole() != Role.USER) {%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="allApplications">${n_all_app}</a>
                </li>
                <%}%>
                <%if (user.getRole() == Role.MANAGER) {%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="users">${n_users}</a>
                </li>
                <%}%>
                <%if (user.getRole() == Role.USER) {%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="userApplications">${n_my_app}</a>
                </li>
                <%}%>
            </ul>
        </div>
        <%}%>
        <div>
            <ul class="list-group list-group-horizontal">
                <li class="list-group-item list-group-item"><a class="dropdown-item" href="?lang=en">EN</a></li>
                <li class="list-group-item list-group-item"><a class="dropdown-item" href="?lang=ua">UA</a></li>
            </ul>
        </div>
    </div>
</nav>
</html>