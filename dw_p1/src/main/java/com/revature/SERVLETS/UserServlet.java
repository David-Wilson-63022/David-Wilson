package com.revature.SERVLETS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.POJOS.UserPojo;
import com.revature.SERVICES.UserService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    UserService service;
    ObjectMapper mapper;


    @Override
    public void init() throws ServletException {
        System.out.println("User servlet initializing...");
        this.service = new UserService();
        this.mapper = new ObjectMapper();
        System.out.println("User servlet initialized!");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("userId");
        String emailParam = req.getParameter("email");
        String usernameParam = req.getParameter("username");
        String passwordParam = req.getParameter("password");
        UserPojo newCredentials = new UserPojo(emailParam, passwordParam);
        UserPojo newCredentials2 = new UserPojo(usernameParam, passwordParam);

        if (param == null) {
            //Return all
            List<UserPojo> userList = service.getAllUsers();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        } else if (newCredentials != null && usernameParam == null) {
            service.logIn(emailParam, passwordParam);
            System.out.println("User logged in!");
        } else if (newCredentials2 != null && emailParam == null) {
            service.logIn(usernameParam, passwordParam);
            System.out.println("User Logged in!");
        }
        //return the one the request wants
        Integer userId = Integer.parseInt(req.getParameter("userId"));

        UserPojo user = service.getUser(userId);
        String json = mapper.writeValueAsString(user);
        resp.getWriter().println(json);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }

        String jsonString = builder.toString();
        UserPojo newUser = mapper.readValue(jsonString, UserPojo.class);

        System.out.println("BREADCRUMB BEFORE SAVE USER");
        service.saveUser(newUser);
        System.out.println("BREADCRUMB AFTER. YUM.");

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("userId");
        String adminParam = req.getParameter("administrator");
        Integer userId = Integer.parseInt(param);
        Boolean admin = Boolean.parseBoolean(adminParam);
        if(adminParam != null && userId != 1){
            service.adminUpdate(userId, admin);
        }else{
            StringBuilder builder = new StringBuilder();
            BufferedReader buffer = req.getReader();
            while (buffer.ready()){
                builder.append(buffer.readLine());
            }
            String json = builder.toString();
            UserPojo updateUser = mapper.readValue(json, UserPojo.class);

            service.updateUser(updateUser, userId);
        }
        resp.setStatus(200);
        resp.setContentType("Application/Json, Charset=UTF-8");
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("userId");
        if(param == null){
            resp.getWriter().println("Employee ain't here.");
        }else{
            Integer userId = Integer.parseInt(req.getParameter("userId"));
            service.deleteUser(userId);
            resp.getWriter().println("Set for waste, gone with haste.");
        }

        resp.getWriter().println("Employment revoked.");
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}