<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<template:page>
    <span>Count on page</span>
    <a href="<c:url value="/panel/deals?count=5"/>">5</a>
    <a href="<c:url value="/panel/deals?count=10"/>">10</a>
    <form method="post">
        <div class="information-text noselect">Deals list</div>
        <br/>
        <div class="table-custom">
            <table class="table">
                <thead>
                <tr>
                    <sec:authorize access="hasRole('ADMIN')">
                        <th class="right-border"></th>
                    </sec:authorize>
                    <th>ID</th>
                    <th>Application ID</th>
                    <th>Creation date</th>
                    <th>Price</th>
                    <th>Comission</th>
                </tr>
                </thead>
                <c:forEach var="deal" items="${pagedList.list}" varStatus="i">
                    <tr>
                        <sec:authorize access="hasRole('ADMIN')">
                            <td class="right-border">
                                <input name="checkedList[${i.index}].checked" type="checkbox" value="true"/>
                                <input type="hidden" name="checkedList[${i.index}].id"
                                       value="<c:out value="${deal.id}"/>"/>
                            </td>
                        </sec:authorize>
                        <td>
                            <a href="<c:url value="/panel/deals/${deal.id}"/>">
                                <c:out value="${deal.id}"/>
                            </a>
                        </td>
                        <td>
                            <a href="<c:url value="/panel/applications/${deal.application.id}"/>">
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
            <hr/>
            <sec:authorize access="hasAnyRole('ADMIN', 'REALTOR')">
                <a href="<c:url value="/panel/deals/new"/>" class="btn btn-success control-button">
                    Create
                </a>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN')">
                <button type="submit" name="remove" class="btn btn-danger control-button">
                    Remove
                </button>
            </sec:authorize>
            <sec:csrfInput/>
        </div>
    </form>
    <template:pagination targetPage="panel/deals" pagination="${pagedList.pagination}"/>
</template:page>