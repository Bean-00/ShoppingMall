package com.model2.mvc.web.user;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/users/*")
public class UserRestController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Value("#{commonProperties['displayCount']}")
    int displayCount;

    @Value("#{commonProperties['pageNumSize']}")
    int pageNumSize;

    @PostMapping("/")
    public ResponseEntity<Void> addUser(@RequestBody User user) throws Exception {

        userService.addUser(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) throws Exception {

        User user = userService.getUserByUserId(userId);

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable String userId, @RequestBody User user) throws Exception {

        userService.updateUser(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) throws Exception {

        User loginUser = userService.loginUser(user);

        return ResponseEntity.ok().body(loginUser);
    }

    @PostMapping("/{currentPage}")
    public ResponseEntity<Map<String, Object>> listUser(@PathVariable int currentPage, @RequestBody Search search) throws Exception {

        search.setCurrentPage(currentPage);
        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);

        List<User> userList = userService.getUserList(search);
        int totalCount = userService.getTotalUserCount(search);

        Map<String, Object> response = new HashMap<>();

        response.put("userList", userList);
        response.put("totalCount", totalCount);

        return ResponseEntity.ok(response);
    }
}

