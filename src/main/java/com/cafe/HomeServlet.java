package com.cafe;

import com.cafe.model.Account;
import com.cafe.model.Cafe;
import com.cafe.model.Customer;
import com.cafe.model.FoodType;
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
import java.util.List;

@WebServlet(
        name = "homeServlet",
        urlPatterns = ""
)
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        /* TODO: do not delete and create new food table
            if connection to food table not established
            delete and create new food table
            populate food table with fake data
         */
        FoodService foodService = (FoodService) servletContext.getAttribute( "foodService" );
        if(foodService == null) {
            try {
                foodService = new FoodService();
                foodService.createTable();
                foodService.populateTable();
                // save it to the application scope
                servletContext.setAttribute( "foodService", foodService );
            } catch (SQLException sqe) {
                sqe.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher view = req.getRequestDispatcher("index.jsp");
        view.forward(req, resp);
    }
}
