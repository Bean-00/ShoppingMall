package com.model2.mvc.view.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.PageMaker;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
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
        List<ProductStatus> productStatusVOList = service.getProductWithStatusList(search);
        int totalCount = service.getAllProductCount(search);

        User user = (User) request.getSession().getAttribute("user");


        PageMaker pageInfo = new PageMaker(currentPage, totalCount, search.getPageNumSize(), search.getDisplayCount());
        request.setAttribute("list", productStatusVOList);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("search", search);
        request.setAttribute("user", user);

        return "forward:/product/listProduct.jsp";
    }

}
