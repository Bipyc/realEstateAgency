<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<template:page>
    <form method="post">
        <table class="table table-hover">
            <thead>
            <tr>
                <sec:authorize access="hasRole('ADMIN')">
                    <th></th>
                </sec:authorize>
                <th>Application ID</th>
                <th>Creation date</th>
                <th>Price</th>
                <th>Comission</th>
            </tr>
            </thead>
            <c:forEach var="deal" items="${pagedList.list}" varStatus="i">
                <tr>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td>
                            <input name="checkedList[${i.index}].checked" type="checkbox" value="true"/>
                            <input type="hidden" name="checkedList[${i.index}].id"
                                   value="<c:out value="${deal.id}"/>"/>
                        </td>
                    </sec:authorize>

                    <td>
                        <a href="<c:url value="/deals/${deal.id}"/>">
                            <c:out value="${deal.application.id}"/>
                        </a>
                    </td>
                    <td>
                        <fmt:formatDate value="${deal.date}" var="date" pattern="yyyy-MM-dd"/>
                        <c:out value="${date}"/>
                    </td>
                    <td>
                        <c:out value="${deal.price}"/>
                    </td>
                    <td>
                        <c:out value="${deal.commission}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="<c:url value="/deals/new"/>" class="btn btn-success">
            Create
        </a>
        <sec:authorize access="hasRole('ADMIN')">
            <button type="submit" name="remove" class="btn btn-danger">
                Remove
            </button>
        </sec:authorize>
        <sec:csrfInput/>
    </form>
    <template:pagination targetPage="deals" pagination="${pagedList.pagination}"/>
</template:page>