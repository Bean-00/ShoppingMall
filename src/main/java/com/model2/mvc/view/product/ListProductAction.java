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

        int page = 1;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        search.setPage(page);
        search.setSearchCondition(request.getParameter("searchCondition"));
        search.setSearchKeyword(request.getParameter("searchKeyword"));

        String pageUnit = getServletContext().getInitParameter("pageSize");
        search.setPageNumSize(Integer.parseInt(pageUnit));

        Map<String, Object> map = new HashMap<>();

        ProductService service = new ProductServiceImpl();
        List<ProductStatus> productStatusVOList = service.getProductWithStatusList(search);

        int totalCount = service.getAllProductCount();

        map.put("list", productStatusVOList);
        map.put("totalCount", totalCount);

        request.setAttribute("map", map);
        request.setAttribute("search", search);

        return "forward:/product/listProduct.jsp";
    }

}
