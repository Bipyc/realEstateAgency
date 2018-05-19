<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ attribute name="path" type="java.lang.String" required="true" %>
<%@ attribute name="label" type="java.lang.String" required="true" %>
<%@ attribute name="value" type="java.lang.String" required="false" %>
<%@ attribute name="type" type="java.lang.String" required="false" %>
<div class="form-group">
    <label class="outlined-text" for="${path}">${label}</label>
    <c:choose>
        <c:when test="${type eq 'password'}">
            <form:password path="${path}" class="form-control input-field" id="${path}"/>
        </c:when>
        <c:when test="${type eq 'textarea'}">
            <form:textarea path="${path}" class="form-control input-field" id="${path}"/>
        </c:when>
        <c:when test="${not empty value}">
            <form:input path="${path}" type="${not empty type ? type: 'input'}" class="form-control input-field"
                        id="${path}" value="${value}"/>
        </c:when>
        <c:otherwise>
            <form:input path="${path}" type="${not empty type ? type: 'input'}" class="form-control input-field"
                        id="${path}"/>
        </c:otherwise>
    </c:choose>

    <div class="error-message" id="error-message">
        <form:errors path="${path}"/>
    </div>
</div>
