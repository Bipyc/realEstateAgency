<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="span" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <form:form method="post" modelAttribute="dealDto">
        <p class="information-text noselect">Deal</p>
        <br/>
        <div class="auth-block">
            <div class="auth-record">
                <c:if test="${empty create}">
                    <div class="form-group">
                        <span>Date:</span>
                        <fmt:formatDate value="${dealDto.date}" var="date" pattern="yyyy-MM-dd"/>
                        <span><c:out value="${date}"/></span>
                    </div>
                </c:if>
                <template:form_elem label="Client*" path="clientName"/>
                <template:form_elem label="Application ID*" type="number" path="applicationId"/>
                <template:form_elem label="Price*" type="number" path="price"/>
                <template:form_elem label="Commission" type="number" path="commission"/>
            </div>
            <div class="form-group">
                <sec:authorize access="hasAnyRole('ADMIN', 'REALTOR')">
                    <button type="submit" name="save" class="btn btn-success login-button">
                        Save
                    </button>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <c:if test="${empty create}">
                        <button type="submit" name="remove" class="btn btn-danger login-button">
                            Remove
                        </button>
                    </c:if>
                </sec:authorize>
            </div>
            <sec:csrfInput/>
        </div>
    </form:form>
</template:page>