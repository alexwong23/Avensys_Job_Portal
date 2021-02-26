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
        name = "managerloginservlet",
        urlPatterns = "/managerlogin"
)
public class ManagerLoginServlet extends HttpServlet {

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

        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/managerlogin.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        ManagerService managerService = (ManagerService) servletContext.getAttribute( "managerService" );

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Manager loginManager = managerService.getOneRecord(new Manager(username, password));

            // if login credentials correct
            if(loginManager != null) {
                HttpSession session = req.getSession(true);
                req.setAttribute("loginError", null);
                session.setAttribute("currentAccount", (Account) loginManager);
                // redirect to home page with information
                resp.sendRedirect("");
            } else {
                // display error at login page
                req.setAttribute("loginError", "Invalid username or password");
                RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/managerlogin.jsp");
                view.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
