<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>회원가입</title>

    <link rel="stylesheet" href="/css/admin.css" type="text/css">

    <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script type="text/javascript">
        const fncAddUser = () => {
            // Form 유효성 검증
            const $id = $("input[name='userID']").val()
            const $pw = $("input[name='password]").val()
            const $pw_confirm = $("input[name='password2]").val()
            const $name = $("input[name='userName]").val()

            if (!$id) {
                alert("아이디는 반드시 입력하셔야 합니다.");
                return;
            }
            if (!$pw) {
                alert("패스워드는 반드시 입력하셔야 합니다.");
                return;
            }
            if (!$pw_confirm) {
                alert("패스워드 확인은 반드시 입력하셔야 합니다.");
                return;
            }
            if (!$name) {
                alert("이름은 반드시 입력하셔야 합니다.");
                return;
            }

            if ($pw !== $pw_confirm) {
                alert("비밀번호 확인이 일치하지 않습니다.");
                $pw_confirm.focus();
                return;
            }

            if ($("input:text[name='phone2']").val() !== "" && $("input:text[name='phone3']").val() !== "") {
                const value = $("option:selected").val() + "-"
                    + $("input[name='phone2']").val() + "-"
                    + $("input[name='phone3']").val();
            }

            $("input:hidden[name='phone']").val(value)
            $("form").attr("method", "POST").attr("action", "user/addUser").submit()
        }

        $(function () {
            $("td.ct_btn01:contains('가입')").on("click", fncAddUser);
            $("td.ct_btn01:contains('가입')").off("click");

            $( "td.ct_btn01:contains('취소')" ).on("click" , function() {
                $("form")[0].reset();
            });
            $( "td.ct_btn01:contains('취소')" ).off("click");

            $("input[name='email']").on("change" , () => {

                const email=$("input[name='email']").val();

                if(email != "" && (email.indexOf('@') < 1 || email.indexOf('.') === -1) ){
                    alert("이메일 형식이 아닙니다.");
                }
            });

        });

        function checkSsn() {
            let ssn1, ssn2;
            let nByear, nTyear;
            let today;

            document.detailForm.ssn.value;
            // 유효한 주민번호 형식인 경우만 나이 계산 진행, PortalJuminCheck 함수는 CommonScript.js 의 공통 주민번호 체크 함수임
            if (!PortalJuminCheck(ssn)) {
                alert("잘못된 주민번호입니다.");
                return false;
            }
        }

        function PortalJuminCheck(fieldValue) {
            let pattern = /^([0-9]{6})-?([0-9]{7})$/;
            let num = fieldValue;
            if (!pattern.test(num)) return false;
            num = RegExp.$1 + RegExp.$2;

            let sum = 0;
            let last = num.charCodeAt(12) - 0x30;
            let bases = "234567892345";
            for (let i = 0; i < 12; i++) {
                if (isNaN(num.substring(i, i + 1))) return false;
                sum += (num.charCodeAt(i) - 0x30) * (bases.charCodeAt(i) - 0x30);
            }
            let mod = sum % 11;
            return ((11 - mod) % 10 == last) ? true : false;
        }

        function fncCheckDuplication() {
            popWin = window.open("/user/checkDuplication.jsp", "popWin", "left=300,top=200,width=300,height=200,marginwidth=0,marginheight=0,scrollbars=no,scrolling=no,menubar=no,resizable=no");
        }

        function resetData() {
            document.detailForm.reset();
        }

    </script>

</head>

<body bgcolor="#ffffff" text="#000000">

<form name="detailForm" method="post">

    <table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="15" height="37">
                <img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
            </td>
            <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="93%" class="ct_ttl01">회원가입</td>
                        <td width="20%" align="right">&nbsp;</td>
                    </tr>
                </table>
            </td>
            <td width="12" height="37">
                <img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
            </td>
        </tr>
    </table>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>

        <tr>
            <td width="104" class="ct_write">
                아이디 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="105">
                            <input type="text" name="userId" class="ct_input_bg" style="width:100px; height:19px"
                                   maxLength="20">
                        </td>
                        <td>
                            <table border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="4" height="21">
                                        <img src="/images/ct_btng01.gif" width="4" height="21"/>
                                    </td>
                                    <td align="center" background="/images/ct_btng02.gif" class="ct_btn"
                                        style="padding-top:3px;">
                                        <a href="javascript:fncCheckDuplication();" id="btnCmfID">ID중복확인</a>
                                    </td>
                                    <td width="4" height="21">
                                        <img src="/images/ct_btng03.gif" width="4" height="21">
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>

        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>

        <tr>
            <td width="104" class="ct_write">
                비밀번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <input type="password" name="password" class="ct_input_g"
                       style="width:100px; height:19px" maxLength="10" minLength="6"/>
            </td>
        </tr>

        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>

        <tr>
            <td width="104" class="ct_write">
                비밀번호 확인 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <input type="password" name="password2" class="ct_input_g"
                       style="width:100px; height:19px" maxLength="10" minLength="6"/>
            </td>
        </tr>

        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>

        <tr>
            <td width="104" class="ct_write">
                이름<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
            </td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <input type="text" name="userName" class="ct_input_g"
                       style="width:100px; height:19px" maxLength="50">
            </td>
        </tr>

        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>

        <tr>
            <td width="104" class="ct_write">주민번호</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <input type="text" name="ssn" class="ct_input_g"
                       style="width:100px; height:19px" onChange="javascript:checkSsn();" maxLength="13">
                -제외, 13자리 입력
            </td>
        </tr>

        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>

        <tr>
            <td width="104" class="ct_write">주소</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <input type="text" name="addr" class="ct_input_g"
                       style="width:370px; height:19px" maxLength="100"/>
            </td>
        </tr>

        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>

        <tr>
            <td width="104" class="ct_write">휴대전화번호</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <select name="phone1" class="ct_input_g" style="width:50px"
                        onChange="document.detailForm.phone2.focus();">
                    <option value="010">010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="018">018</option>
                    <option value="019">019</option>
                </select>
                <input type="text" name="phone2" class="ct_input_g"
                       style="width:100px; height:19px" maxLength="9"/>
                -
                <input type="text" name="phone3" class="ct_input_g"
                       style="width:100px; height:19px" maxLength="9"/>
                <input type="hidden" name="phone" class="ct_input_g">
            </td>
        </tr>

        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>

        <tr>
            <td width="104" class="ct_write">이메일</td>
            <td bgcolor="D6D6D6" width="1"></td>
            <td class="ct_write01">
                <table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td height="26">
                            <input type="text" name="email" class="ct_input_g"
                                   style="width:100px; height:19px" onChange="check_email(this.form);">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="1" colspan="3" bgcolor="D6D6D6"></td>
        </tr>
    </table>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
        <tr>
            <td width="53%"></td>
            <td align="right">
                <table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="17" height="23">
                            <img src="/images/ct_btnbg01.gif" width="17" height="23"/>
                        </td>
                        <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                            <a href="javascript:fncAddUser();">가입</a>
                        </td>
                        <td width="14" height="23">
                            <img src="/images/ct_btnbg03.gif" width="14" height="23"/>
                        </td>
                        <td width="30"></td>
                        <td width="17" height="23">
                            <img src="/images/ct_btnbg01.gif" width="17" height="23"/>
                        </td>
                        <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                            <a href="javascript:resetData();">취소</a>
                        </td>
                        <td width="14" height="23">
                            <img src="/images/ct_btnbg03.gif" width="14" height="23">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>

</form>

<script type="text/javascript">
    document.getElementById("btnCmfID").focus();
</script>

</body>
</html>
