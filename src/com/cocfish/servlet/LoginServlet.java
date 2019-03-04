package com.cocfish.servlet;

import com.cocfish.dao.UserDao;
import com.cocfish.login.User;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet('/loginServlet')
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        // 设置编码
        req.setCharacterEncoding("utf-8");

//        // 获取请求参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        // 封装user对象
//        User loginUser = new User();
//        loginUser.setUsername(username);
//        loginUser.setPassword(password);

        // 使用apache的javabean工具类来简化获取、封装user对象的过程
        Map<String, String []> map = req.getParameterMap();
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 调用UserDao的login方法
        UserDao dao = new UserDao();
        User resultUser = dao.login(loginUser);
        if (resultUser == null) {
            // 登录失败转发到失败servlet
            req.getRequestDispatcher("/failServlet").forward(req, resp);
        } else {
            // 登录成功 请求域中存储数据并转发
            req.setAttribute("user", resultUser);
            req.getRequestDispatcher("/successServlet").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
