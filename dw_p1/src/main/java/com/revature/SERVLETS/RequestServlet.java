package com.revature.SERVLETS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.POJOS.RequestPojo;
import com.revature.POJOS.UserPojo;
import com.revature.SERVICES.RequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class RequestServlet extends HttpServlet {
    private RequestService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("Request servlet initializing...");
        this.service = new RequestService();
        this.mapper = new ObjectMapper();
        System.out.println("Request servlet initialized!");
    }

    @Override
    public void destroy() {
    }

    //SUPER MASSIVE NOTE ABOUT INTELLIJ COMMUNICATING WITH POSTMAN****
    //After writing code in IntelliJ, it's important to restart the program so that
    //code i'm writing is reimplemented successfully.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("requestId");
        if(param == null) {
            //Return all
            List<RequestPojo> requestList = service.getAllRequests();
            String json = mapper.writeValueAsString(requestList);
            resp.getWriter().print(json);
        } else {
            //return just one singular request by requestId
            Integer requestId = Integer.parseInt(req.getParameter("requestId"));

            RequestPojo request = service.getRequest(requestId);
            String json = mapper.writeValueAsString(request);
            resp.getWriter().print(json);
        }

        resp.setStatus(200);
        resp.setContentType("Application/Json");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }

        String json = builder.toString();
        RequestPojo newRequest = mapper.readValue(json, RequestPojo.class);

        System.out.println("Breadcrumb before service.saveRequest");
        service.saveRequest(newRequest);
        System.out.println("breadcrumb after service.saveRequest");

        resp.setStatus(200);
        resp.setContentType("Application/Json, Charset=UTF-8");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("userId");

        if(param == null) {
            System.out.println("No employees found.");
        } else {
            StringBuilder builder = new StringBuilder();
            BufferedReader buffer = req.getReader();
            while (buffer.ready()) {
                builder.append(buffer.readLine());
            }
            String json = builder.toString();
            RequestPojo pullRequest = mapper.readValue(json, RequestPojo.class);

            service.updateRequest(pullRequest);
        }
        resp.setStatus(200);
        resp.setContentType("Application/Json, Charset=UTF-8");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("requestId");
        if(param == null){
            resp.getWriter().println("No requests spotted here, Sir!");
        }else{
            Integer requestId = Integer.parseInt(req.getParameter("requestId"));
            service.deleteRequest(requestId);
            resp.getWriter().println("Checked the hood, now it's gone for good!");
        }

        resp.getWriter().println("Request removed.");
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}
