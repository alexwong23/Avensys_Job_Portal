package com.cafe;

import com.cafe.model.*;
import com.cafe.service.AccountService;
import com.cafe.service.FoodService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "loginservlet",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {

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

        /* TODO: do not delete and create new account table
            if connection to account table not established
            delete and create new account table
            populate account table with fake data
         */
        AccountService accountService = (AccountService) servletContext.getAttribute( "accountService" );
        if(accountService == null) {
            try {
                accountService = new AccountService();
                accountService.createTable();
                accountService.populateTable();
                // save it to the application scope
                servletContext.setAttribute( "accountService", accountService );
            } catch (SQLException sqe) {
                sqe.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/login.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        AccountService accountService = (AccountService) servletContext.getAttribute( "accountService" );

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");

        try {
            Account loginAccount = accountService.getOneRecordByType(new Account(username, password, type));
            // if login credentials correct
            if(loginAccount != null) {
                // set current account
                HttpSession session = req.getSession(true);
                req.setAttribute("loginError", null);
                session.setAttribute("currentAccount", loginAccount);
                log("currentAccount was added after login: " + loginAccount);
                // redirect to home page with information
                resp.sendRedirect("");
            } else {
                // display error at login page
                req.setAttribute("loginError", "Invalid username or password");
                log("Invalid username or password");
                RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/login.jsp");
                view.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
