package com.cafe;

import com.cafe.model.FoodType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "foodservlet",
        urlPatterns = "/food"
)
public class FoodServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        // create new cafe object if not found
        Cafe cafe = (Cafe) servletContext.getAttribute( "cafe" );
        Customer customer = null;
        if(cafe == null) {
            cafe = new Cafe();
            customer = new Customer("Alex", "alex@gmail.com");
            cafe.addCustomer(customer);

            // save it to the application scope
            servletContext.setAttribute( "cafe", cafe );
            servletContext.setAttribute( "customer", customer);
        } else {
            customer = (Customer) servletContext.getAttribute( "customer" );
        }

        // open order page
        req.setAttribute("goods", cafe.getGoods());
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/food.jsp");
        view.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        Cafe cafe = (Cafe) servletContext.getAttribute( "cafe" );
        Customer customer = (Customer) servletContext.getAttribute( "customer" );

        String itemName = req.getParameter("Item");
        int itemQuantity = Integer.parseInt(req.getParameter("Quantity"));
        // todo: throw invalid food string
        if(cafe.validGood(itemName)) {
            cafe.transaction(customer, itemName, itemQuantity);
        }
        resp.sendRedirect("bill");
    }
}
