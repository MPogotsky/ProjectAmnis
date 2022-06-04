package com.amnis.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.amnis.amnisapi.Controller;
import com.amnis.amnisapi.JiraConnector;
import com.atlassian.jira.rest.client.api.JiraRestClient;


@WebServlet(name = "ProcessingServlet", value = "/ProcessingServlet")
public class ProcessingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task_id = request.getParameter("task_id");
        String username = request.getParameter("username");
        String companyuri = request.getParameter("companyuri");
        //System.out.println(task_id);

        Controller controller = new Controller(task_id, username,companyuri);
        controller.updateDatabase();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
