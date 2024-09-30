<%@ page contentType="text/html; charset=UTF-8" %>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>상품 목록조회</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment.min.js" ></script>
    <script type="text/javascript">

        function fncGetProductList(currentPage) {
            $("#currentPage").val(currentPage)
            document.detailForm.submit()
        }

    </script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

    <form name="detailForm" action="/product/listProduct?menu=manage"
          method="post">

        <table width="100%" height="37" border="0" cellpadding="0"
               cellspacing="0">
            <tr>
                <td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
                                                width="15" height="37"/></td>
                <td background="/images/ct_ttl_img02.gif" width="100%"
                    style="padding-left: 10px;">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="93%" class="ct_ttl01">상품 관리</td>
                        </tr>
                    </table>
                </td>
                <td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
                                                width="12" height="37"/></td>
            </tr>
        </table>

        <table width="100%" border="0" cellspacing="0" cellpadding="0"
               style="margin-top: 10px;">
            <tr>
                <td align="right">
                    <select name="searchCondition" class="ct_input_g" style="width:80px">

                        <option value="0" ${! empty search.searchCondition && search.searchCondition == 0 ? "selected" : ""}>
                            상품번호
                        </option>
                        <option value="1" ${! empty search.searchCondition && search.searchCondition == 1 ? "selected" : ""}>
                            상품명
                        </option>
                        <option value="2" ${! empty search.searchCondition && search.searchCondition == 2 ? "selected" : ""}>
                            상품가격
                        </option>
                    </select>
                    <input type="text" name="searchKeyword"
                           value="${! empty search.searchKeyword ? search.searchKeyword: ""}"
                           class="ct_input_g" style="width:200px; height:19px">
                </td>
                <td align="right" width="70">
                    <table border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="17" height="23"><img
                                    src="/images/ct_btnbg01.gif" width="17" height="23"></td>
                            <td background="/images/ct_btnbg02.gif" class="ct_btn01"
                                style="padding-top: 3px;">
                                <a href="javascript:fncGetProductList();">검색</a></td>
                            <td width="14" height="23"><img
                                    src="/images/ct_btnbg03.gif" width="14" height="23"></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>


        <table width="100%" border="0" cellspacing="0" cellpadding="0"
               style="margin-top: 10px;">
            <tr>
                <td colspan="11">전체 ${pageInfo.totalCount} 건수, 현재 ${pageInfo.currentPage} 페이지
                </td>
            </tr>
            <tr>
                <td class="ct_list_b" width="100">No</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b" width="150">상품명</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b" width="150">가격</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b">등록일</td>
                <td class="ct_line02"></td>
                <td class="ct_list_b">현재상태</td>
            </tr>
            <tr>
                <td colspan="11" bgcolor="808285" height="1"></td>
            </tr>

            <c:set var="i" value="0"/>
            <c:forEach var="product" items="${list}">
                <tr class="ct_list_pop">
                    <td align="center">
                            ${product.rowNum}
                    </td>
                    <td></td>
                    <td align="left">
                        <c:if test="${user.role == 'admin' && product.status.code == 0}">
                            <a href="/product/updateProductView?prodNo=${product.prodNo}&menu=manage">${product.productName}</a>
                        </c:if>
                        <c:if test="${user.role == 'admin' && product.status.code != 0}">
                            <span id="product-name"onclick="viewProductInfo(${product.prodNo})">${product.productName}</span>
                        </c:if>
                        <c:if test="${user.role == 'user'}">
                            <a href="/product/getProduct?prodNo=${product.prodNo}&menu=manage">${product.productName}</a>
                        </c:if>
                        <c:if test="${user.role == null}">
                            ${product.productName}
                        </c:if>
                    </td>
                    <td></td>
                    <td align="left">${product.price}
                    </td>
                    <td></td>
                    <td align="left">${product.regDateString}
                    </td>
                    <td></td>
                    <c:if test="${user.role == 'user'}">
                        <c:if test="${product.status.code != '0'}">
                            <td align="left">재고 없음</td>
                        </c:if>
                        <c:if test="${product.status.code == '0'}">
                            <td align="left">판매 중</td>
                        </c:if>
                    </c:if>
                    <c:if test="${empty user}">
                        <td align="left">상품 상태는 회원만 확인 가능합니다</td>
                    </c:if>
                    <c:if test="${user.role =='admin'}">
                        <c:choose>
                            <c:when test="${product.status.code == '1'}">
                                <td align="left">
                                        ${product.status.text}
                                    <a href="/purchase/updateTranCode?prodNo=${product.prodNo}&tranCode=${product.status.code}&page=${pageInfo.currentPage}">
                                        배송하기
                                    </a>
                                </td>
                            </c:when>
                            <c:when test="${product.status.code != '1'}">
                                <td align="left">
                                        ${product.status.text}
                                </td>
                            </c:when>
                        </c:choose>
                    </c:if>
                    </td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="11" bgcolor="D6D7D6" height="1"></td>
            </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0"
               style="margin-top: 10px;">
            <tr>
                <td align="center">
                    <input type="hidden" id="currentPage" name="currentPage" value=""/>
                    <c:if test="${ pageInfo.isEnablePrev }">
                        ◀ 이전
                    </c:if>
                    <c:if test="${!pageInfo.isEnablePrev}">
                        <a href="javascript:fncGetProductList('${ pageInfo.prevPage}')">◀ 이전</a>
                    </c:if>

                    <c:forEach var="i" begin="${pageInfo.currentStartPageNum}" end="${pageInfo.currentEndPageNum}"
                               step="1">
                        <a href="javascript:fncGetProductList('${ i }');">${ i }</a>
                    </c:forEach>

                    <c:if test="${ pageInfo.isEnableNext}">
                        이후 ▶
                    </c:if>
                    <c:if test="${ !pageInfo.isEnableNext }">
                        <a href="javascript:fncGetProductList('${pageInfo.nextPage}')">이후 ▶</a>
                    </c:if>
                </td>
            </tr>
        </table>
        <!--  페이지 Navigator 끝 -->
    </form>

