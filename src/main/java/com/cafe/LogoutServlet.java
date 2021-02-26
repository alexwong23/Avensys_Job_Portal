package com.cafe;

import com.cafe.model.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        Seeker currentSeeker = (Seeker) session.getAttribute( "currentAccount" );
        if(currentSeeker != null) {
            session.setAttribute( "currentAccount", null);
            resp.sendRedirect("");
        } else {
            resp.sendRedirect("");
        }
    }
}
