package com.model2.mvc.view.purchase;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseBuyerVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ListPurchaseAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SearchVO searchVO = new SearchVO();

        int page = 1;
        if (Objects.nonNull("page")){
            page = Integer.parseInt(request.getParameter("page"));
        }

        searchVO.setPage(page);
        searchVO.setSearchCondition(request.getParameter("searchCondition"));
        searchVO.setSearchKeyword(request.getParameter("searchKeyword"));

        String pageUnit = getServletContext().getInitParameter("pageSize");
        searchVO.setPageUnit(Integer.parseInt(pageUnit));

        PurchaseService service = new PurchaseServiceImpl();
        Map<String, Object> map = new HashMap<>();

        List<PurchaseBuyerVO> purchaseBuyerList = service.getPurchaseList(searchVO);

        int totalCount = service.getAllPurchaseCount();

        map.put("count", totalCount);
        map.put("list", purchaseBuyerList);

        request.setAttribute("map", map);
        request.setAttribute("searchVO", searchVO);

        return "forward:/product/listPurchase.jsp";
    }
}
