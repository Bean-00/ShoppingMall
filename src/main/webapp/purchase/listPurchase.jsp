<%@ page import="com.model2.mvc.common.SearchVO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.model2.mvc.service.product.vo.ProductStatusVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseBuyerVO" %>
<%@ page import="com.model2.mvc.service.purchase.constant.PurchaseStatus" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    Map<String, Object> map = (Map<String, Object>) request.getAttribute("map");
    SearchVO searchVO = (SearchVO) request.getAttribute("searchVO");

    int total = 0;
    ArrayList<PurchaseBuyerVO> list = null;
    if (map != null) {
        total = ((Integer) map.get("count")).intValue();
        list = (ArrayList<PurchaseBuyerVO>) map.get("list");
    }

    int currentPage = searchVO.getPage();

//    int totalPage = 0;
//    if (total > 0) {
//        totalPage = total / searchVO.getPageUnit();
//        if (total % searchVO.getPageUnit() > 0)
//            totalPage += 1;
//    }

%>
<html>
<head>
    <title>구매 목록조회</title>

    <link rel="stylesheet" href="/css/admin.css" type="text/css">

    <script type="text/javascript">
        function fncGetUserList() {
            document.detailForm.submit();
        }
    </script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

    <form name="detailForm" action="/listUser.do" method="post">

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
                <td colspan="11">전체 <%= total%> 건수, 현재 <%= currentPage%> 페이지</td>
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
                    PurchaseBuyerVO purchaseBuyerVO = list.get(i);
            %>

            <tr class="ct_list_pop">
                <td align="center">
                    <%=purchaseBuyerVO.getRowNum()%>
                </td>
                <td></td>
                <td align="left">
                    <a href="/getUser.do?userId=<%=purchaseBuyerVO.getBuyerId()%>"><%=purchaseBuyerVO.getBuyerId()%></a>
                </td>
                <td></td>
                <td align="left"><%=purchaseBuyerVO.getBuyerName()%>
                </td>
                <td></td>
                <td align="left"><%=purchaseBuyerVO.getBuyerPhone()%>
                </td>
                <td></td>
                <td align="left">현재
                    <%= PurchaseStatus.getTextByCode((purchaseBuyerVO.getTranCode()))%>
                    상태 입니다.
                </td>
                <td></td>
                <td align="left">

                </td>
            </tr>
                <% }
            %>
            <tr>
                <td colspan="11" bgcolor="D6D7D6" height="1"></td>
            </tr>


            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
                <tr>
                    <td align="center">

                        <a href="/listPurchase.do?page=<%= currentPage%>"><%= currentPage%>
                        </a>

                    </td>
                </tr>
            </table>

            <!--  페이지 Navigator 끝 -->
    </form>

</div>

</body>
</html>