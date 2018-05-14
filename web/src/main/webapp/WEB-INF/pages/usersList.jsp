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
                <th>Login</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Patronymic</th>
                <th>Action</th>
            </tr>
            </thead>
            <c:forEach var="user" items="${pagedList.list}" varStatus="i">
                <tr>
                    <td>
                        <input name="checkedList[${i.index}].checked" type="checkbox" value="true"/>
                        <input type="hidden" name="checkedList[${i.index}].id" value="<c:out value="${user.id}"/>"/>
                    </td>
                    <td>
                        <a href="<c:url value="/users/${user.id}"/>"><c:out value="${user.login}"/></a>
                    </td>
                    <td>
                        <c:out value="${user.firstName}"/>
                    </td>
                    <td>
                        <c:out value="${user.lastName}"/>
                    </td>
                    <td>
                        <c:out value="${user.patronymic}"/>
                    </td>
                    <td>
                        <button type="submit" name="email" class="btn btn-info">
                            Send Email
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="<c:url value="/users/new"/>" class="btn btn-success">
            Create
        </a>
        <button type="submit" name="remove" class="btn btn-danger">
            Remove
        </button>
        <sec:csrfInput/>
    </form>
    <template:pagination targetPage="users" pagination="${pagedList.pagination}"/>
</template:page>