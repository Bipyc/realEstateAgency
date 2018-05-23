<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="span" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <c:choose>
        <c:when test="${empty create}">
            <div class="information-text outlined-text noselect">Create inspection</div>
        </c:when>
        <c:when test="${empty createByUser}">
            <div class="information-text outlined-text noselect">Add inspection</div>
        </c:when>
        <c:otherwise>
            <div class="information-text outlined-text noselect">Edit inspection</div>
        </c:otherwise>
    </c:choose>
    <br/>
    <form:form method="post" enctype="multipart/form-data" modelAttribute="inspectionDto">
        <sec:csrfInput/>
        <div class="auth-block">
            <div class="auth-record">
                <c:choose>
                    <c:when test="${empty createByUser}">
                        <c:if test="${not empty create}">
                            <template:form_elem label="Immobility ID*" type="number" path="immobilityId"/>
                        </c:if>
                        <c:if test="${empty create}">
                            <div class="form-group">
                                <span>Immobility ID: </span>
                                <span>${inspectionDto.immobilityId}</span>
                            </div>
                            <form:hidden path="immobilityId"/>
                        </c:if>
                        <template:form_elem label="Realtor*" path="realtorName"/>
                        <template:form_elem label="Client*" path="clientName"/>
                    </c:when>
                    <c:otherwise>
                        <form:hidden path="immobilityId" value="1"/>
                        <form:hidden path="realtorName" value="1"/>
                        <form:hidden path="clientName" value="1"/>
                    </c:otherwise>
                </c:choose>
                <fmt:formatDate value="${inspectionDto.date}" var="date" pattern="yyyy-MM-dd"/>
                <template:form_elem label="Date*" path="date" value="${date}" type="date"/>
                <fmt:formatDate value="${inspectionDto.time}" var="time" pattern="HH:mm:ss"/>
                <template:form_elem label="Time*" path="time" value="${time}" type="time"/>
                <template:form_elem label="Comment" path="comment" type="textarea"/>
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
        </div>
    </form:form>
</template:page>