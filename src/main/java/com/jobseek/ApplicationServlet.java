package com.jobseek;

import com.jobseek.model.*;
import com.jobseek.service.ApplicationService;
import com.jobseek.service.JobService;
import com.jobseek.service.RootService;
import com.jobseek.service.SeekerService;

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
        name = "ApplicationServlet",
        urlPatterns = "/applications/*"
)
public class ApplicationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        // redirect to login page if user is NOT logged in as a hiring manager
        HttpSession session = req.getSession(true);
        Account currentAccount = (Account) session.getAttribute( "currentAccount" );
        if(currentAccount == null || !currentAccount.getType().equals("manager")) {
            resp.sendRedirect("../managerlogin");
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

        SeekerService seekerService = rootService.getSeekerService();
        JobService jobService = rootService.getJobService();
        try {
            String jobIDString = req.getPathInfo();
            int jobID = Integer.parseInt(jobIDString.replace("/",""));
            log(String.valueOf(jobID));
            // show seekers who applied for this hiring manager's job
            req.setAttribute("seekers", seekerService.getRecordsByManagerAccountAndJobID(currentAccount, jobID));
            req.setAttribute("job", jobService.getOneRecordByID(jobID));
            log(jobService.getOneRecordByID(jobID).getTitle());
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/view/applications.jsp");
        view.forward(req, resp);
        return;
    }

}
