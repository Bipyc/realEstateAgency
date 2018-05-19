<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <form:form method="get"  modelAttribute="sf">
        <form:input path="cityName"/>
        <select id="typeImmobility" name="typeImmobility" class="select-style">
            <option ${sf.typeImmobility eq "HOUSE" ? 'selected' : ''} value="HOUSE">House
            </option>
            <option ${sf.typeImmobility eq "APARTMENT" ? 'selected' : ''} value="APARTMENT">
                Apartment
            </option>
        </select>
        <select name="typeApplication" class="select-style">
            <c:forEach var="type" items="${typeApplications}">
                <option ${sf.typeApplication eq type.id ? 'selected' : ''} value="${type.id}"><c:out
                        value="${type.name}"/></option>
            </c:forEach>
        </select>
        <form:input type="numeric" path="minPrice"/>
        <form:input type="numeric" path="maxPrice"/>
        <button type="submit" class="btn btn-success control-button">
            Create
        </button>
        <sec:csrfInput/>
    </form:form>
    <div class="information-text">Immobility list</div>
    <br/>
    <div class="table-custom">
        <table class="table">
            <thead>
            <tr>
                <th class="right-border">Photo</th>
                <th>Name</th>
                <th>City</th>
                <th>Type</th>
                <th>Price</th>
            </tr>
            </thead>
            <c:forEach var="immobilities" items="${pagedList.list}" varStatus="i">
                <tr>
                    <td class="right-border">
                        <a href="<c:url value="/catalog/${immobilities.id}"/>">
                            <c:choose>
                                <c:when test="${empty immobilities.photos || empty immobilities.photos[0]}">
                                    <img src="<c:url value="/resources/img/default.png"/>" width="auto"
                                         height="100"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="<c:url value="/images"/>/<c:out value="${immobilities.photos[0].path}"/>"
                                         width="auto" height="100"/>
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </td>
                    <td>
                        <a href="<c:url value="/catalog/${immobilities.id}"/>"><c:out
                                value="${immobilities.name}"/></a>
                    </td>
                    <td>
                        <c:out value="${immobilities.city.name}"/>
                    </td>
                    <td>
                        <c:out value="${immobilities.type}"/>
                    </td>
                    <td>
                        <c:out value="${immobilities.price}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <hr/>

    </div>
    <template:pagination targetPage="catalog" pagination="${pagedList.pagination}" queries="${paginationParams}"/>
</template:page>