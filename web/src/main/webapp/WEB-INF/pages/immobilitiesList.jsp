<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page catalogTabIsActive="${true}">
    <form method="post">
        <table class="table table-hover">
            <thead>
            <tr>
                <th></th>
                <th>Photo</th>
                <th>Name</th>
                <th>City</th>
                <th>Price</th>
            </tr>
            </thead>
            <c:forEach var="immobilities" items="${pagedList.list}" varStatus="i">
                <tr>
                    <td>
                        <input name="checkedList[${i.index}].checked" type="checkbox" value="true"/>
                        <input type="hidden" name="checkedList[${i.index}].id"
                               value="<c:out value="${immobilities.id}"/>"/>
                    </td>
                    <td>
                        <a href="<c:url value="/immobilities/${immobilities.id}"/>"><c:out
                                value="${immobilities.name}"/></a>
                    </td>
                    <td>
                        <a href="<c:url value="/immobilities/${immobilities.id}"/>"><c:out
                                value="${immobilities.name}"/></a>
                    </td>
                    <td>
                        <c:out value="${immobilities.city.name}"/>
                    </td>
                    <td>
                        <c:out value="${immobilities.price}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <sec:authorize access="hasRole('CLIENT')">
            <a href="<c:url value="/immobilities/new"/>" class="btn btn-success">
                Create
            </a>
        </sec:authorize>
        <button type="submit" name="remove" class="btn btn-danger">
            Remove
        </button>
        <sec:csrfInput/>
    </form>
    <template:pagination targetPage="users" pagination="${pagedList.pagination}"/>
</template:page>