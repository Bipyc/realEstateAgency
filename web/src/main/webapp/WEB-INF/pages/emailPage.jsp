<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<template:page>
    <div class="pt-3">
        <p class="information-text">Send emails</p>
        <br/>
        <form:form method="post" modelAttribute="emailMessage">
            <div class="emailAddresses" class="form-group">
                <p class="outlined-text">Addresses</p>
                <c:forEach items="${emailMessage.emails}" var="email" varStatus="i">
                    <p>${email}</p>
                    <form:hidden path="emails[${i.index}]"/>
                </c:forEach>
            </div>
            <template:form_elem path="title" label="Title"/>
            <template:form_elem path="text" type="textarea" label="Text"/>
            <button type="submit" class="btn btn-success control-button">
                Send
            </button>
            <a class="btn btn-prim" href="<c:url value="/panel/users"/>">Back</a>
            <sec:csrfInput/>
        </form:form>
    </div>
</template:page>
