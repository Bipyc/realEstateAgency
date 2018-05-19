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
            <div class="information-text outlined-text">Create immobility</div>
        </c:when>
        <c:otherwise>
            <div class="information-text outlined-text">Edit immobility</div>
        </c:otherwise>
    </c:choose>
    <br/>
    <form:form method="post" enctype="multipart/form-data" modelAttribute="inspectionDto">
        <sec:csrfInput/>
        <div class="auth-block">
            <div class="auth-record">
                <template:form_elem label="Immobility ID*" path="immobilityId"/>
                <template:form_elem label="Realtor*" path="realtorName"/>
                <template:form_elem label="Client*" path="clientName"/>
                <fmt:formatDate value="${inspectionDto.date}" var="date" pattern="yyyy-MM-dd"/>
                <template:form_elem label="Date*" path="date" value="${date}" type="date"/>
                <fmt:formatDate value="${inspectionDto.time}" var="time" pattern="HH:mm:ss"/>
                <template:form_elem label="Time*" path="time" value="${time}" type="time"/>
                <template:form_elem label="Comment" path="comment" type="textarea"/>
            </div>
            <div class="form-group">
                <button type="submit" name="save" class="btn btn-success">
                    Save
                </button>
                <c:if test="${empty create}">
                    <button type="submit" name="remove" class="btn btn-danger">
                        Remove
                    </button>
                </c:if>
            </div>
        </div>
    </form:form>
</template:page>