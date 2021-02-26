<%@ page import ="java.util.*" %>
<%@ page import ="com.cafe.model.Item" %>
<%@ page import ="com.cafe.model.Bill" %>
<%@ page import ="com.cafe.model.Seeker" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<!DOCTYPE html>
<html>
    <tag:header/>
    <body>
        <% Seeker currentSeeker = (Seeker) session.getAttribute("currentSeeker"); %>
        <tag:navbar userSession="<%= currentSeeker %>">
        </tag:navbar>

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
                    <form method="delete" action="bill">
                        <input type="hidden" id="hiddenItem" name="Item" value="">
                        <%
                            Bill bill = (Bill) request.getAttribute("bill");
                            HashMap<Item, Integer> billHashMap = bill.getItems();
                            int counter = 1;
                            for(Map.Entry<Item, Integer> entry: billHashMap.entrySet()) {
                                out.println("<tr>");
                                out.println("<th scope=\"row\">" + counter + "</th>");
                                out.println("<td>" + entry.getKey().getName() + "</td>");
                                out.println("<td>$" + entry.getKey().getPrice() + "</td>");
                                out.println("<td>" + entry.getValue() + "</td>");
                                out.println("<td>$" + entry.getKey().getPrice() * entry.getValue() + "</td>");
                                out.println("<td><button type=\"submit\" id=\"deleteItem\" class=\"btn btn-danger\"value=" + entry.getKey().getName() + ">Delete</button></td>");
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
              <a class="btn btn-secondary" href="/cafe/food" role="button">Buy more food</a>
              &nbsp;
              <form method="post" action="bill">
                 <button type="submit" class="btn btn-success">Pay Bill</button>
              </form>
            </div>
        </div>

        <form method="post" action="food">
            <fieldset>
                <legend>What would you like to buy?</legend>
                <div class="form-group">
                    <label for="itemSelect">Select an Item</label>
                    <select class="form-control" id="itemSelect" name="Item">
                    <%
                        for(int i = 0; i < result.size(); i++) {
                            out.println("<option>" + result.get(i).getName() + "</option>");
                        }
                    %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="quantitySelect">Select a Quantity</label>
                    <select class="form-control" id="quantitySelect" name="Quantity" size="3">
                        <option value="1" selected>1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Buy</button>
            </fieldset>
       </form>
        <br>

        <tag:footer/>
        <tag:script/>
    </body>
</html>
