package com.model2.mvc.web.user;

import com.model2.mvc.common.PageMaker;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Value("#{commonProperties['displayCount']}")
    //@Value("#{commonProperties['pageUnit'] ?: 3}")
    int displayCount;

    @Value("#{commonProperties['pageNumSize']}")
    //@Value("#{commonProperties['pageSize'] ?: 2}")
    int pageNumSize;

    @RequestMapping("/addUserView.do")
    public String addUserView() {
        System.out.println("/addUserView.do");

        return "redirect:/user/addUserView.jsp";
    }

    @RequestMapping("/addUser.do")
    public String addUser(@ModelAttribute("user") User user) throws Exception {
        System.out.println("/addUser.do");

        userService.addUser(user);

        return "redirect:/user/loginView.jsp";
    }

    @RequestMapping("/getUser.do")
    public String getUser(@RequestParam("userId") String userId, Model model) throws Exception {
        System.out.println("/getUser.do");

        User user = userService.getUserByUserId(userId);

        model.addAttribute("user", user);

        return "forward:/user/getUser.jsp";
    }

    @RequestMapping("/updateUserView.do")
    public String userUpdateView(@RequestParam("userId") String userId, Model model) throws Exception {
        System.out.println("/updateUserView.do");

        User user = userService.getUserByUserId(userId);

        model.addAttribute("user", user);

        return "foward:/user/updateUser.jsp";
    }

    @RequestMapping("updateUser.do")
    public String updateUser(@ModelAttribute("user") User user, HttpSession session) throws Exception {
        System.out.println("/updateUser.do");

        userService.updateUser(user);

        String sessionId = ((User) session.getAttribute("user")).getUserId();
        if (sessionId.equals(user.getUserId())) {
            session.setAttribute("user", user);
        }

        return "redirect:/getUser.do?userId=" + user.getUserId();
    }

    @RequestMapping("/loginView.do")
    public String loginView() {
        System.out.println("/loginView.do");

        return "forward:/user/loginView.jsp";
    }

    @RequestMapping("/login.do")
    public String login(@ModelAttribute("user") User user, HttpSession session) throws Exception {
        System.out.println("/login.do");

        User loginUser = userService.loginUser(user);

        if (Objects.isNull(loginUser)) {
            return "forward:/user/fail-loginView.jsp";
        }
        session.setAttribute("user", loginUser);
        return "redirect:index.jsp";
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session) {
        System.out.println("/logout.do");

        session.invalidate();

        return "redirect:/index.jsp";
    }

    @RequestMapping("/checkDuplication.do")
    public String checkDuplication(@RequestParam("userId") String userId, Model model) throws Exception {
        boolean result = userService.checkDuplication(userId);

        model.addAttribute("result", new Boolean(result));
        model.addAttribute("userId", userId);

        return "forward:/user/checkDuplication.jsp";
    }

    @RequestMapping("/listUser.do")
    public String listUser(@ModelAttribute("search") Search search, Model model) throws Exception {
        System.out.println("/listUser.do");

        int currentPage = search.getCurrentPage();
        currentPage = Objects.nonNull(currentPage) && currentPage > 0? search.getCurrentPage() : 1;

        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);

        List<User> userList = userService.getUserList(search);
        int totalCount = userService.getTotalUserCount(search);

        PageMaker pageInfo = new PageMaker(currentPage, totalCount, search.getPageNumSize(), search.getDisplayCount());

        model.addAttribute("userList", userList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("search", search);

        return "forward:/user/listUser.jsp";
    }
}

