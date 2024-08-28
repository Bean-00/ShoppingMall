package com.model2.mvc.view.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;


public class ListUserAction extends Action {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        SearchVO searchVO = new SearchVO();

        int page = 1;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        searchVO.setPage(page);
        searchVO.setSearchCondition(request.getParameter("searchCondition"));
        searchVO.setSearchKeyword(request.getParameter("searchKeyword"));

        String pageUnit = getServletContext().getInitParameter("pageSize");
        searchVO.setPageUnit(Integer.parseInt(pageUnit));

        UserService service = new UserServiceImpl();
        Map<String, Object> map = new HashMap<>();

        List<UserVO> userList = service.getUserList(searchVO);
        int totalCount = service.getUserTotalCount();

        map.put("count", totalCount);
        map.put("list", userList);

        request.setAttribute("map", map);
        request.setAttribute("searchVO", searchVO);

        return "forward:/user/listUser.jsp";
    }
}