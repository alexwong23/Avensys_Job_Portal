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
import java.util.List;

@WebServlet(
        name = "billservlet",
        urlPatterns = "/bill"
)
public class BillServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        // create new cafe object if not found
        Cafe cafe = (Cafe) servletContext.getAttribute( "cafe" );
        Customer customer = null;
        if(cafe == null) {
            cafe = new Cafe();
            customer = new Customer("Alex", "alex@gmail.com");

            // save it to the application scope
            servletContext.setAttribute( "cafe", cafe );
            servletContext.setAttribute( "customer", customer);
        } else {
            customer = (Customer) servletContext.getAttribute( "customer" );
        }

        // open order page
        req.setAttribute("bill", cafe.getUnpaidCustomerBill(customer));
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/bill.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        Cafe cafe = (Cafe) servletContext.getAttribute( "cafe" );
        Customer customer = (Customer) servletContext.getAttribute( "customer" );

        // todo: throw error if unable to pay
        cafe.payBill(customer);

        // update bill variable
        req.setAttribute("bill", cafe.getUnpaidCustomerBill(customer));

        resp.sendRedirect("history");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        Cafe cafe = (Cafe) servletContext.getAttribute( "cafe" );
        Customer customer = (Customer) servletContext.getAttribute( "customer" );

        String itemName = req.getParameter("Item");

        // todo: throw invalid parameters
        cafe.delete(customer, itemName);

        resp.sendRedirect("bill");
    }
}
