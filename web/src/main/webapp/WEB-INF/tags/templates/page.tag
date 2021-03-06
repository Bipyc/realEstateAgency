<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="isDark" type="java.lang.Boolean" %>
<c:set var="isDark" value="${(empty isDark) ? true : isDark}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<div class="wrapper-bg">
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">
            <strong><c:out value="${errorMessage}"/></strong>
        </div>
    </c:if>
    <div class="header">
        <div class="auth ">
            <sec:authorize access="hasRole('ANONYMOUS')">
                <a class="auth-link" href="<c:url value="/registration"/>">Registration</a>
                <a class="auth-link" href="<c:url value="/login"/>">Login</a>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ADMIN', 'CLIENT', 'REALTOR')">
                <form action="<c:url value="/logout"/>" method="post">
                    <sec:csrfInput/>
                    <span class="username noselect"><sec:authentication property="principal.username"/></span>
                    <button class="auth-link" type="submit">Logout</button>
                </form>
            </sec:authorize>
        </div>
        <nav class="navbar navbar-default">
            <div class="container">
                <a class="navbar-brand" href="<c:url value="/"/>">REALT</a>
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="<c:url value="/"/>">HOME</a></li>

                        <li>
                            <a href="<c:url value="/catalog"/>">CATALOG</a>
                        </li>
                        <sec:authorize access="hasAnyRole('ADMIN', 'CLIENT', 'REALTOR')">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">PANEL <span
                                        class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <sec:authorize access="hasRole('CLIENT')">
                                        <li><a href="<c:url value="/panel/immobilities"/>">My Immobilities</a></li>
                                        <li><a href="<c:url value="/panel/inspections"/>">My Inspections</a></li>
                                        <li><a href="<c:url value="/panel/deals"/>">My Deals</a></li>
                                        <li><a href="<c:url value="/document"/>">Documents</a></li>
                                    </sec:authorize>
                                    <sec:authorize access="hasRole('REALTOR')">
                                        <li><a href="<c:url value="/panel/applications"/>">My Applications</a></li>
                                        <li><a href="<c:url value="/panel/inspections"/>">My Inspections</a></li>
                                        <li><a href="<c:url value="/panel/deals"/>">My Deals</a></li>
                                        <li><a href="<c:url value="/document"/>">Documents</a></li>
                                    </sec:authorize>
                                    <sec:authorize access="hasRole('ADMIN')">
                                        <li><a href="<c:url value="/panel/users"/>">Users</a></li>
                                        <li><a href="<c:url value="/panel/immobilities"/>">Immobilities</a></li>
                                        <li><a href="<c:url value="/panel/applications"/>">Applications</a></li>
                                        <li><a href="<c:url value="/panel/inspections"/>">Inspections</a></li>
                                        <li><a href="<c:url value="/panel/deals"/>">Deals</a></li>
                                        <li><a href="<c:url value="/panel/typeApplications"/>">Comission</a>
                                        <li><a href="<c:url value="/document"/>">Documents</a></li>
                                    </sec:authorize>
                                </ul>
                            </li>
                        </sec:authorize>
                        <li>
                            <sec:authorize access="hasAnyRole('ADMIN', 'CLIENT', 'REALTOR')">
                                <a href="<c:url value="/profile"/>">PROFILE</a>
                            </sec:authorize>
                        </li>

                    </ul>
                </div>
            </div>
            </li>
        </nav>
    </div>
    <div class="content">
        <div ${isDark ? 'class="content_dark_background"' : 'class="main-text"'} >
            <jsp:doBody/>
        </div>
    </div>
    <div class="footer">
        Dima Bipyc and two asshols, 2018
    </div>
</div>
<script>
    var context_path = "<c:out value="${pageContext.request.contextPath}"/>";
</script>
<script src="<c:url value="/resources/js/script.js"/>"></script>
</body>
</html>