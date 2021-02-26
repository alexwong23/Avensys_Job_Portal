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
        name = "applyjobservlet",
        urlPatterns = "/applyjob"
)
public class ApplyJobServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        // redirect to login page if user is NOT logged in as a job seeker
        HttpSession session = req.getSession(true);
        Account currentAccount = (Account) session.getAttribute( "currentAccount" );
        if(currentAccount == null || !currentAccount.getType().equals("manager")) {
            resp.sendRedirect("./login");
            return;                                     // required so it does not execute rest of code
        }

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

        // job seeker applies for a job
        String idString = req.getParameter("jobID");
        log("job ID parameter is " + idString);
        int jobID = 2;
        try {
            jobID = Integer.parseInt(idString);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("applyJobError", "Apply Job Failed: ID not an integer");
            RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/jobs.jsp");
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
                RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/jobs.jsp");
                view.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}