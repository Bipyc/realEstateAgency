<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <form method="post">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Commission</th>
            </tr>
            </thead>
            <c:forEach var="typeApplication" items="${pagedList.list}" varStatus="i">
                <tr>
                    <td>
                        <a href="<c:url value="/typeApplications/${typeApplication.id}"/>"><c:out
                                value="${typeApplication.name}"/></a>
                    </td>
                    <td>
                        <c:out value="${typeApplication.commission}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="<c:url value="/typeApplications/new"/>" class="btn btn-success">
            Create
        </a>
        <sec:csrfInput/>
    </form>
    <template:pagination targetPage="users" pagination="${pagedList.pagination}"/>
</template:page>