<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <div class="error-bg">
            <div class="info">
                follow us :
                <a href="https://github.com/DmKoshelek/realEstateAgency" target="_blank">GitHub</a>
                <a href="https://www.linkedin.com" target="_blank">LinkedIn</a>
            </div>

            <div class="box">
                <%--<div>--%>
                <%--close !--%>
                <%--</div>--%>
                <%--<p><span>error 404 !</span> sorry page isn't found for one of the reformes </p>--%>
                <jsp:doBody/>
            </div>
            <script src="<c:url value="/resources/js/script.js"/>"></script>
        </div>
    </body>
</html>