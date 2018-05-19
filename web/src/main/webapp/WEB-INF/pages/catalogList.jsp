<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <form:form method="get" modelAttribute="sf" class="search-form">
        <div class="container-fluid">
            <div class="search">
                <div class="information-text">Search</div>
                <br/>
                <div class="col-sm-3"></div>
                <div class="col-sm-6">
                    <div class="row">

                        <div class="col-sm-6">
                            <label class="outlined-text align-middle">City: </label>
                        </div>
                        <div class="col-sm-6">
                            <form:input class="input-field" path="cityName"/>
                        </div>

                    </div>
                    <div class="row">

                        <div class="col-sm-6">
                            <label class="outlined-text">Immobility type: </label>
                        </div>
                        <div class="col-sm-6">
                            <select id="typeImmobility" name="typeImmobility" class="select-style">
                                <option ${sf.typeImmobility eq "HOUSE" ? 'selected' : ''} value="HOUSE">House
                                </option>
                                <option ${sf.typeImmobility eq "APARTMENT" ? 'selected' : ''} value="APARTMENT">
                                    Apartment
                                </option>
                            </select>
                        </div>

                    </div>
                    <div class="row">

                        <div class="col-sm-6">
                            <label class="outlined-text">Order type</label>
                        </div>
                        <div class="col-sm-6">
                            <select name="typeApplication" class="select-style">
                                <c:forEach var="type" items="${typeApplications}">
                                    <option ${sf.typeApplication eq type.id ? 'selected' : ''} value="${type.id}"><c:out
                                            value="${type.name}"/></option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>
                    <div class="row">

                        <div class="col-sm-6">
                            <label class="outlined-text">min price</label>
                        </div>
                        <div class="col-sm-6">
                            <form:input class="input-field" type="number" step="50" min="0" path="minPrice"/>
                        </div>

                    </div>
                    <div class="row">

                        <div class="col-sm-6">
                            <label class="outlined-text">max price</label>
                        </div>
                        <div class="col-sm-6">
                            <form:input class="input-field" type="number" step="50" min="0" path="maxPrice"/>
                        </div>

                    </div>
                    <div class="row">

                        <button class="btn btn-info" type="submit"><i class="fa fa-search"></i></button>

                    </div>
                </div>
                <div class="col-sm-3"></div>

            </div>
        </div>
        <sec:csrfInput/>
    </form:form>
    <div class="information-text">Catalog</div>
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