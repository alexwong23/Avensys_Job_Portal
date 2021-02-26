<%@ page import ="java.util.*" %>
<%@ page import ="com.jobseek.model.Account" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<!DOCTYPE html>
<html>
    <tag:header/>
    <body>
        <% Account currentAccount = (Account) session.getAttribute("currentAccount"); %>
        <tag:navbar userSession="<%= currentAccount %>">
        </tag:navbar>

        <div class="jumbotron">
            <h1>Hiring Manager Login</h1>
            <p>Please login to create jobs.</p>
        </div>

        <div class="container">
            <div class="row justify-content-md-center">
                <div class="myform form ">
                  <form action="managerlogin" method="post">
                     <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" class="form-control" placeholder="Enter Username" required>
                     </div>
                     <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="Enter Password" required>
                     </div>
                     <%
                        String loginError = (String) request.getAttribute("loginError");
                        if(loginError != null) {
                            out.println("<p>" + loginError + "</p>");
                        }
                     %>

                     <div class="col-md-12 text-center">
                        <button type="submit" class=" btn btn-block btn-primary">Login</button>
                     </div>
                     <hr>
                     <div class="form-group">
                        <p class="text-center">Don't have an account? <a href="./managerregister">Register here</a></p>
                     </div>
                  </form>
              </div>
            </div>
        </div>

        <br>

        <tag:footer/>
        <tag:script/>
    </body>
</html>
