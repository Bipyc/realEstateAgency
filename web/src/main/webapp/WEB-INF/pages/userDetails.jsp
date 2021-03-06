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
            <c:when test="${not empty registration}">
                <p class="information-text noselect">Registration</p>
            </c:when>
            <c:when test="${not empty createUser}">
                <p class="information-text noselect">Create user</p>
            </c:when>
            <c:when test="${not empty showUserProfile}">
                <p class="information-text noselect">Profile</p>
            </c:when>
            <c:otherwise>
                <p class="information-text noselect">Edit user</p>
            </c:otherwise>
        </c:choose>
        <br/>
        <div class="auth-block">
            <div class="auth-record">
                <div class="choise">
                    <c:choose>
                        <c:when test="${not empty registration}">
                            <form:hidden path="typeUser"/>
                        </c:when>
                        <c:when test="${not empty createUser}">
                            <div class="form-group">
                                <span>User type: </span>
                                <select id="typeUser" name="typeUser" class="select-style">
                                    <option ${typeUser eq "CLIENT" ? 'selected' : ''} value="CLIENT">Client</option>
                                    <option ${typeUser eq "REALTOR" ? 'selected' : ''} value="REALTOR">Realtor</option>
                                    <option ${typeUser eq "ADMIN" ? 'selected' : ''} value="ADMIN">Admin</option>
                                </select>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <form:hidden path="typeUser"/>
                            <div class="form-group">
                                <span>User type: </span>
                                <span>${userDto.typeUser}</span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:choose>
                    <c:when test="${empty showUserProfile}">
                        <template:form_elem label="Login*" path="login"/>
                    </c:when>
                    <c:otherwise>
                        <form:hidden path="login"/>
                        <div class="form-group">
                            <span>Login: </span>
                            <span>${userDto.login}</span>
                        </div>
                    </c:otherwise>
                </c:choose>
                <template:form_elem label="First name*" path="firstName"/>
                <template:form_elem label="Last name*" path="lastName"/>
                <template:form_elem label="Patronymic*" path="patronymic"/>
                <template:form_elem label="Email*" path="email"/>
                <template:form_elem label="Password*" path="password" type="password"/>
                <fmt:formatDate value="${userDto.dateOfBirth}" var="dateOfBirth" pattern="yyyy-MM-dd"/>
                <template:form_elem label="Date of Birth*" path="dateOfBirth" value="${dateOfBirth}" type="date"/>
                <template:form_elem label="Passport number*" path="number"/>
                <fmt:formatDate value="${userDto.dateOfIssue}" var="dateOfIssue" pattern="yyyy-MM-dd"/>
                <template:form_elem label="Date of Issue*" path="dateOfIssue" value="${dateOfIssue}" type="date"/>
                <template:form_elem label="Identification Number*" path="identificationNumber"/>
                <template:form_elem label="Issuing authority*" path="authority"/>
                <template:form_elem label="Phone*" path="phone"/>
                <c:choose>
                    <c:when test="${userDto.typeUser ne \"CLIENT\" && typeUser ne \"CLIENT\" && empty showUserProfile}">
                        <fmt:formatDate value="${userDto.dateOfIssue}" var="employmentDate" pattern="yyyy-MM-dd"/>
                        <template:form_elem label="Employment Date*" path="employmentDate" value="${employmentDate}"
                                            type="date"/>
                        <template:form_elem label="Salary*" type="number" path="salary"/>
                    </c:when>
                    <c:otherwise>
                        <form:hidden path="employmentDate" value="1970-01-01"/>
                        <form:hidden path="salary" value="0"/>
                    </c:otherwise>
                </c:choose>
            </div>
            <br/>
            <div class="form-group">
                <button type="submit" name="save" class="btn btn-success login-button">
                    Save
                </button>
                <c:if test="${empty createUser && empty registration && empty showUserProfile}">
                    <button type="submit" name="remove" class="btn btn-danger login-button">
                        Remove
                    </button>
                </c:if>
            </div>
            <sec:csrfInput/>
        </div>
    </form:form>
    <script>
        $(function () {
            // bind change event to select
            $('#typeUser').on('change', function () {
                var value = $(this).val(); // get selected value
                if (value) { // require a URL
                    window.location = window.location.href.split('?')[0] + "?typeUser=" + value; // redirect
                }
                return false;
            });
        });
    </script>
</template:page>