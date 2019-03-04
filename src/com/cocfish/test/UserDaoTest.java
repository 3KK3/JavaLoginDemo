package com.cocfish.test;

import com.cocfish.dao.UserDao;
import com.cocfish.login.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin (){
        User user = new User();
        user.setPassword("123");
        user.setUsername("baby");

        UserDao dao = new UserDao();
        User newUser = dao.login(user);
        System.out.println(newUser);

    }


}
