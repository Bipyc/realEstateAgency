<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:page>
    <div class="about-img">
        <div class="information-text">Dream team</div>
        <br/>
        <div class="about-column">
            <a href="https://github.com/DmKoshelek" target="_blank">
                <img src="<c:url value="/resources/img/aboutUs/DKoshelev.jpg"/>"/>
            </a>
        </div>
        <div class="about-column">
            <a href="https://github.com/Bipyc" target="_blank">
                <img src="<c:url value="/resources/img/aboutUs/DKutas.jpg"/>"/>
            </a>
        </div>
        <div class="about-column">
            <a href="https://github.com/VladAleynikov" target="_blank">
                <img src="<c:url value="/resources/img/aboutUs/VAleynikov.jpg"/>"/>
            </a>
        </div>
    </div>
    <div class="about-context">
        <div class="about-column right-border about-text">
            Koshelek Dmitriy Michailovich
            <br/><br/>
            <b>O Mistress Mine</b><br/>
            O Mistress mine, where are you roaming?
            O, stay and hear; your true love's coming,
            That can sing both high and low:
            Trip no further, pretty sweeting;
            Journeys end in lovers meeting,
            Every wise man's son doth know.
            What is love? 'Tis not hereafter;
            Present mirth hath present laughter;
            What's to come is still unsure:
            In delay there lies not plenty;
            Then, come kiss me, sweet and twenty,
            Youth's a stuff will not endure.
            Trip no further, pretty sweeting;
            Journeys end in lovers meeting,
            Every wise man's son doth know.
            What is love? 'Tis not hereafter;
            Present mirth hath present laughter;
            Every wise man's son doth know.
        </div>
        <div class="about-column right-border about-text">
            Kutas Dmitriy Virusovich
            <br/><br/>
            <b>Love Sonnet 18</b><br/>
            Shall I compare thee to a summer's day?
            Thou art more lovely and more temperate:
            Rough winds do shake the darling buds of May,
            And summer's lease hath all too short a date:
            Sometime too hot the eye of heaven shines,
            And often is his gold complexion dimm'd;
            And every fair from fair sometime declines,
            By chance or nature's changing course untrimm'd;
            But thy eternal summer shall not fade
            Nor lose possession of that fair thou owest;
            Nor shall Death brag thou wander'st in his shade,
            When in eternal lines to time thou growest:
            So long as men can breathe or eyes can see,
            So long lives this and this gives life to thee.
        </div>
        <div class="about-column about-text">
            Aleynikov Vladislav Sergeevich
            <br/><br/>
            <b>Love Sonnet 147</b><br/>
            My love is as a fever, longing still
            For that which longer nurseth the disease,
            Feeding on that which doth preserve the ill,
            Th' uncertain sickly appetite to please.
            My reason, the physician to my love,
            Angry that his prescriptions are not kept,
            Hath left me, and I desperate now approve
            Desire is death, which physic did except.
            Past cure I am, now reason is past care,
            And frantic-mad with evermore unrest;
            My thoughts and my discourse as mad men's are,
            At random from the truth vainly expressed.
            For I have sworn thee fair, and thought thee bright,
            Who art as black as hell, as dark as night.
        </div>
    </div>


</template:page>