<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:error>
    <div>
        Forbidden!
    </div>
    <p class="outlined-text"><span class="outlined-text">error 403 !</span>you do not have enough permissions<br/><button class="btn btn-prim" onclick="history.back()">Back</button></p>
</template:error>
