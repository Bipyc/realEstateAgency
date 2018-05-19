<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="span" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <div class="information-text outlined-text">${immobilityDto.name}</div>
    <label for="ownerLogin">Owner: </label>
    <span id="ownerLogin"><c:out value="${immobilityDto.ownerLogin}"/></span>
    <label for="name">Name: </label>
    <span id="name"><c:out value="${immobilityDto.name}"/></span>
    <label for="description">Description: </label>
    <span id="description"><c:out value="${immobilityDto.description}"/></span>
    <label for="price">Price: </label>
    <span id="price"><c:out value="${immobilityDto.price}"/></span>
    <label for="cityName">City: </label>
    <span id="cityName"><c:out value="${immobilityDto.cityName}"/></span>
    <label for="numberOfRooms">Number of rooms: </label>
    <span id="numberOfRooms"><c:out value="${immobilityDto.numberOfRooms}"/></span>
    <label for="square">Square: </label>
    <span id="square"><c:out value="${immobilityDto.square}"/></span>
    <label for="square">Address: </label>
    <span id="address"><c:out value="${immobilityDto.address}"/></span>
    <label for="square">Type of immobility: </label>
    <span id="address"><c:out value="${immobilityDto.typeImmobility}"/></span>
    <div id="gallery">
        <c:forEach var="photo" items="${immobilityDto.photos}" varStatus="i">
            <div>
                <img class="previewImage" src="<c:url value="/images/${photo.path}"/>"/>
            </div>
        </c:forEach>
    </div>
    <a href="<c:url value="/inspection/${immobilityDto.id}"/> " class="btn btn-success control-button">
        Order for inspection
    </a>


    </div>
</template:page>