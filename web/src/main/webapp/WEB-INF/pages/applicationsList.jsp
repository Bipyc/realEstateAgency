<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<template:page>
    <div class="information-text">Order list</div>
    <br/>
    <form method="post">
        <div class="table-custom">
            <table class="table">
                <thead>
                <tr>
                    <th class="right-border"></th>
                    <th>ID</th>
                    <th>Immobility ID</th>
                    <th>Creation date</th>
                    <th>Realtor</th>
                    <th>Status</th>
                </tr>
                </thead>
                <c:forEach var="application" items="${pagedList.list}" varStatus="i">
                    <tr>
                        <td class="right-border">
                            <input name="checkedList[${i.index}].checked" type="checkbox" value="true"/>
                            <input type="hidden" name="checkedList[${i.index}].id"
                                   value="<c:out value="${application.id}"/>"/>
                        </td>
                        <td>
                            <a href="<c:url value="/panel/applications/${application.id}"/>">
                                <c:out value="${application.id}"/>
                            </a>
                        </td>
                        <td>
                            <a href="<c:url value="/panel/immobility/${application.immobility.id}"/>">
                                <c:out value="${application.immobility.id}"/>
                            </a>
                        </td>
                        <td>
                            <fmt:formatDate value="${application.date}" var="date" pattern="yyyy-MM-dd"/>
                            <c:out value="${date}"/>
                        </td>
                        <td>
                            <c:out value="${application.realtor.login}"/>
                        </td>
                        <td>
                            <c:out value="${application.status}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <hr/>
            <a href="<c:url value="/panel/applications/new"/>" class="btn btn-success control-button">
                Create
            </a>
            <button type="submit" name="remove" class="btn btn-danger control-button">
                Remove
            </button>
        </div>

        <sec:csrfInput/>
    </form>
    <template:pagination targetPage="panel/applications" pagination="${pagedList.pagination}"/>
</template:page>