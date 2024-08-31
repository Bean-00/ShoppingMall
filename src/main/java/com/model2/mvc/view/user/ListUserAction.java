package com.model2.mvc.view.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        int Currentpage = 1;
        if (request.getParameter("currentPage") != null &&
                !request.getParameter("currentPage").equals("undefined"))
            Currentpage = Integer.parseInt(request.getParameter("currentPage"));

        search.setPage(Currentpage);
        search.setSearchCondition(request.getParameter("searchCondition"));
        search.setSearchKeyword(request.getParameter("searchKeyword"));

        int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageNumSize"));
        int pageSize = Integer.parseInt(getServletContext().getInitParameter("displayCount"));

        search.setPageUnit(pageUnit);
        search.setPageSize(pageSize);

        UserService service = new UserServiceImpl();
        Map<String, Object> map = new HashMap<>();

        List<User> userList = service.getUserList(search);
        int totalCount = service.getUserTotalCount(search);

        map.put("count", totalCount);
        map.put("list", userList);

        request.setAttribute("map", map);
        request.setAttribute("search", search);

        return "forward:/user/listUser.jsp";
    }
}