<%@ page import ="java.util.*" %>
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
            <h1>Register</h1>
            <p>Please enter your username and password.</p>
        </div>

        <div class="container">
            <div class="row justify-content-md-center">
                <div class="myform form ">
                  <form action="register" method="post">
                     <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" class="form-control" placeholder="Enter Username" required>
                     </div>
                     <div class="form-group">
                         <label for="typeSelect">Select a Type</label>
                         <select class="form-control" id="typeSelect" name="type" size="2">
                             <option value="jobseeker" selected>Jobseeker</option>
                             <option value="company">Company</option>
                         </select>
                     </div>
                     <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="Enter Password" required>
                     </div>
                     <div class="form-group">
                         <label for="confirm">Confirm Password</label>
                         <input type="password" id="confirm" name="confirm" class="form-control" placeholder="Confirm Password" required>
                      </div>
                     <%
                        String registerError = (String) request.getAttribute("registerError");
                        if(registerError != null) {
                            out.println("<p>" + registerError + "</p>");
                        }
                     %>

                     <div class="form-group">
                        <p class="text-center">By registering, you accept our <a href="#">Terms Of Use</a></p>
                     </div>
                     <div class="col-md-12 text-center">
                        <button type="submit" class=" btn btn-block btn-primary">Register</button>
                     </div>
                     <hr>
                     <div class="form-group">
                        <p class="text-center">Have an account? <a href="./login">Login here</a></p>
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