</div>

<button id = "prod-modal-btn" hidden="hidden" data-toggle="modal" data-target="#prod-modal">
</button>

<div id="prod-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">상품 상세 조회</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="flex-item justify-around">
                        <label for="prod-num" class="col-sm-2 control-label" style="margin-top:6px; padding: 0px">상품 번호</label>
                        <div class="col-sm-5">
                            <input id="prod-num" type="number" readonly class="form-control" style="width: 100%">
                        </div>
                        <label for="prod-name" class="col-sm-2 control-label"style="margin-top:6px; padding: 0px">상품 명</label>
                        <div class="col-sm-5">
                            <input id="prod-name" type="text" readonly class="form-control" style="width: 100%">
                        </div>
                    </div>

                    <div class="flex-item">
                        <label for="prod-detail" class="col-sm-2 control-label"style="margin-top:6px; padding: 0px">상세 설명</label>
                        <div class="col-sm-13">
                            <input id="prod-detail"  type="text" readonly class="form-control" style="width: 445px">
                        </div>
                    </div>

                    <div class="flex-item justify-around">
                        <label for="prod-regDate" class="col-sm-2 control-label"style="margin-top:6px; padding: 0px">등록 날짜</label>
                        <div class="col-sm-5">
                            <input id="prod-regDate" type="date" readonly class="form-control" style="width: 100%">
                        </div>
                        <label for="prod-price" class="col-sm-2 control-label"style="margin-top:6px; padding: 0px">상품 가격</label>
                        <div class="col-sm-5">
                            <input id="prod-price" type="number" readonly class="form-control">
                        </div>
                    </div>

                    <div class="flex-item justify-around">
                        <label for="prod-menuDate" class="col-sm-2 control-label"style="margin-top:6px; padding: 0px">구매 일자</label>
                        <div class="col-sm-5">
                            <input id="prod-menuDate" type="date" readonly class="form-control" style="width: 100%">
                        </div>
                        <label for="prod-fileName" class="col-sm-2 control-label"style="margin-top:6px; padding: 0px">첨부 파일</label>
                        <div class="col-sm-5">
                            <input id="prod-fileName" type="text" readonly class="form-control" style="width: 100%">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>

<script type = "text/javascript" src="../javascript/ajax.js"></script>
<script>
    const viewProductInfo = (prodNo) => {
        sendGetAjax(`/api/products/` + prodNo, res => {
            if (!res)
                return alert("상품 조회에 실패했습니다");
            console.log("prodNo: ", prodNo)
            console.log("###", res)
            $("#prod-modal #prod-num").val(res.prodNo);
            $("#prod-modal #prod-detail").val(res.prodDetail);
            $("#prod-modal #prod-fileName").val(res.fileName);
            $("#prod-modal #prod-menuDate").val(moment(res.menuDate).format('YYYY-MM-DD'));
            $("#prod-modal #prod-name").val(res.prodName);
            $("#prod-modal #prod-price").val(res.price);
            $("#prod-modal #prod-regDate").val(moment(res.regDate).format('YYYY-MM-DD'));
            $("#prod-modal-btn").click();
        })
    }


</script>

<style>
    .justify-around{
        justify-content: space-between;
    }
    .flex-item{
        display: flex;
        width: 549px;
        margin-bottom: 20px;
    }

</style>
</html>