<%@page import="java.util.Objects"%>
<%@page import="com.model2.mvc.service.domain.User"%>
<%@page import="com.model2.mvc.service.user.dao.UserDao"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<html>
<head>
<title>로그인</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
</head>

<script type="text/javascript">
	//이 구문이 실행이 됨
	console.log(">>>> DOM 렌더링 전", document.loginForm);
	document.loginForm.userId.focus();
</script>

<body bgcolor="#ffffff" text="#000000">
	<form id="loginForm" name="loginForm" method="post" action="/login.do"
		target="_parent">
 
		<div align="center">

			<TABLE WITH="100%" HEIGHT="100%" BORDER="0" CELLPADDING="0"
				CELLSPACING="0">
				<TR>
					<TD ALIGN="CENTER" VALIGN="MIDDLE">

						<table width="650" height="390" border="5" cellpadding="0"
							cellspacing="0" bordercolor="#D6CDB7">
							<tr>
								<td width="10" height="5" align="left" valign="top"
									bordercolor="#D6CDB7">
									<table width="650" height="390" border="0" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="305"><img src="/images/logo-spring.png"
												width="305" height="390"></td>
											<td width="345" align="left" valign="top"
												background="/images/login02.gif">
												<table width="100%" height="220" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td width="30" height="100">&nbsp;</td>
														<td width="100" height="100">&nbsp;</td>
														<td height="100">&nbsp;</td>
														<td width="20" height="100">&nbsp;</td>
													</tr>
													<tr>
														<td width="30" height="50">&nbsp;</td>
														<td width="100" height="50"><img
															src="/images/text_login.gif" width="91" height="32">
														</td>
														<td height="50">&nbsp;</td>
														<td width="20" height="50">&nbsp;</td>
													</tr>
													<tr>
														<td width="200" height="50" colspan="4"></td>
													</tr>
													<tr>
														<td width="30" height="30">&nbsp;</td>
														<td width="100" height="30"><img
															src="/images/text_id.gif" width="100" height="30">
														</td>
														<td height="30">
															<input type="text" name="userId"
															class="ct_input_g" style="width: 180px; height: 19px"
															maxLength='50' />
														</td>
														<td width="20" height="30">&nbsp;</td>
													</tr>
													<tr>
														<td width="30" height="30">&nbsp;</td>
														<td width="100" height="30"><img
															src="/images/text_pas.gif" width="100" height="30">
														</td>
														<td height="30">
															<input type="password"
															name="password" class="ct_input_g"
															style="width: 180px; height: 19px" maxLength="50" />
														</td>
														<td width="20" height="30">&nbsp;</td>
													</tr>
													<tr>
														<td width="30" height="20">&nbsp;</td>
														<td width="100" height="20">&nbsp;</td>
														<td height="20" align="center">
															<table width="136" height="20" border="0" cellpadding="0"
																cellspacing="0">
																<tr>
																	<td width="56"><a href="javascript:fncLogin();">
																			<img src="/images/btn_login.gif" width="56"
																			height="20" border="0">
																	</a></td>
																	<td width="10">&nbsp;</td>
																	<td width="70"><a href="addUserView.jsp;"> <img
																			src="/images/btn_add.gif" width="70" height="20"
																			border="0">
																	</a></td>
																</tr>
															</table>
														</td>
														<td width="20" height="20">&nbsp;</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</TD>
				</TR>
			</TABLE>
		</div>

	</form>

</body>
</html>

<script type="text/javascript">
	//선언만 하기 떄문에
	function fncLogin() {
		//form name으로 window 객체의 document의 프로퍼티로 등록이 된다.
		// const $loginForm = document.loginForm;
		const $loginForm = document.getElementById('loginForm');
		if (!$loginForm
			|| !$loginForm.userId
			|| !$loginForm.password) return;

		// Java에서의 ! 연산 : not 연산 (true -> false, false -> true)
		// JS 에서의 ! 연산 : (false, null, undefined, '', 0) -> true (값이 있는지 없는지 체크)
		// JS 에서의 !!연산 : 값이 있다. -> false 도 아니고 null 도 아니고 undefined도 아니고 빈 문자열도 아니고 0도 아님
		//
		// Valid Check : $loginForm.userId 가 null 이거나 undefined 이면 어떡하지?
		// -> if($loginForm.userId !== null && $loginForm.userId !== undefined) return;
		// -> if(!$loginForm.userId) return -> $loginForm.userId?.value;

		const id = $loginForm.userId.value;
		const pw = $loginForm.password.value;
		// if (id == null || id.length < 1) {
		if (!id) {
			// alert('ID 를 입력하지 않으셨습니다.');
			// $loginForm.userId.focus();
			// return;
			return alertMsg($loginForm.userId, 'ID');
		}
		// if (pw == null || pw.length < 1) {
		if (!pw && pw !== 0) {
			// alert('패스워드를 입력하지 않으셨습니다.');
			// $loginForm.password.focus();
			// return;
			return alertMsg($loginForm.password, '패스워드');
		}
		$loginForm.submit();
	}

	function alertMsg($el, title) {
		<%--alert(`${title} 를 입력하지 않으셨습니다.`);--%>
		alert(title + ' 를 입력하지 않으셨습니다.');
		$el.focus();
	}
</script>
<script type="text/javascript">
	//이 구문이 실행이 됨
	console.log(">>>> DOM 렌더링 후", document.loginForm);
	document.loginForm.userId.focus();
</script>

