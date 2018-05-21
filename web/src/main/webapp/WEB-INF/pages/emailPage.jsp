<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <div class="pt-3">
        <p class="information-text noselect">Send emails</p>
        <br/>
        <form method="post">
            <c:forEach items="${emails}" var="email">
                <input type="text" name="email" value="${email}">
            </c:forEach>
            <input type="text" name="email" >
            <sec:csrfInput/>
        </form>
    </div>
</template:page>
