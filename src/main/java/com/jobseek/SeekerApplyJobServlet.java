package com.jobseek;

import com.jobseek.model.*;
import com.jobseek.service.ApplicationService;

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
        name = "SeekerApplyJobServlet",
        urlPatterns = "/seekerapplyjob"
)
public class SeekerApplyJobServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        // redirect to login page if user is NOT logged in as a job seeker
        HttpSession session = req.getSession(true);
        Account currentAccount = (Account) session.getAttribute( "currentAccount" );
        if(currentAccount == null || !currentAccount.getType().equals("seeker")) {
            resp.sendRedirect("./seekerlogin");
            return;                                     // required so it does not execute rest of code
        }

        ApplicationService applicationService = (ApplicationService) servletContext.getAttribute( "applicationService" );
        try {
            String idString = req.getParameter("jobID");
            int jobID = Integer.parseInt(idString);
            if(applicationService.insertOneRecord(currentAccount, jobID)) {
                resp.sendRedirect("./history");
                return;
            } else {
                resp.sendRedirect("./jobs"); // todo: error not displayed
                return;
            }
        } catch (ArithmeticException ae) {
            ae.printStackTrace();
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
