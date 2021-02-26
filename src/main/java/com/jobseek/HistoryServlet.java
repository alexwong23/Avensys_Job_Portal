package com.jobseek;

import com.jobseek.model.*;
import com.jobseek.service.ApplicationService;
import com.jobseek.service.JobService;

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
            resp.sendRedirect("./login");
            return;                                     // required so it does not execute rest of code
        }

        /* TODO: do not delete and create new account table
            if connection to account table not established
            delete and create new account table
            populate account table with fake data
         */
        JobService jobService = (JobService) servletContext.getAttribute( "jobService" );
        try {
            if(jobService == null) {
                jobService = new JobService();
                jobService.createTable();
                jobService.populateTable();
                // save it to the application scope
                servletContext.setAttribute("jobService", jobService);
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            // show my jobs
            req.setAttribute("jobs", jobService.getRecordsByManagerID(currentAccount));
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/jobs.jsp");
        view.forward(req, resp);
        return;
    }

}
