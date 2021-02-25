<%@ page import ="java.util.*" %>
<%@ page import ="com.cafe.model.Item" %>
<%@ page import ="com.cafe.model.Account" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<!DOCTYPE html>
<html>
    <tag:header/>
    <body>
        <% Account currentAccount = (Account) session.getAttribute("currentAccount"); %>
        <tag:navbar userSession="<%= currentAccount %>">
        </tag:navbar>

        <div class="jumbotron">
            <h1>Menu</h1>
            <p>We serve only the finest quality items.</p>
        </div>

        <div class="container">
            <div class="row justify-content-md-center">
              <table class="table">
                <thead class="thead-light">
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price($)</th>
                    <th scope="col">Stock</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                        List<Item> result= (List<Item>) request.getAttribute("goods");

                        for(int i = 0; i < result.size(); i++) {
                            out.println("<tr>");
                            out.println("<th scope=\"row\">" + (i + 1) + "</th>");
                            out.println("<td>" + result.get(i).getName() + "</td>");
                            out.println("<td>$" + result.get(i).getPrice() + "</td>");
                            out.println("<td> --- </td>");
                            out.println("</tr>");
                        }
                    %>
                </tbody>
              </table>

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
            </div>
        </div>

        <br>

        <tag:footer/>
        <tag:script/>
    </body>
</html>
