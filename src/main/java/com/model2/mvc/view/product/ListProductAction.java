package com.model2.mvc.view.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.ProductStatus;

public class ListProductAction extends Action {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {

        Search search = new Search();

        int currentPage = 1;
        if (request.getParameter("currentPage") != null &&
                !request.getParameter("currentPage").equals("undefined"))
            currentPage = Integer.parseInt(request.getParameter("currentPage"));

        int pageNumSize = Integer.parseInt(getServletContext().getInitParameter("pageNumSize"));
        int displayCount = Integer.parseInt(getServletContext().getInitParameter("displayCount"));

        search.setCurrentPage(currentPage);
        search.setPageNumSize(pageNumSize);
        search.setDisplayCount(displayCount);

        search.setSearchCondition(request.getParameter("searchCondition"));
        search.setSearchKeyword(request.getParameter("searchKeyword"));

        ProductService service = new ProductServiceImpl();
        Map<String, Object> map = new HashMap<>();

        List<ProductStatus> productStatusVOList = service.getProductWithStatusList(search);
        int totalCount = service.getAllProductCount(search);

        map.put("totalCount", totalCount);
        map.put("list", productStatusVOList);

        request.setAttribute("map", map);
        request.setAttribute("search", search);

        return "forward:/product/listProduct.jsp";
    }

}
