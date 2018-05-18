<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <form method="post">
        <div class="information-text">Immobility list</div>
        <div class="table-custom">
            <table class="table">
                <thead>
                <tr>
                    <th class="right-border"></th>
                    <th class="right-border">Photo</th>
                    <th>Name</th>
                    <th>City</th>
                    <th>Price</th>
                </tr>
                </thead>
                <c:forEach var="immobilities" items="${pagedList.list}" varStatus="i">
                    <tr>
                        <td class="right-border">
                            <input name="checkedList[${i.index}].checked" type="checkbox" value="true"/>
                            <input type="hidden" name="checkedList[${i.index}].id"
                                   value="<c:out value="${immobilities.id}"/>"/>
                        </td>
                        <td class="right-border">
                            <a href="<c:url value="/immobilities/${immobilities.id}"/>">
                                <img src="<c:url value="/images"/>/<c:out value="${empty immobilities.photos ||
                            empty immobilities.photos[0] ? 'default.png' : immobilities.photos[0].path}"/>"
                                     width="auto" height="100"/>
                            </a>
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
            <hr />
            <sec:authorize access="hasRole('CLIENT')">
                <a href="<c:url value="/immobilities/new"/>" class="btn btn-success control-button">
                    Create
                </a>
            </sec:authorize>
            <button type="submit" name="remove" class="btn btn-danger control-button">
                Remove
            </button>
            <sec:csrfInput/>
        </div>
    </form>
    <template:pagination targetPage="users" pagination="${pagedList.pagination}"/>
</template:page>