<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ attribute name="pagination" type="by.bsuir.realEstateAgency.web.bean.pagedList.Pagination" required="true" %>
<%@ attribute name="targetPage" type="java.lang.String" required="true" %>
<%@ attribute name="queries" type="java.lang.String" %>
<div class="center">
    <ul class="pagination">
        <li class="page-item ${pagination.pageNumber eq pagination.startPaginationNumber ? 'disabled' : ''}">
            <c:if test="${pagination.pageNumber ne pagination.startPaginationNumber}">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/${targetPage}?count=${pagination.countOnPage}&page=${pagination.pageNumber-1}${queries}"
                   tabindex="-1">Previous</a>
            </c:if>
        </li>
        <c:forEach var="i" begin="${pagination.startPaginationNumber}" end="${pagination.finishPaginationNumber}">
            <li class="page-item ${i eq pagination.pageNumber ? 'disabled' : ''}"><a class="page-link"
                                                                                     href="${pageContext.request.contextPath}/${targetPage}?count=${pagination.countOnPage}&page=${i}${queries}">${i}</a>
            </li>
        </c:forEach>
        <li class="page-item ${pagination.pageNumber eq pagination.finishPaginationNumber ? 'disabled' : ''}">
            <c:if test="${pagination.pageNumber ne pagination.finishPaginationNumber}">
                <a class="page-link"
                    href="${pageContext.request.contextPath}/${targetPage}?count=${pagination.countOnPage}&page=${pagination.pageNumber+1}${queries}">Next</a>
            </c:if>
        </li>
    </ul>
</div>

