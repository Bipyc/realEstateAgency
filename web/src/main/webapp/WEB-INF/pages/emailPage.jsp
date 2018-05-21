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
                <div class="form-group">
                    <span>Template: </span>
                    <select id="selectTemplate" class="select-style">
                        <option value="0">None</option>
                        <option value="1">Invitation to the office</option>
                        <option value="2">Stock</option>
                    </select>
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
    <script>
        var templates = ["", "Hello {firstName} {lastName} " +
        "\n \nWe ask you to come to our office for additional questions.\n" +
        "\n" +
        "You can find out more by calling +375295553535",
            "Hello {firstName} {lastName} \n \n" +
            "Today we have the perfect offer for you!\n" +
            "We reduce commission 2 times!\n" +
            "We are waiting for you in our office!"];
        $(function () {
            // bind change event to select
            $("#selectTemplate").on('change', function () {
                var value = $("#selectTemplate").val();
                $("textarea[name=text]").val(templates[value]);
                return false;
            });
        });
    </script>
</template:page>
