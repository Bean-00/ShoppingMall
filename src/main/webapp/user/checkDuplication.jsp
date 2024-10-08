<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>아이디 중복 확인</title>

    <link rel="stylesheet" href="/css/admin.css" type="text/css">
    <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script type="text/javascript">

        $(function () {
            const $userId =  $("#userId")
            $userId.focus()

            $userId.on("keydown", (event) => {
                alert("keycode :" + event.keyCode)

                if (event.keyCode === '13') {
                   // fncCheckDuplication()
                }
            })

            $("td.ct_btn:contains('중복확인')").on("click" , () => {
                if(!!$userId) {
                    $("form").attr("method" , "POST")
                    $("form").attr("action" , "/user/checkDuplication")
                    $("form").submit()

                }else {
                    alert('아이디는 반드시 입력하셔야 합니다.')
                }
                $userId.focus()
            })

            $("td.ct_btn01:contains('사용')").on("click" , () => {

                if(opener) {
                    opener.$("input[name='userId']").val("${userId}");
                    opener.$("input[name='password']").focus();
                }

                window.close();
            })

            $("td.ct_btn01:contains('닫기')").on("click" , function() {
                window.close();
            })


        })

        // function fncCheckDuplication() {
        //     // Form 유효성 검증
        //     if (document.detailForm.userId.value != null && document.detailForm.userId.value.length > 0) {
        //         document.detailForm.action = '/user/checkDuplication';
        //         document.detailForm.submit();
        //     } else {
        //         alert('아이디는 반드시 입력하셔야 합니다.');
        //     }
        //     document.getElementById("userId").focus();
        // }

        function fncUseId() {
            if (opener) {
                <%-- /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////
                opener.document.detailForm.userId.value = "<%=userId%>";
                         /////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>
                opener.document.detailForm.userId.value = "${userId}";
            }
            window.close();
        }

    </script>
</head>

<body bgcolor="#ffffff" text="#000000">

<form name="detailForm" method="post">

    <input type="hidden" name="name" value=""/>

    <!-- 타이틀 시작 -->
    <table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="15" height="37">
                <img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
            </td>
            <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="93%" class="ct_ttl01">ID중복확인</td>
                        <td width="20%" align="right">&nbsp;</td>
                    </tr>
                </table>
            </td>
            <td width="12" height="37">
                <img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
            </td>
        </tr>
    </table>
    <!-- 타이틀 끝 -->

    <!-- 검색결과 시작 -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="32" style="padding-left:12px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:3px;">
                    <tr>
                        <td width="8" style="padding-bottom:3px;">
                            <img src="/images/ct_bot_ttl01.gif" width="4" height="7">
                        </td>
                        <td class="ct_ttl02">
                            <%--				<%--%>
                            <%--					if(request.getAttribute("result") != null){--%>
                            <%--				%>--%>
                            <%--					<%=userId %>--%>
                            <%--					<%--%>
                            <%--						if(result){--%>
                            <%--					%>--%>
                            <%--							는 사용 가능합니다.--%>
                            <%--					<%--%>
                            <%--						}else{--%>
                            <%--					%>--%>
                            <%--							는 사용이 불가능합니다.--%>
                            <%--					<%--%>
                            <%--						}--%>
                            <%--					}--%>
                            <%--					%>--%>
                            <c:if test="${ ! empty result }">
                                ${userId} 는 사용
                                ${ result ? "" : "불" }가능 합니다.
                            </c:if>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td background="/images/ct_line_ttl.gif" height="1"></td>
        </tr>
    </table>
    <!-- 검색결과 끝 -->

    <!-- 등록 테이블시작 -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
        <tr>
            <td width="104" class="ct_write">
                아이디 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <!-- 테이블 시작 -->
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
						<td width="105">
							<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////
                            <%	if(result) { %>
                                                    <input type="text" name="userId" id="userId" value="<%=userId %>" class="ct_input_g" style="width:100px; height:19px"  maxLength="20" >
                            <%	}else {%>
                                                    <input type="text" name="userId" id="userId" class="ct_input_g" style="width:100px; height:19px"  maxLength="20" >
                            <%	} %>/////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>
							<input type="text" name="userId" id="userId"
								   value="${ ! empty result && result ? userId : '' }"
								   class="ct_input_g" style="width:100px; height:19px"  maxLength="20" >
						</td>
                        <td>
                            <table border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="4" height="21">
                                        <img src="/images/ct_btng01.gif" width="4" height="21">
                                    </td>
                                    <td align="center" background="/images/ct_btng02.gif" class="ct_btn"
                                        style="padding-top:3px;">
                                        <a href="javascript:fncCheckDuplication();">중복확인</a>
                                    </td>
                                    <td width="4" height="21">
                                        <img src="/images/ct_btng03.gif" width="4" height="21">
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <!-- 테이블 끝 -->
            </td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
    </table>
    <!-- 등록테이블 끝 -->

    <!-- 버튼 시작 -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
        <tr>
            <td align="center">
                <table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////
                        <%	if(result){ %>
                                /////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>
						<c:if test="${ ! empty result && result }">
							<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
							</td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
								<a href="javascript:fncUseId();">사용</a>
							</td>
							<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
							</td>
						</c:if>
						<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////
                        <%	} %>
                                 /////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////--%>
						<td width="30"></td>
						<td width="17" height="23">
							<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
						</td>
						<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
							<a href="javascript:window.close();">닫기</a>
						</td>
						<td width="14" height="23">
							<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
						</td>
					</tr>
                </table>
            </td>
        </tr>
    </table>
    <!-- 버튼 끝 -->

</form>

</body>
</html>
