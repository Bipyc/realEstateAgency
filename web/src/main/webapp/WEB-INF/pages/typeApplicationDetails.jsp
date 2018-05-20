<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="span" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <form:form method="post" modelAttribute="typeApplicationDto">
        <p class="information-text">Application details</p>
        <br/>
        <div class="auth-block">
            <div class="auth-record">
                <template:form_elem label="Name*" path="name"/>
                <template:form_elem label="Commission(in persent)*" type="number" path="commission"/>
            </div>
            <div class="form-group">
                <button type="submit" name="save" class="btn btn-success login-button">
                    Save
                </button>
                <c:if test="${empty create}">
                    <button type="submit" name="remove" class="btn btn-danger login-button">
                        Remove
                    </button>
                </c:if>
            </div>
            <sec:csrfInput/>
        </div>
    </form:form>
</template:page>