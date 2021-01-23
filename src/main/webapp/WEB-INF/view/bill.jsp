<%@ page import ="java.util.*" %>
<%@ page import ="com.cafe.Item" %>
<%@ page import ="com.cafe.Bill" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<!DOCTYPE html>
<html>
    <tag:header/>
    <body>
        <tag:navbar/>
        <div class="jumbotron">
            <h1>My Bill</h1>
            <p>We accept the following payment methods: VISA/Mastercard/Cryptocurrencies/Cash.</p>
        </div>

        <div class="container">
            <div class="row justify-content-md-center">
              <table class="table">
                <thead class="thead-light">
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price($)</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Subtotal($)</th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                    <form method="post" action="bill">
                        <input type="hidden" id="hiddenItem" name="Item" value="">
                        <%
                            Bill bill = (Bill) request.getAttribute("bill");
                            HashMap<Item, Integer> billHashMap = bill.getItems();
                            int counter = 1;
                            for(Map.Entry<Item, Integer> entry: billHashMap.entrySet()) {
                                out.println("<tr>");
                                out.println("<th scope=\"row\">" + counter + "</th>");
                                out.println("<td>" + entry.getKey().getName() + "</th>");
                                out.println("<td>$" + entry.getKey().getPrice() + "</th>");
                                out.println("<td>" + entry.getValue() + "</th>");
                                out.println("<td>$" + entry.getKey().getPrice() * entry.getValue() + "</th>");
                                out.println("<td><button type=\"submit\" id=\"deleteItem\" class=\"btn btn-danger\"value=" + entry.getKey().getName() + ">Delete</button></th>");
                                out.println("</tr>");
                                counter++;
                            }
                        %>
                        <tr>
                            <th></th>
                            <td></td>
                            <td></td>
                            <td>Total</td>
                            <td>
                                <% out.println("$" + bill.getTotal()); %>
                            </td>
                            <td></td>
                        </tr>
                    </form>
                </tbody>
              </table>

              <br>
              <a class="btn btn-primary" href="/cafe/food" role="button">Buy more food</a>
            </div>
        </div>
        <br>

        <tag:footer/>
        <tag:script/>
    </body>
</html>
