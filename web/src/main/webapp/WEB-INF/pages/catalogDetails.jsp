<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="span" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <div class="immobility-information">
        <a href="<c:url value="/inspection/${immobilityDto.id}"/> " class="btn btn-info login-button">
            Order for inspection
        </a>
        <hr/>
        <div class="information-text outlined-text">${immobilityDto.name}</div>
        <hr/>

        <div class="information-description-block">
            <label for="ownerLogin">Owner: </label>
            <span id="ownerLogin"><c:out value="${immobilityDto.ownerLogin}"/></span>
            <br/>
            <label for="description">Description: </label>
            <span id="description"><c:out value="${immobilityDto.description}"/></span>
            <br/>
            <label for="price">Price: </label>
            <span id="price">$<c:out value="${immobilityDto.price}"/></span>
            <br/>
            <label for="cityName">City: </label>
            <span id="cityName"><c:out value="${immobilityDto.cityName}"/></span>
            <br/>
            <label for="numberOfRooms">Number of rooms: </label>
            <span id="numberOfRooms"><c:out value="${immobilityDto.numberOfRooms}"/></span>
            <br/>
            <label for="square">Square: </label>
            <span id="square"><c:out value="${immobilityDto.square}"/> m2</span>
            <br/>
            <label for="square">Address: </label>
            <span id="address"><c:out value="${immobilityDto.address}"/></span>
            <br/>
            <label for="square">Type of immobility: </label>
            <span id="address"><c:out value="${immobilityDto.typeImmobility}"/></span>
            <br/>
        </div>

        <hr/>
        <div class="information-text">Photos</div>
        <hr/>
        <br/>
        <div id="gallery">
            <c:forEach var="photo" items="${immobilityDto.photos}" varStatus="i">
                <div>
                    <img src="<c:url value="/images/${photo.path}"/>"/>
                </div>
            </c:forEach>
        </div>
        <a href="<c:url value="/inspection/${immobilityDto.id}"/> " class="btn btn-info login-button">
            Order for inspection
        </a>
    </div>
</template:page>