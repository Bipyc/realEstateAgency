<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="span" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <form:form method="post" modelAttribute="applicationDto">
        <p class="information-text">Order</p>
        <div class="auth-block">
            <div class="auth-record">
                <c:if test="${empty create}">
                    <div class="form-group">
                        <span>Date:</span>
                        <fmt:formatDate value="${applicationDto.date}" var="date" pattern="yyyy-MM-dd"/>
                        <span><c:out value="${date}"/></span>
                    </div>
                </c:if>
                <template:form_elem label="Realtor*" path="realtorName"/>
                <template:form_elem label="Immobility ID*" type="number" path="immobilityId"/>
                <div class="choise">
                    <div class="form-group">
                        <span>Type:</span>
                        <select name="typeId" class="select-style">
                            <c:forEach var="type" items="${typeApplications}">
                                <option ${applicationDto.typeId eq type.id ? 'selected' : ''} value="${type.id}"><c:out
                                        value="${type.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="choise">
                    <div class="form-group">
                        <span>Status:</span>
                        <select name="status" class="select-style">
                            <option ${applicationDto.status eq "OPEN" ? 'selected' : ''} value="OPEN">Open</option>
                            <option ${applicationDto.status eq "CANCELED" ? 'selected' : ''} value="CANCELED">Canceled
                            </option>
                            <option ${applicationDto.status eq "ACTIVE" ? 'selected' : ''} value="ACTIVE">Active
                            </option>
                            <option ${applicationDto.status eq "CLOSE" ? 'selected' : ''} value="CLOSE">Close</option>
                        </select>
                    </div>
                </div>
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