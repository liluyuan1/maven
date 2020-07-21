package com.lly.util;

import com.lly.pojo.Users;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class MyListener implements ServletContextListener, HttpSessionListener {

    private ServletContext application;

    //application创建
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("创建application");
        this.application = sce.getServletContext();
        application.setAttribute("username", new ArrayList<String>());

    }
    //application销毁
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("销毁application");

    }

    //session创建
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session");
       HttpSession session =  se.getSession();
       session.setMaxInactiveInterval(600);


    }
    //session销毁
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");
        HttpSession session =  se.getSession();
       Users users = (Users) session.getAttribute("users");
      List<Users> usersList = (List<Users> ) application.getAttribute("username");
      usersList.remove(users.getUname());
    }
}
