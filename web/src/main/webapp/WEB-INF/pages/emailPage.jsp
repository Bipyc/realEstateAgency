<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<template:page>
    <div class="auth-record email-form">
        <div class="pt-3">
            <p class="information-text noselect">Send emails</p>
            <br/>
            <form:form method="post" modelAttribute="emailMessage">
                <div class="emailAddresses" class="form-group">
                    <p class="outlined-text">Addresses</p>
                    <c:forEach items="${emailMessage.emails}" var="email" varStatus="i">
                        <p class="email-record">${email}</p>
                        <form:hidden path="emails[${i.index}]"/>
                    </c:forEach>
                </div>
                <template:form_elem path="title" label="Title"/>
                <span>
                    <template:form_elem path="text" type="textarea" label="Text"/>
                </span>
                <button type="submit" class="btn btn-success control-button">
                    Send
                </button>
                <a class="btn btn-info control-button" href="<c:url value="/panel/users"/>">Back</a>
                <sec:csrfInput/>
            </form:form>
        </div>
    </div>
</template:page>
