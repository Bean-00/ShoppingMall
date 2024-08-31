package com.model2.mvc.view.purchase;

import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.PurchaseBuyer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ListPurchaseAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Search search = new Search();

        int currentPage = 1;
        if (request.getParameter("currentPage") != null &&
                !request.getParameter("currentPage").equals("undefined"))
            currentPage = Integer.parseInt(request.getParameter("currentPage"));

        search.setCurrentPage(currentPage);
        search.setSearchCondition(request.getParameter("searchCondition"));
        search.setSearchKeyword(request.getParameter("searchKeyword"));

        int pageNumSize = Integer.parseInt(getServletContext().getInitParameter("pageNumSize"));
        int displayCount = Integer.parseInt(getServletContext().getInitParameter("displayCount"));

        search.setPageNumSize(pageNumSize);
        search.setDisplayCount(displayCount);

        PurchaseService service = new PurchaseServiceImpl();
        Map<String, Object> map = new HashMap<>();
        String buyerId = request.getParameter("buyerId");

        List<PurchaseBuyer> purchaseBuyerList = service.getPurchaseList(search, buyerId);
        int totalCount = service.getAllPurchaseCount(buyerId);

        map.put("totalCount", totalCount);
        map.put("list", purchaseBuyerList);

        request.setAttribute("map", map);
        request.setAttribute("search", search);


        return "forward:/purchase/listPurchase.jsp";
    }
}
