package com.model2.mvc.service.user.test;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/commonservice.xml"})
public class UserServiceTest {

    //==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

//    @Before
    public void setUp() {
        userService.deleteUser("testUserId");
    }

    @Test
//    @Ignore
    public void testAddUser() throws Exception {

        User user = new User();
        user.setUserId("testUserId");
        user.setUserName("testUserName");
        user.setPassword("testPasswd");
        user.setSsn("1111112222222");
        user.setPhone("111-2222-3333");
        user.setAddr("경기도");
        user.setEmail("test@test.com");

        userService.addUser(user);

        user = userService.getUserByUserId("testUserId");

        //==> API 확인
        assertEquals("testUserId", user.getUserId());
        assertEquals("testUserName", user.getUserName());
        assertEquals("testPasswd", user.getPassword());
        assertEquals("111-2222-3333", user.getPhone());
        assertEquals("경기도", user.getAddr());
        assertEquals("test@test.com", user.getEmail());
    }

    @Test
    public void testGetUser() throws Exception {


        User user = userService.getUserByUserId("testUserId");
        System.out.println(user.toString());

        //==> console 확인
        //System.out.println(user);

        //==> API 확인
        assertEquals("testUserId", user.getUserId());
        assertEquals("testUserName", user.getUserName());
        assertEquals("testPasswd", user.getPassword());
        assertEquals("111-2222-3333", user.getPhone());
        assertEquals("경기도", user.getAddr());
        assertEquals("test@test.com", user.getEmail());

        Assert.assertNotNull(userService.getUserByUserId("user02"));
        Assert.assertNotNull(userService.getUserByUserId("user05"));
    }

    @Test
    @Ignore
    public void testUpdateUser() throws Exception{

        User user = userService.getUserByUserId("testUserId");
        Assert.assertNotNull(user);

        assertEquals("testUserName", user.getUserName());
        assertEquals("111-2222-3333", user.getPhone());
        assertEquals("경기도", user.getAddr());
        assertEquals("test@test.com", user.getEmail());

        user.setUserName("change");
        user.setPhone("777-7777-7777");
        user.setAddr("change");
        user.setEmail("change@change.com");

        userService.updateUser(user);

        user = userService.getUserByUserId("testUserId");
        Assert.assertNotNull(user);

        //==> console 확인
        //System.out.println(user);

        //==> API 확인
        assertEquals("change", user.getUserName());
        assertEquals("777-7777-7777", user.getPhone());
        assertEquals("change", user.getAddr());
        assertEquals("change@change.com", user.getEmail());
    }

    @Test
    public void testCheckDuplication() throws Exception{


        //==> console 확인
        System.out.println(userService.checkDuplication("testUserId"));
        System.out.println(userService.checkDuplication("testUserId"+System.currentTimeMillis()) );

        //==> API 확인
        Assert.assertFalse( userService.checkDuplication("testUserId") );
        Assert.assertTrue( userService.checkDuplication("testUserId"+System.currentTimeMillis()) );

    }

    //==>  주석을 풀고 실행하면....
    @Test
    public void testGetUserListAll() throws Exception{

        Search search = new Search();
        search.setCurrentPage(1);
        search.setDisplayCount(3);

        List<User> userList = userService.getUserList(search);

        Assert.assertEquals(3, userList.size());

        //==> console 확인
        //System.out.println(list);

        Integer totalCount = userService.getTotalUserCount(search);
        System.out.println(totalCount);

        System.out.println("=======================================");

        search.setCurrentPage(1);
        search.setDisplayCount(3);
        search.setSearchCondition("0");
        search.setSearchKeyword("admin");
        userList = userService.getUserList(search);

        Assert.assertEquals(1, userList.size());

        //==> console 확인
        //System.out.println(list);

        totalCount = userService.getTotalUserCount(search);
        System.out.println(totalCount);
    }

    @Test
    public void testGetUserListByUserId() throws Exception{

        Search search = new Search();
        search.setCurrentPage(1);
        search.setDisplayCount(3);
        search.setSearchCondition("0");
        search.setSearchKeyword("user01");

        User user = userService.getUserByUserId("user01");

        Assert.assertEquals("user01 테스트",
                user.getUserName(),
                userService.getUserList(search).get(0).getUserName());

    }

    @Test
    public void testGetUserListByUserName() throws Exception{

        Search search = new Search();
        search.setCurrentPage(1);
        search.setDisplayCount(3);
        search.setSearchCondition("1");
        search.setSearchKeyword("SCOTT");

        List<User> userList = userService.getUserList(search);

        Assert.assertEquals("SCOTT 결과", 3, userList.size());

        //==> console 확인
    }
}