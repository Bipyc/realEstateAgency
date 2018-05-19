<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<template:page>
    <form method="post">
        <div class="information-text">Immobility list</div>
        <div class="table-custom">
            <table class="table">
                <thead>
                <tr>
                    <th class="right-border"></th>
                    <th>ID</th>
                    <th>Immobility</th>
                    <th>Date</th>
                    <th>Time</th>
                </tr>
                </thead>
                <c:forEach var="inspection" items="${pagedList.list}" varStatus="i">
                    <tr>
                        <td class="right-border">
                            <input name="checkedList[${i.index}].checked" type="checkbox" value="true"/>
                            <input type="hidden" name="checkedList[${i.index}].id"
                                   value="<c:out value="${inspection.id}"/>"/>
                        </td>
                        <td>
                            <a href="<c:url value="/inspection/${inspection.id}"/>">
                                <c:out value="${inspection.id}"/>
                            </a>
                        </td>
                        <td>
                            <a href="<c:url value="/immobilities/${inspection.immobility.id}"/>"><c:out
                                    value="${inspection.immobility.name}"/></a>
                        </td>
                        <td>
                            <fmt:formatDate value="${inspection.date}" var="date" pattern="yyyy-MM-dd"/>
                            <c:out value="${date}"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${inspection.time}" var="time" pattern="HH:mm"/>
                            <c:out value="${time}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <hr/>
            <a href="<c:url value="/inspections/new"/>" class="btn btn-success control-button">
                Create
            </a>
            <button type="submit" name="remove" class="btn btn-danger control-button">
                Remove
            </button>
            <sec:csrfInput/>
        </div>
    </form>
    <template:pagination targetPage="inspections" pagination="${pagedList.pagination}"/>
</template:page>