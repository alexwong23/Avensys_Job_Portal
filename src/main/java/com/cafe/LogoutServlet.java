package com.cafe;

import com.cafe.model.*;
import com.cafe.service.AccountService;
import com.cafe.service.FoodService;
import com.sun.corba.se.spi.activation.LocatorPackage.ServerLocation;

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
        name = "logoutservlet",
        urlPatterns = "/logout"
)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        // redirect to home page if user is logged in
        HttpSession session = req.getSession(true);
        Account currentAccount = (Account) session.getAttribute( "currentAccount" );
        if(currentAccount != null) {
            session.setAttribute( "currentAccount", null);
            resp.sendRedirect("");
        } else {
            resp.sendRedirect("");
        }
    }
}
