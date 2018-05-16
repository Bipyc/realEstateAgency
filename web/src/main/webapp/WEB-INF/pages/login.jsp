<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <div class="pt-3">
        <p class="information-text">Log in</p>
        <a class="auth-link" href="<c:url value="/registration"/>">Registration</a>
        <form method="post" >
            <c:if test="${param.error != null}">
                <div class="error-message">
                    Invalid username or password.
                </div>
            </c:if>
            <div class="auth-block">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input class="input-field form-control" type="text" id="username" name="username"/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input class="input-field form-control" type="password" id="password" name="password"/>
                </div>
            </div>
            <sec:csrfInput/>
            <button type="submit" class="btn login-button">Log in</button>
        </form>
    </div>
</template:page>