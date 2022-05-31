package com.amnis.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.amnis.amnisapi.JiraConnector;
import com.amnis.amnisapi.MainConnector;
import com.amnis.model.*;


@WebServlet(name = "ProcessingServlet", value = "/ProcessingServlet")
public class ProcessingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task_id = request.getParameter("task_id");
        //System.out.println(task_id);

        MainConnector mainConnector = new JiraConnector();
        mainConnector.callToDB(task_id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
