<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>구매 목록조회</title>

    <link rel="stylesheet" href="/css/admin.css" type="text/css">
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>

    <script type="text/javascript">
        function fncGetPurchaseBuyerList(currentPage) {
            $("currentPage").val(currentPage)
            document.detailForm.submit()
        }
    </script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

    <form name="detailForm" action="/listPurchase?buyerId=${user.userId}" method="post">

        <table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="15" height="37"><img src="/images/ct_ttl_img01.gif" width="15" height="37"></td>
                <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="93%" class="ct_ttl01">구매 목록조회</td>
                        </tr>
                    </table>
                </td>
                <td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
            </tr>
        </table>

        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
            <tr>
                <td colspan="11">전체 ${pageInfo.totalCount} 건수, 현재 ${pageInfo.currentPage} 페이지</td>
            </tr>
            <tr>
                <td class="ct_list_b" width="100">No</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b" width="150">회원ID</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b" width="150">회원명</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b">전화번호</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b">배송현황</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b">정보수정</td>
            </tr>
            <tr>
                <td colspan="11" bgcolor="808285" height="1"></td>
            </tr>
            <c:set var="i" value="0"></c:set>
            <c:forEach var="purchase" items="${list}">

            <tr class="ct_list_pop">
                <td align="center">
                        ${purchase.rowNum}
                </td>
                <td></td>
                <td align="left">
                    <a href="/getUser?userId=${purchase.buyer.userId}">${purchase.buyer.userId}
                    </a>
                </td>
                <td></td>
                <td align="left">${purchase.receiverName}
                </td>
                <td></td>
                <td align="left">${purchase.receiverPhone}
                </td>
                <td></td>
                <td align="left">현재
                        ${purchase.tranText}
                    상태 입니다.
                </td>
                <td></td>
                <td align="left">
                    <c:if test="${purchase.status == '2'}">
                        <a href="/updateTranCode?prodNo=${purchase.purchaseProd.prodNo}&role=Buyer&buyerId=${purchase.buyer.userId}&page=${pageInfo.currentPage}">
                            물건도착
                        </a>
                    </c:if>
                </td>
            </tr>
            </c:forEach>

            <tr>
                <td colspan="11" bgcolor="D6D7D6" height="1"></td>
            </tr>

            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
                <tr>
                    <td align="center">
                        <input type="hidden" id="currentPage" name="currentPage" value=""/>
                        <c:if test="${ pageInfo.isEnablePrev }">
                            ◀ 이전
                        </c:if>
                        <c:if test="${!pageInfo.isEnablePrev}">
                            <a href="javascript:fncGetPurchaseBuyerList('${ pageInfo.prevPage}')">◀ 이전</a>
                        </c:if>

                        <c:forEach var="i" begin="${pageInfo.currentStartPageNum}" end="${pageInfo.currentEndPageNum}"
                                   step="1">
                            <a href="javascript:fncGetPurchaseBuyerList('${ i }');">${ i }</a>
                        </c:forEach>

                        <c:if test="${ pageInfo.isEnableNext}">
                            이후 ▶
                        </c:if>
                        <c:if test="${ !pageInfo.isEnableNext }">
                            <a href="javascript:fncGetPurchaseBuyerList('${pageInfo.nextPage}')">이후 ▶</a>
                        </c:if>
                    </td>
                </tr>
            </table>
            <!--  페이지 Navigator 끝 -->
    </form>

</div>

</body>
</html>