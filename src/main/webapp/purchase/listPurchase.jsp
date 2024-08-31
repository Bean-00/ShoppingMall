<%@ page import="com.model2.mvc.common.Search" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.model2.mvc.service.domain.PurchaseBuyer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model2.mvc.common.PageMaker" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.model2.mvc.service.purchase.constant.PurchaseStatus" %>
<%@ page import="com.model2.mvc.service.domain.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    Map<String, Object> map = (Map<String, Object>) request.getAttribute("map");
    Search search = (Search) request.getAttribute("search");

    User vo=(User)session.getAttribute("user");

    List<PurchaseBuyer> list = null;
    int totalCount = (Integer) map.get("totalCount");
    int pageUnit = search.getPageNumSize();
    int pageSize = search.getDisplayCount();
    int currentPage = search.getCurrentPage();

    PageMaker pageInfo = new PageMaker(currentPage, totalCount, pageUnit, pageSize);
    pageInfo.setCurrentPage(search.getCurrentPage());

    if (Objects.nonNull(map)) {
        pageInfo.setTotalCount(totalCount);
        list = (List<PurchaseBuyer>) map.get("list");
    }

%>
<html>
<head>
    <title>구매 목록조회</title>

    <link rel="stylesheet" href="/css/admin.css" type="text/css">

    <script type="text/javascript">
        function fncGetPurchaseBuyerList(currentPage) {
            document.getElementById("currentPage").value = currentPage;
            document.detailForm.submit();
        }
    </script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

    <form name="detailForm" action="/listPurchase.do?buyerId=<%=vo.getUserId()%>" method="post">

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
                <td colspan="11">전체 <%=totalCount%> 건수, 현재 <%=currentPage%> 페이지</td>
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

                <%
                for (int i = 0; i < list.size(); i++) {
                    PurchaseBuyer purchaseBuyerVO = list.get(i);
            %>

            <tr class="ct_list_pop">
                <td align="center">
                    <%=purchaseBuyerVO.getRowNum()%>
                </td>
                <td></td>
                <td align="left">
                    <a href="/getUser.do?userId=<%=purchaseBuyerVO.getBuyerId()%>"><%=purchaseBuyerVO.getBuyerId()%>
                    </a>
                </td>
                <td></td>
                <td align="left"><%=purchaseBuyerVO.getBuyerName()%>
                </td>
                <td></td>
                <td align="left"><%=purchaseBuyerVO.getBuyerPhone()%>
                </td>
                <td></td>
                <td align="left">현재
                    <%=PurchaseStatus.getTextByCode((purchaseBuyerVO.getTranCode()))%>
                    상태 입니다.

                </td>
                <td></td>
                <td align="left">
                    <%
                        if (purchaseBuyerVO.getTranCode().equals("2")) { %>
                    <a href="/updateTranCode.do?prodNo=<%=purchaseBuyerVO.getProdNo()%>&role=Buyer&buyerId=<%=purchaseBuyerVO.getBuyerId()%>&page=<%=currentPage%>">
                    물건도착
                    </a>

                    <% }
                    %>
                </td>
            </tr>
                <% }
            %>
            <tr>
                <td colspan="11" bgcolor="D6D7D6" height="1"></td>
            </tr>

            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
                <tr>
                    <td align="center">
                        <input type="hidden" id="currentPage" name="currentPage" value=""/>
                        <% if (pageInfo.isEnablePrev()) {
                        %>
                        ◀ 이전
                        <% } else { %>
                        <a href="javascript:fncGetPurchaseBuyerList('<%=pageInfo.getPrevPage()%>')">◀ 이전</a>
                        <% } %>
                        <% for (int i = pageInfo.getCurrentStartPageNum(); i <= pageInfo.getCurrentEndPageNum(); i++) {
                        %>
                        <a href="javascript:fncGetPurchaseBuyerList('<%=i%>');"><%=i%>
                        </a>
                        <% } %>
                        <% if (pageInfo.isEnableNext()) { %>
                        이후 ▶
                        <%
                        } else { %>
                        <a href="javascript:fncGetPurchaseBuyerList('<%=pageInfo.getNextPage()%>')">이후 ▶</a>
                        <%
                            } %>
                    </td>
                </tr>
            </table>
            <!--  페이지 Navigator 끝 -->
    </form>

</div>

</body>
</html>