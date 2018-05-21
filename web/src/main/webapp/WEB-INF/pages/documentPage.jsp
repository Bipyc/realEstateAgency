<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <table>
        <sec:authorize access="hasRole('ADMIN')">
            <tr>
                <td>City Statistic</td>
                <td><a target="_blank" href="<c:url value="/document?type=0&documentId=0"/>">pdf</a></td>
                <td><a target="_blank" href="<c:url value="/document?type=1&documentId=0"/>">xlsx</a></td>
                <td><a target="_blank" href="<c:url value="/document?type=2&documentId=0"/>">csv</a></td>
            </tr>
            <tr>
                <td>Realtor Report</td>
                <td><a target="_blank" href="<c:url value="/document?type=0&documentId=2"/>">pdf</a></td>
                <td><a target="_blank" href="<c:url value="/document?type=1&documentId=2"/>">xlsx</a></td>
                <td><a target="_blank" href="<c:url value="/document?type=2&documentId=2"/>">csv</a></td>
            </tr>
            <tr>
                <td>Year Statistic</td>
                <td><a target="_blank" href="<c:url value="/document?type=0&documentId=4"/>">pdf</a></td>
                <td><a target="_blank" href="<c:url value="/document?type=1&documentId=4"/>">xlsx</a></td>
                <td><a target="_blank" href="<c:url value="/document?type=2&documentId=4"/>">csv</a></td>
            </tr>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('REALTOR', 'CLIENT')">
            <tr>
                <td>History Deals</td>
                <td><a target="_blank" href="<c:url value="/document?type=0&documentId=1"/>">pdf</a></td>
                <td><a target="_blank" href="<c:url value="/document?type=1&documentId=1"/>">xlsx</a></td>
                <td><a target="_blank" href="<c:url value="/document?type=2&documentId=1"/>">csv</a></td>
            </tr>
            <tr>
                <td>Visiting Schedule</td>
                <td><a target="_blank" href="<c:url value="/document?type=0&documentId=3"/>">pdf</a></td>
                <td><a target="_blank" href="<c:url value="/document?type=1&documentId=3"/>">xlsx</a></td>
                <td><a target="_blank" href="<c:url value="/document?type=2&documentId=3"/>">csv</a></td>
            </tr>
        </sec:authorize>
    </table>
</template:page>