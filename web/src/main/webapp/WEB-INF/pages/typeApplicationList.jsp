<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <span>Count on page</span>
    <a href="<c:url value="/panel/typeApplications?count=5"/>">5</a>
    <a href="<c:url value="/panel/typeApplications?count=10"/>">10</a>
    <form method="post">
        <div class="information-text noselect">Comission</div>
        <br/>
        <div class="table-custom">
            <table class="table">
                <thead>
                <tr>
                    <th class="right-border">Name</th>
                    <th>Commission</th>
                </tr>
                </thead>
                <c:forEach var="typeApplication" items="${pagedList.list}" varStatus="i">
                    <tr>
                        <td class="right-border">
                            <a href="<c:url value="/panel/typeApplications/${typeApplication.id}"/>"><c:out
                                    value="${typeApplication.name}"/></a>
                        </td>
                        <td>
                            <c:out value="${typeApplication.commission}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <hr/>
            <a href="<c:url value="/panel/typeApplications/new"/>" class="btn btn-success control-button">
                Create
            </a>
            <sec:csrfInput/>
        </div>
    </form>
    <template:pagination targetPage="panel/typeApplications" pagination="${pagedList.pagination}"/>
</template:page>