<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:error>
    <div>
        Server Error!
    </div>
    <p class="outlined-text"><span class="outlined-text">error 500 !</span> Sorry! Unexpected error<br/><button class="btn btn-prim" onclick="history.back()">Back</button></p>
</template:error>