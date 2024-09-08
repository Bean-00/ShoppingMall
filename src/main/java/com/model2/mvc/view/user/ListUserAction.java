package com.model2.mvc.view.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.PageMaker;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.User;


public class ListUserAction extends Action {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {

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

        UserService service = new UserServiceImpl();

        List<User> userList = service.getUserList(search);
        int totalCount = service.getTotalUserCount(search);


        PageMaker pageInfo = new PageMaker(currentPage, totalCount, search.getPageNumSize(), search.getDisplayCount());
        request.setAttribute("list", userList);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("search", search);

        return "forward:/user/listUser.jsp";
    }
}