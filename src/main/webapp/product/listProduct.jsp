<%@ page contentType="text/html; charset=UTF-8" %>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>상품 목록조회</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>

    <style>
        .row {
            margin-top: 16px;
        }

        .image-box {
            height: 262px;
            align-content: center;
        }

        .btn-primary {
            margin-top: 16px;
        }

    </style>

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

        <div class="row justify-content-between" style="margin-top: 10px">
            <span id="product-total-count" class="col-4" style="display: flex">
            </span>
            <div class="col-4" style="justify-content: center">
                <select id="search-select" name="searchCondition" class="ct_input_g" style="width:80px; height: 38px">

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
                <input id="input-keyword" type="text" name="searchKeyword"
                       value="${! empty search.searchKeyword ? search.searchKeyword: ""}"
                       class="ct_input_g" style="width:200px; height:38px;"
                       onkeyup="searchProductName()" autocomplete="off">
                <ul id="search-bar" class="dropdown-menu" style="margin-left: 84px; width: 200px">
                </ul>
                <a class="btn btn-secondary" href="javascript:fncGetProductList()" role="button">검색</a>
            </div>
        </div>

    </form>
    <div style="width: 100%; height: 30px"></div>
    <div id="card-box-container" class="container-xl overflow-y-scroll" style="height: calc(100vh - 200px)">
    </div>
</div>

<button id="prod-modal-btn" hidden="hidden" data-bs-toggle="modal" data-bs-target="#prod-modal">
</button>
<div id="prod-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">상품 상세 조회</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <label for="prod-num" class="col-md-1 col-sm-6 control-label">상품 번호</label>
                        <div class="col-md-5 col-sm-6">
                            <input id="prod-num" type="number" readonly class="form-control">
                        </div>
                        <label for="prod-name" class="col-md-1 col-sm-6 control-label">상품 명</label>
                        <div class="col-md-5 col-sm-6">
                            <input id="prod-name" type="text" readonly class="form-control">
                        </div>
                    </div>

                    <div class="row">
                        <label for="prod-detail" class="col-md-1 col-sm-6 control-label">상세 설명</label>
                        <div class="col-md-11 col-sm-6">
                            <input id="prod-detail" type="text" readonly class="form-control">
                        </div>
                    </div>

                    <div class="row">
                        <label for="prod-regDate" class="col-md-1 col-sm-2 control-label">등록 날짜</label>
                        <div class="col-md-5 col-sm-4">
                            <input id="prod-regDate" type="date" readonly class="form-control">
                        </div>
                        <label for="prod-price" class="col-md-1 col-sm-2 control-label">상품 가격</label>
                        <div class="col-md-5 col-sm-4">
                            <input id="prod-price" type="number" readonly class="form-control">
                        </div>
                    </div>

                    <div class="row" style="margin-bottom: 16px">
                        <label for="prod-menuDate" class="col-md-1 col-sm-2 control-label">구매 일자</label>
                        <div class="col-md-5 col-sm-4">
                            <input id="prod-menuDate" type="date" readonly class="form-control">
                        </div>
                        <label for="prod-fileName" class="col-md-1 col-sm-2 control-label">첨부 파일</label>
                        <div class="col-md-5 col-sm-4">
                            <input id="prod-fileName" type="text" readonly class="form-control">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>


