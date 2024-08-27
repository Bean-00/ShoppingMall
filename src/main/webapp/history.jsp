<%@ page import="java.util.Optional" %>
<%@ page import="com.model2.mvc.common.util.SessionUtil" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>

    <title>열어본 상품 보기</title>

</head>
<body>
당신이 열어본 상품을 알고 있다
<br>
<br>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    Optional<String> cookieValue = SessionUtil.getCookieValue(request.getCookies(), SessionUtil.HISTORY_NAME);
    if (cookieValue.isPresent()) {
        String[] h = cookieValue.get().split("\\|");
        for (int i = 0; i < h.length; i++) {
            if (!h[i].equals("null")) {
%>
                <a href="/getProduct.do?prodNo=<%=h[i]%>&menu=search" target="rightFrame"><%=h[i]%>
                </a>
                <br>
<%
            }
        }
    }
%>

</body>
</html>