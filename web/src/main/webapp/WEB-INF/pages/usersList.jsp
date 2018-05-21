<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <form method="post">
        <div class="information-text">Users list</div>
        <br />
        <div class="table-custom">
            <table class="table">
                <thead>
                <tr>
                    <th class="right-border"></th>
                    <th>Login</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th class="right-border">Patronymic</th>
                    <th>Action</th>
                </tr>
                </thead>
                <c:forEach var="user" items="${pagedList.list}" varStatus="i">
                    <tr>
                        <td class="right-border">
                            <input name="checkedList[${i.index}].checked" type="checkbox" value="true"/>
                            <input type="hidden" name="checkedList[${i.index}].id" value="<c:out value="${user.id}"/>"/>
                        </td>
                        <td>
                            <a href="<c:url value="/panel/users/${user.id}"/>"><c:out value="${user.login}"/></a>
                        </td>
                        <td>
                            <c:out value="${user.firstName}"/>
                        </td>
                        <td>
                            <c:out value="${user.lastName}"/>
                        </td>
                        <td class="right-border">
                            <c:out value="${user.patronymic}"/>
                        </td>
                        <td>
                            <a href="<c:url value="/panel/email?em=${user.email}"/>" class="btn btn-info">
                                Send Email
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <hr/>
            <a href="<c:url value="/panel/users/new"/>" class="btn btn-success control-button">
                Create
            </a>
            <button type="submit" name="remove" class="btn btn-danger control-button">
                Remove
            </button>
            <button type="submit" name="sendEmail" class="btn btn-info control-button">
                Send Email
            </button>
            <sec:csrfInput/>
        </div>
    </form>
    <template:pagination targetPage="users" pagination="${pagedList.pagination}"/>
</template:page>