<script type="text/javascript" src="../javascript/ajax.js"></script>
<script>
    let currentPage;
    $(document).ready(() => {
        currentPage = 1;
        setProductList(currentPage);
        currentPage++;
    })

    $("#card-box-container").scroll(function () {
        const $card_box_container = $("#card-box-container");
        let card_box_scrollTop = $card_box_container.scrollTop();
        const scrollableHeight = $card_box_container[0].scrollHeight - $card_box_container[0].clientHeight;

        if (Math.abs(card_box_scrollTop - scrollableHeight) <= 1) {
            appendProductList(currentPage);
            currentPage++;
        }
    })

    const setProductList = (currentPage, searchCondition, searchKeyword) => {
        let apiAddress = `/api/products/totalCount` + buildProductQueryParam(currentPage, searchCondition, searchKeyword);
        sendGetAjax(apiAddress, res => {
            createTotalProductElement(res);
            sendGetAjax(`/api/products` + buildProductQueryParam(currentPage, searchCondition, searchKeyword), res => {
                createProductColumnElement(res);
            })
        })
    }

    const appendProductList = (currentPage, searchCondition, searchKeyword) => {
        let apiAddress = `/api/products` + buildProductQueryParam(currentPage, searchCondition, searchKeyword);
        sendGetAjax(apiAddress, res => {
                createProductColumnElement(res);
        })
    }

    const buildProductQueryParam = (currentPage, searchCondition, searchKeyword) => {
        let queryParam = '?currentPage=' + currentPage;
        if (searchKeyword)
            queryParam += '&searchCondition=' + searchCondition + "&searchKeyword" + searchKeyword;
        return queryParam;
    }

    const createTotalProductElement = (totalCount) => {
        $("#product-total-count").text("전체 " + totalCount + " 건");
    }

    const createProductColumnElement = (res) => {

        const $card_box_container = $("#card-box-container");

        let $card_box_html = currentPage === 1 ? '' : $card_box_container.html();
        $card_box_html += `<div id="product-card-box" class="row justify-content-evenly">`
            + createProductRowElement(res) + `</div>`;
        $card_box_container.html($card_box_html);
    }

    const createProductRowElement = (res) => {
        let $card_html = '';
        for (let product of res) {
            $card_html += `<div id = "product-card" class="card col-xl-4" style="width: 18rem; height: fit-content;">
            <span id = "row-num" class="position-absolute top-0 start-0 translate-middle badge rounded-pill bg-dark">
                  ` + product.rowNum + `
            </span>
            <div id = "file-name" class="image-box">
                <img src="/images/uploadFiles/` + product.fileName + `" class="card-img-top" alt="/images/uploadFiles/딸기.jpg">
            </div>
            <div class="card-body">
                <div class="align-content-around">
                    <h6>상품 상태 <span id = "status" class="badge bg-success rounded-pill">` + product.status + `</span></h6>
                    <h5 id = "name" class="card-title">` + product.productName + `</h5>
                    <span id = "price" class="card-text" style="display: flex">` + product.price + `</span>
                    <span id = "menu-date" class="card-text" style="display: flex">` + product.regDateString + ` 등록</span>
                    <a id = "link" href="#" class="btn btn-primary">구매하기</a>
                </div>
            </div>
        </div>`
        }
        return $card_html;
    }

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

    const searchProductName = () => {
        const $input = $("#input-keyword");
        const $select = $("#search-select");

        const debouncedGetAjax = _.debounce((keyword) => {
            sendGetAjax('/api/products/name?searchKeyword=' + keyword, (res) => {
                let html = '';
                if (res.length) {
                    for (let name of res) {
                        html += createSearchLiElement(name, false);
                    }
                    $searchBar.html(html);

                } else {
                    html += createSearchLiElement('검색 결과가 없습니다.', true);
                    $searchBar.html(html);
                }
                $searchBar.html(html);
                $searchBar.addClass('show');
            });
        }, 300);

        if (!$select || $select.val() !== '1')
            return;
        const keyword = $input.val();
        const $searchBar = $("#search-bar");
        if (keyword) {
            debouncedGetAjax(keyword);
        } else {
            $searchBar.removeClass('show');
        }
    }

    const createSearchLiElement = (value, ignoreClick) => {
        return '<li><a class="dropdown-item" onclick="setSearchKeyword(\'' + value + '\',' + ignoreClick + ')">' + value + '</a></li>';
    }

    const setSearchKeyword = (value, ignoreClick) => {
        if (ignoreClick) return;
        console.log(value);
        $("#input-keyword").val(value);
        $("#search-bar").removeClass('show');
    }


</script>

</html>