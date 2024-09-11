<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${ pageInfo.currentPage <= pageInfo.pageUnit }">
    ◀ 이전
</c:if>
<c:if test="${ pageInfo.currentPage > pageInfo.pageUnit }">
    <a href="javascript:fncGetUserList('${ pageInfo.currentPage-1}')">◀ 이전</a>
</c:if>

<c:forEach begin="${pageInfo.beginUnitPage}" end="${pageInfo.endUnitPage}" step="1">
    <a href="javascript:fncGetUserList('${ i }');">${ i }</a>
</c:forEach>

<c:if test="${ pageInfo.endUnitPage >= pageInfo.maxPage }">
    이후 ▶
</c:if>
<c:if test="${ pageInfo.endUnitPage < pageInfo.maxPage }">
    <a href="javascript:fncGetUserList('${pageInfo.endUnitPage+1}')">이후 ▶</a>
</c:if>