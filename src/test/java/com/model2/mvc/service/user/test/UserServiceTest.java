package com.model2.mvc.service.user.test;

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

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/commonservice.xml"})
public class UserServiceTest {

    //==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Before
    public void setUp() {
        userService.deleteUser("testUserId");
    }

    @Test
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

        user = userService.getUser("testUserId");

        //==> console 확인
        //System.out.println(user);

        //==> API 확인
        assertEquals("testUserId", user.getUserId());
        assertEquals("testUserName", user.getUserName());
        assertEquals("testPasswd", user.getPassword());
        assertEquals("111-2222-3333", user.getPhone());
        assertEquals("경기도", user.getAddr());
        assertEquals("test@test.com", user.getEmail());
    }

    @Test
    @Ignore
    public void testGetUser() throws Exception {

        User user = new User();
        //==> 필요하다면...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("경기도");
//		user.setEmail("test@test.com");

        user = userService.getUser("testUserId");

        //==> console 확인
        //System.out.println(user);

        //==> API 확인
        assertEquals("testUserId", user.getUserId());
        assertEquals("testUserName", user.getUserName());
        assertEquals("testPasswd", user.getPassword());
        assertEquals("111-2222-3333", user.getPhone());
        assertEquals("경기도", user.getAddr());
        assertEquals("test@test.com", user.getEmail());

        Assert.assertNotNull(userService.getUser("user02"));
        Assert.assertNotNull(userService.getUser("user05"));
    }

    @Test
    @Ignore
    public void testUpdateUser() throws Exception{

        User user = userService.getUser("testUserId");
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

        user = userService.getUser("testUserId");
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
    @Ignore
    public void testCheckDuplication() throws Exception{

        //==> 필요하다면...
//		User user = new User();
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("경기도");
//		user.setEmail("test@test.com");
//
//		userService.addUser(user);

        //==> console 확인
        System.out.println(userService.checkDuplication("testUserId"));
        System.out.println(userService.checkDuplication("testUserId"+System.currentTimeMillis()) );

        //==> API 확인
        Assert.assertFalse( userService.checkDuplication("testUserId") );
        Assert.assertTrue( userService.checkDuplication("testUserId"+System.currentTimeMillis()) );

    }

    //==>  주석을 풀고 실행하면....
    //@Test
//    public void testGetUserListAll() throws Exception{
//
//        Search search = new Search();
//        search.setCurrentPage(1);
//        search.setPageSize(3);
//        List<User> map = userService.getUserList(search);
//
//        List<Object> list = (List<Object>)map.get("list");
//        Assert.assertEquals(3, list.size());
//
//        //==> console 확인
//        //System.out.println(list);
//
//        Integer totalCount = (Integer)map.get("totalCount");
//        System.out.println(totalCount);
//
//        System.out.println("=======================================");
//
//        search.setCurrentPage(1);
//        search.setPageSize(3);
//        search.setSearchCondition("0");
//        search.setSearchKeyword("");
//        map = userService.getUserList(search);
//
//        list = (List<Object>)map.get("list");
//        Assert.assertEquals(3, list.size());
//
//        //==> console 확인
//        //System.out.println(list);
//
//        totalCount = (Integer)map.get("totalCount");
//        System.out.println(totalCount);
//    }
//
//    //@Test
//    public void testGetUserListByUserId() throws Exception{
//
//        Search search = new Search();
//        search.setCurrentPage(1);
//        search.setPageSize(3);
//        search.setSearchCondition("0");
//        search.setSearchKeyword("admin");
//        Map<String,Object> map = userService.getUserList(search);
//
//        List<Object> list = (List<Object>)map.get("list");
//        Assert.assertEquals(1, list.size());
//
//        //==> console 확인
//        //System.out.println(list);
//
//        Integer totalCount = (Integer)map.get("totalCount");
//        System.out.println(totalCount);
//
//        System.out.println("=======================================");
//
//        search.setSearchCondition("0");
//        search.setSearchKeyword(""+System.currentTimeMillis());
//        map = userService.getUserList(search);
//
//        list = (List<Object>)map.get("list");
//        Assert.assertEquals(0, list.size());
//
//        //==> console 확인
//        //System.out.println(list);
//
//        totalCount = (Integer)map.get("totalCount");
//        System.out.println(totalCount);
//    }
//
//    //@Test
//    public void testGetUserListByUserName() throws Exception{
//
//        Search search = new Search();
//        search.setCurrentPage(1);
//        search.setPageSize(3);
//        search.setSearchCondition("1");
//        search.setSearchKeyword("SCOTT");
//        Map<String,Object> map = userService.getUserList(search);
//
//        List<Object> list = (List<Object>)map.get("list");
//        Assert.assertEquals(3, list.size());
//
//        //==> console 확인
//        System.out.println(list);
//
//        Integer totalCount = (Integer)map.get("totalCount");
//        System.out.println(totalCount);
//
//        System.out.println("=======================================");
//
//        search.setSearchCondition("1");
//        search.setSearchKeyword(""+System.currentTimeMillis());
//        map = userService.getUserList(search);
//
//        list = (List<Object>)map.get("list");
//        Assert.assertEquals(0, list.size());
//
//        //==> console 확인
//        System.out.println(list);
//
//        totalCount = (Integer)map.get("totalCount");
//        System.out.println(totalCount);
//    }
}