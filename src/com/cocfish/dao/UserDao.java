package com.cocfish.dao;

import com.cocfish.login.User;
import com.cocfish.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

// 操作数据中user表的类
public class UserDao {
    // 声明JDBCTemplate对象来公用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    // 登录方法
    public User login(User loginUser) {

        try {
            // 编写sql
            String sql = "select * from User where username = ? and password = ?";
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword());


            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
