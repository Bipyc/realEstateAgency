<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
<div class="document-page">
    <div class="information-text noselect">Documents</div>
    <br/>
    <sec:authorize access="hasRole('ADMIN')">
        <div class="table-custom">
            <table class="table">
                <thead>
                <tr>
                    <th colspan="3">City Statistic</th>
                </tr>
                </thead>
                <tr>
                    <td class="right-border"><a target="_blank" href="<c:url value="/document?type=0&documentId=0"/>">pdf</a></td>
                    <td class="right-border"><a target="_blank" href="<c:url value="/document?type=1&documentId=0"/>">xlsx</a></td>
                    <td><a target="_blank" href="<c:url value="/document?type=2&documentId=0"/>">csv</a></td>
                </tr>
            </table>
        </div>
        <br/>
        <div class="table-custom">
            <table class="table">
                <thead>
                <tr>
                    <th colspan="3">Realtor Report</th>
                </tr>
                </thead>
                <tr>
                    <td class="right-border"><a target="_blank" href="<c:url value="/document?type=0&documentId=2"/>">pdf</a></td>
                    <td class="right-border"><a target="_blank" href="<c:url value="/document?type=1&documentId=2"/>">xlsx</a></td>
                    <td><a target="_blank" href="<c:url value="/document?type=2&documentId=2"/>">csv</a></td>
                </tr>
            </table>
        </div>
        <br/>
        <div class="table-custom">
            <table class="table">
                <thead>
                <tr>
                    <th colspan="3">Year Statistic</th>
                </tr>
                </thead>
                <tr>
                    <td class="right-border"><a target="_blank" href="<c:url value="/document?type=0&documentId=4"/>">pdf</a></td>
                    <td class="right-border"><a target="_blank" href="<c:url value="/document?type=1&documentId=4"/>">xlsx</a></td>
                    <td><a target="_blank" href="<c:url value="/document?type=2&documentId=4"/>">csv</a></td>
                </tr>
            </table>
        </div>
    </sec:authorize>

    <sec:authorize access="hasAnyRole('REALTOR', 'CLIENT')">
        <div class="table-custom">
            <table class="table">
                <thead>
                <tr>
                    <th colspan="3">History Deals</th>
                </tr>
                </thead>
                <tr>
                    <td class="right-border"><a target="_blank" href="<c:url value="/document?type=0&documentId=1"/>">pdf</a></td>
                    <td class="right-border"><a target="_blank"  href="<c:url value="/document?type=1&documentId=1"/>">xlsx</a></td>
                    <td><a target="_blank" href="<c:url value="/document?type=2&documentId=1"/>">csv</a></td>
                </tr>
            </table>
        </div>
        <br/>
        <div class="table-custom">
            <table class="table">
                <thead>
                <tr>
                    <th colspan="3">Visiting Schedule</th>
                </tr>
                </thead>
                <tr>
                    <td class="right-border"><a target="_blank" href="<c:url value="/document?type=0&documentId=3"/>">pdf</a></td>
                    <td class="right-border"><a target="_blank" href="<c:url value="/document?type=1&documentId=3"/>">xlsx</a></td>
                    <td><a target="_blank"  href="<c:url value="/document?type=2&documentId=3"/>">csv</a></td>
                </tr>
            </table>
        </div>
    </sec:authorize>
</div>
</template:page>