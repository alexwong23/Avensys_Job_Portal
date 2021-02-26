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
            <h1>Seeker Register</h1>
            <p>Register an account to apply for jobs</p>
        </div>

        <div class="container">
            <div class="row justify-content-md-center">
                <div class="myform form ">
                  <form action="seekerregister" method="post">
                     <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" class="form-control" placeholder="Enter Username" required>
                     </div>
                     <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="Enter Password" required>
                     </div>
                     <div class="form-group">
                         <label for="confirm">Confirm Password</label>
                         <input type="password" id="confirm" name="confirm" class="form-control" placeholder="Confirm Password" required>
                     </div>
                     <div class="form-group">
                         <label for="educationSelect">Select your highest level of education</label>
                         <select class="form-control" id="educationSelect" name="educationLevel" size="3">
                             <option value="primary" selected>Primary</option>
                             <option value="secondary">Secondary</option>
                             <option value="tertiary">Short-cycle Tertiary</option>
                             <option value="bachelor">Bachelor's or equivalent</option>
                             <option value="master">Master's or equivalent</option>
                             <option value="doctoral">Doctoral or equivalent</option>
                         </select>
                     </div>
                     <div class="form-group">
                         <label for="school">School</label>
                         <input type="text" id="school" name="school" class="form-control" placeholder="Enter the name of your School">
                     </div>
                     <div class="form-group">
                         <label for="yearSelect">Year Graduated</label>
                         <select class="form-control" id="yearSelect" name="yearGraduated" size="3">
                             <% for(int i = 2021; i >= 1900; i--) { %>
                                 <option value=<%= i %> selected><%= i %></option>
                             <% } %>
                         </select>
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
                        <p class="text-center">Have an account? <a href="./seekerlogin">Login here</a></p>
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
