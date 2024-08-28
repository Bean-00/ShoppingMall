<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO" %>
<%@ page import="java.util.Objects" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<% PurchaseVO purchaseVO = (PurchaseVO) request.getAttribute("purchase"); %>

<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td><%=purchaseVO.getPurchaseProd().getProdNo()%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td><%=purchaseVO.getBuyer().getUserId()%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
			<%
				String paymentOption = purchaseVO.getPaymentOption();
				String paymentMethod = paymentOption.equals("1") ? "현금구매" : "신용구매";
			%>
			<%= paymentMethod %>
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%=purchaseVO.getReceiverName()%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td><%=purchaseVO.getReceiverPhone()%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td><%=purchaseVO.getDivyAddr()%></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td><%=purchaseVO.getDivyRequest()%></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td><%=purchaseVO.getDivyDate()%></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>