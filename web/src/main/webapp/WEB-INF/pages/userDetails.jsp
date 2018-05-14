<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="span" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <form:form method="post" modelAttribute="userDto">
        <c:choose>
            <c:when test="${not empty createUser}">
                <select id="typeUser" name="typeUser">
                    <option ${typeUser eq "CLIENT" ? 'selected' : ''} value="CLIENT">Client</option>
                    <option ${typeUser eq "REALTOR" ? 'selected' : ''} value="REALTOR">Realtor</option>
                    <option ${typeUser eq "ADMIN" ? 'selected' : ''} value="ADMIN">Admin</option>
                </select>
            </c:when>
            <c:otherwise>
                <form:hidden path="typeUser"/>
                <div class="form-group">
                    <span>TypeUser:</span>
                    <span>${userDto.typeUser}</span>
                </div>
            </c:otherwise>
        </c:choose>
        <div class="form-group">
            <label for="login">Login*</label>
            <form:input path="login" class="form-control" id="login"/>
            <div class="error-message" id="error-message">
                <form:errors path="login"/>
            </div>
        </div>
        <div class="form-group">
            <label for="firstName">First name*</label>
            <form:input path="firstName" class="form-control" id="firstName"/>
            <div class="error-message" id="error-message">
                <form:errors path="firstName"/>
            </div>
        </div>
        <div class="form-group">
            <label for="lastName">Last name*</label>
            <form:input path="lastName" class="form-control" id="lastName"/>
            <div class="error-message" id="error-message">
                <form:errors path="lastName"/>
            </div>
        </div>
        <div class="form-group">
            <label for="patronymic">Patronymic*</label>
            <form:input path="patronymic" class="form-control" id="patronymic"/>
            <div class="error-message" id="error-message">
                <form:errors path="patronymic"/>
            </div>
        </div>
        <div class="form-group">
            <label for="email">Email*</label>
            <form:input path="email" class="form-control" id="email"/>
            <div class="error-message" id="email-message">
                <form:errors path="email"/>
            </div>
        </div>
        <div class="form-group">
            <label for="phone">Phone*</label>
            <form:input path="phone" class="form-control" id="phone"/>
            <div class="error-message" id="error-message">
                <form:errors path="phone"/>
            </div>
        </div>
        <div class="form-group">
            <label for="password">Password*</label>
            <form:password path="password" class="form-control" id="password"/>
            <div class="error-message" id="error-message">
                <form:errors path="password"/>
            </div>
        </div>
        <div class="form-group">
            <label for="dateOfBirth">Date of Birth*</label>
            <fmt:formatDate value="${userDto.dateOfBirth}" var="dateString" pattern="yyyy-MM-dd" />
            <form:input type="date" path="dateOfBirth" value="${dateString}" class="form-control" id="dateOfBirth"/>
            <div class="error-message" id="error-message">
                <form:errors path="dateOfBirth"/>
            </div>
        </div>
        <div class="form-group">
            <label for="number">Passport number*</label>
            <form:input path="number" class="form-control" id="number"/>
            <div class="error-message" id="error-message">
                <form:errors path="number"/>
            </div>
        </div>
        <div class="form-group">
            <label for="dateOfIssue">Date of Issue*</label>
            <fmt:formatDate value="${userDto.dateOfIssue}" var="dateStringIssue" pattern="yyyy-MM-dd" />
            <form:input type="date" path="dateOfIssue" value="${dateStringIssue}" class="form-control" id="dateOfIssue"/>
            <div class="error-message" id="error-message">
                <form:errors path="dateOfIssue"/>
            </div>
        </div>
        <div class="form-group">
            <label for="identificationNumber">Identification Number*</label>
            <form:input path="identificationNumber" class="form-control" id="identificationNumber"/>
            <div class="error-message" id="error-message">
                <form:errors path="identificationNumber"/>
            </div>
        </div>
        <div class="form-group">
            <label for="authority">Issuing authority*</label>
            <form:input path="authority" class="form-control" id="authority"/>
            <div class="error-message" id="error-message">
                <form:errors path="authority"/>
            </div>
        </div>
        <c:choose>
            <c:when test="${userDto.typeUser ne \"CLIENT\" && typeUser ne \"CLIENT\"}">
                <div class="form-group">
                    <label for="employmentDate">EmploymentDate*</label>
                    <fmt:formatDate value="${userDto.employmentDate}" var="dateString" pattern="yyyy-MM-dd" />
                    <form:input type="date" path="employmentDate" value="${dateString}" class="form-control" id="employmentDate"/>
                    <div class="error-message" id="error-message">
                        <form:errors path="employmentDate"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="salary">Salary*</label>
                    <form:input path="salary" class="form-control" id="salary"/>
                    <div class="error-message" id="error-message">
                        <form:errors path="salary"/>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <form:hidden path="employmentDate" value="1970-01-01"/>
                <form:hidden path="salary" value="0"/>
            </c:otherwise>
        </c:choose>
        <div class="form-group">
            <button type="submit" name="save" class="btn btn-success">
                Save
            </button>
            <c:if test="${empty createUser}">
                <button type="submit" name="remove" class="btn btn-danger">
                    Remove
                </button>
            </c:if>
        </div>
        <sec:csrfInput/>
    </form:form>
    <script>
        $(function(){
            // bind change event to select
            $('#typeUser').on('change', function () {
                var value = $(this).val(); // get selected value
                if (value) { // require a URL
                    window.location = window.location.href.split('?')[0]+"?typeUser="+value; // redirect
                }
                return false;
            });
        });
    </script>
</template:page>