<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="com.model2.mvc.common.*" %>
<%@ page import="com.model2.mvc.service.domain.User" %>

<%
    Map<String, Object> map = (Map<String, Object>) request.getAttribute("map");
    Search search = (Search) request.getAttribute("search");

    List<User> list = null;
    int totalCount = (Integer) map.get("count");
    int pageUnit = search.getPageUnit();
    int pageSize = search.getPageSize();
    int currentPage = search.getPage();

    PageMaker pageInfo = new PageMaker(currentPage, totalCount, pageUnit, pageSize);
    pageInfo.setCurrentPage(search.getPage());

    if (Objects.nonNull(map)) {
        pageInfo.setTotalCount(totalCount);
        list = (List<User>) map.get("list");
    }

%>

<html>
<head>
    <title>회원 목록조회</title>

    <link rel="stylesheet" href="/css/admin.css" type="text/css">

    <script type="text/javascript">
        function fncGetUserList(currentPage) {
            document.getElementById("currentPage").value = currentPage;
            document.detailForm.submit();
        }
    </script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

    <form name="detailForm" action="/listUser.do" method="post">

        <table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="15" height="37">
                    <img src="/images/ct_ttl_img01.gif" width="15" height="37">
                </td>
                <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="93%" class="ct_ttl01">회원 목록조회</td>
                        </tr>
                    </table>
                </td>
                <td width="12" height="37">
                    <img src="/images/ct_ttl_img03.gif" width="12" height="37">
                </td>
            </tr>
        </table>

        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
            <tr>
                <%
                    if (search.getSearchCondition() != null) {
                %>
                <td align="right">
                    <select name="searchCondition" class="ct_input_g" style="width:80px">
                        <%
                            if (search.getSearchCondition().equals("0")) {
                        %>
                        <option value="0" selected>회원ID</option>
                        <option value="1">회원명</option>
                        <%
                        } else {
                        %>
                        <option value="0">회원ID</option>
                        <option value="1" selected>회원명</option>
                        <%
                            }
                        %>
                    </select>
                    <input type="text" name="searchKeyword" value="<%=search.getSearchKeyword() %>"
                           class="ct_input_g" style="width:200px; height:19px">
                </td>
                <%
                } else {
                %>
                <td align="right">
                    <select name="searchCondition" class="ct_input_g" style="width:80px">
                        <option value="0">회원ID</option>
                        <option value="1">회원명</option>
                    </select>
                    <input type="text" name="searchKeyword" class="ct_input_g" value="" style="width:200px; height:19px">
                </td>
                <%
                    }
                %>
                <td align="right" width="70">
                    <table border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="17" height="23">
                                <img src="/images/ct_btnbg01.gif" width="17" height="23">
                            </td>
                            <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                                <a href="javascript:fncGetUserList();">검색</a>
                            </td>
                            <td width="14" height="23">
                                <img src="/images/ct_btnbg03.gif" width="14" height="23">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
            <tr>
                <td colspan="11">전체  <%=totalCount%> 건수, 현재 <%=currentPage%> 페이지</td>
            </tr>
            <tr>
                <td class="ct_list_b" width="100">No</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b" width="150">회원ID</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b" width="150">회원명</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b">이메일</td>
            </tr>
            <tr>
                <td colspan="11" bgcolor="808285" height="1"></td>
            </tr>
            <%
                for (int i = 0; i < list.size(); i++) {
                    User vo = list.get(i);
            %>
            <tr class="ct_list_pop">
                <td align="center"><%=vo.getRowNum()%>
                </td>
                <td></td>
                <td align="left">
                    <a href="/getUser.do?userId=<%=vo.getUserId() %>"><%=vo.getUserId()%>
                    </a>
                </td>
                <td></td>
                <td align="left"><%=vo.getUserName()%>
                </td>
                <td></td>
                <td align="left"><%=Optional.ofNullable(vo.getEmail()).orElse("")%>
                </td>
            </tr>
            <tr>
                <td colspan="11" bgcolor="D6D7D6" height="1"></td>
            </tr>
            <% } %>
        </table>

        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
            <tr>
                <td align="center">
                    <input type="hidden" id="currentPage" name="currentPage" value=""/>
<%--                    <% if (pageInfo.getCurrentPage() <= pageInfo.getPageUnit()) {--%>
                    <% if (pageInfo.isEnablePrev()) {
                       %>
                    ◀ 이전
                    <% } else { %>
<%--                    <a href="javascript:fncGetUserList('<%=pageInfo.getCurrentPage()-1%>')">◀ 이전</a>--%>
                    <a href="javascript:fncGetUserList('<%=pageInfo.getPrevPage()%>')">◀ 이전</a>
                    <% } %>
                    <% for (int i = pageInfo.getBeginUnitPage(); i <= pageInfo.getEndUnitPage(); i++) {
                    %>
                    <a href="javascript:fncGetUserList('<%=i%>');"><%=i%>
                    </a>
                    <% } %>
                    <% if (pageInfo.isEnableNext()) { %>
<%--                    <% if (pageInfo.getEndUnitPage() >= pageInfo.getMaxPage()) { %>--%>
                    이후 ▶
                    <%
                    } else { %>
<%--                    <a href="javascript:fncGetUserList('<%=pageInfo.getEndUnitPage()+1%>')">이후 ▶</a>--%>
                    <a href="javascript:fncGetUserList('<%=pageInfo.getNextPage()%>')">이후 ▶</a>
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