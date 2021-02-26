package com.jobseek;

import com.jobseek.model.*;
import com.jobseek.service.ManagerService;
import com.jobseek.service.RootService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        name = "managerregisterservlet",
        urlPatterns = "/managerregister"
)
public class ManagerRegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        // redirect to home page if user is logged in
        HttpSession session = req.getSession(true);
        Account currentAccount = (Account) session.getAttribute( "currentAccount" );
        if(currentAccount != null) {
            resp.sendRedirect("");
            return;                                     // required so it does not execute rest of code
        }

        /* TODO: do not delete
            if no connection to mysql tables
                delete and create all tables
                populate tables with mock data
                set attributes for each table
         */
        RootService rootService = (RootService) servletContext.getAttribute( "rootService" );
        if(rootService == null) {
            try {
                rootService = new RootService();
                rootService.dropTables();
                rootService.createTables();
                rootService.populateMockData();
                // save it to the application scope
                servletContext.setAttribute( "rootService", rootService);
                servletContext.setAttribute( "seekerService", rootService.getSeekerService());
                servletContext.setAttribute( "managerService", rootService.getManagerService());
                servletContext.setAttribute( "jobService", rootService.getJobService());
                servletContext.setAttribute( "applicationService", rootService.getApplicationService());
            } catch (SQLException sqe) {
                sqe.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/managerregister.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        ManagerService managerService = (ManagerService) servletContext.getAttribute( "managerService" );

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");
        String company = req.getParameter("company");
        String title = req.getParameter("title");
        String industry = req.getParameter("industry");

        Manager newManager = new Manager(username, password, company, industry);
        if(!password.equals(confirm)) {
            // password and confirm password has to be the same
            req.setAttribute("registerError", "Register Failed: Passwords do not match");
            RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/managerregister.jsp");
            view.forward(req, resp);
            return;
        }

        try {
            if(managerService.insertOneRecord(newManager)) {
                // if registered account
                // set current account
                HttpSession session = req.getSession(true);
                req.setAttribute("registerError", null);
                session.setAttribute("currentAccount", (Account) newManager);
                log("currentAccount was added after login: " + newManager);
                // redirect to home page with information
                resp.sendRedirect("");
            } else {
                // display error at register page
                req.setAttribute("registerError", "Register Failed: Username has been taken");
                log("Register Failed: Username has been taken");
                RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/managerregister.jsp");
                view.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
