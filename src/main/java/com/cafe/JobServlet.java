package com.cafe;

import com.cafe.model.*;
import com.cafe.service.ApplicationService;
import com.cafe.service.JobService;
import com.cafe.service.SeekerService;

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
        name = "jobservlet",
        urlPatterns = "/jobs"
)
public class JobServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        // redirect to login page if user is NOT logged in
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
            // show only available jobs
            req.setAttribute("jobs", jobService.getRecordsByAvailability(true));
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/job.jsp");
        view.forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        /* TODO: do not delete and create new account table
            if connection to account table not established
            delete and create new account table
            populate account table with fake data
         */
        ApplicationService applicationService = (ApplicationService) servletContext.getAttribute( "applicationService" );
        if(applicationService == null) {
            try {
                applicationService = new ApplicationService();
                applicationService.createTable();
                // save it to the application scope
                servletContext.setAttribute( "applicationService", applicationService);
            } catch (SQLException sqe) {
                sqe.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        // redirect to login page if user is NOT logged in as a job seeker
        HttpSession session = req.getSession(true);
        Account currentAccount = (Account) session.getAttribute( "currentAccount" );
        if(currentAccount == null || !currentAccount.getType().equals("seeker")) {
            resp.sendRedirect("./login");
            return;                                     // required so it does not execute rest of code
        }

        // job seeker applies for a job

        String idString = req.getParameter("jobID");
        log("job ID parameter is " + idString);
        int jobID = 2;
        try {
            jobID = Integer.parseInt(idString);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("applyJobError", "Apply Job Failed: ID not an integer");
            RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/job.jsp");
            view.forward(req, resp);
            return;
        }

        try {
            if(applicationService.insertOneRecord(currentAccount, jobID)) {
                // redirect to home page with information
                resp.sendRedirect("");
                return;
            } else {
                // display error at register page
                req.setAttribute("applyJobError", "Apply Job Failed: Job record not inserted");
                RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/job.jsp");
                view.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
