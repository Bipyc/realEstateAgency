<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page isDark="false">
    <h1>Discover a place you’ll love to live</h1>
    <p>Here you can find flats for rent or for sale. Let's start!</p>
    <a class="btn btn-prim" href="<c:url value="/catalog"/>">Catalog</a>
    <a class="btn btn-prim" href="<c:url value="/about"/>" >About us</a>
</template:page>
