package com.jobseek;

import com.jobseek.model.*;
import com.jobseek.service.ApplicationService;
import com.jobseek.service.JobService;
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
        name = "historyservlet",
        urlPatterns = "/history"
)
public class HistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        // redirect to login page if user is NOT logged in as a job seeker
        HttpSession session = req.getSession(true);
        Account currentAccount = (Account) session.getAttribute( "currentAccount" );
        if(currentAccount == null) {
            resp.sendRedirect("./seekerlogin");
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

        JobService jobService = rootService.getJobService();
        try {
            // show seeker or manager jobs
            req.setAttribute("jobs", jobService.getRecordsByAccount(currentAccount));
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/history.jsp");
        view.forward(req, resp);
        return;
    }

}
