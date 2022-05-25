package com.amnis.servlets;

import com.amnis.amnisapi.JiraConnector;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.Serializable;

@WebServlet(name = "TestServlet", urlPatterns = {"/test"})
public class RequestServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String info = request.getParameter("info");

        //89.64.0.199

        System.out.println(info);

        JiraConnector jiraConnector = new JiraConnector();

        jiraConnector.callToDB(info);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
