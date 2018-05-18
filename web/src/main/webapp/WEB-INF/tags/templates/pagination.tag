<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ attribute name="pagination" type="by.bsuir.realEstateAgency.web.bean.pagedList.Pagination" required="true" %>
<%@ attribute name="targetPage" type="java.lang.String" required="true" %>
<div class="center">
    <ul class="pagination">
        <li class="page-item ${pagination.pageNumber eq pagination.startPaginationNumber ? 'disabled' : ''}">
            <a class="page-link"
               href="${pageContext.request.contextPath}/${targetPage}?page=${pagination.pageNumber-1}"
               tabindex="-1">Previous</a>
        </li>
        <c:forEach var="i" begin="${pagination.startPaginationNumber}" end="${pagination.finishPaginationNumber}">
            <li class="page-item ${i eq pagination.pageNumber ? 'disabled' : ''}"><a class="page-link"
                                                                                     href="${pageContext.request.contextPath}/${targetPage}?page=${i}">${i}</a>
            </li>
        </c:forEach>
        <li class="page-item ${pagination.pageNumber eq pagination.finishPaginationNumber ? 'disabled' : ''}">
            <a class="page-link"
               href="${pageContext.request.contextPath}/${targetPage}?page=${pagination.pageNumber+1}">Next</a>
        </li>
    </ul>
</div>

