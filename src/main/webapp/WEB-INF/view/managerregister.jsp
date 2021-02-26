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
            <h1>Hiring Manager Register</h1>
            <p>Register an account to create jobs</p>
        </div>

        <div class="container">
            <div class="row justify-content-md-center">
                <div class="myform form ">
                  <form action="managerregister" method="post">
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
                         <label for="company">Company Name</label>
                         <input type="text" id="company" name="company" class="form-control" placeholder="Enter the name of your Company">
                     </div>
                     <div class="form-group">
                         <label for="title">Job Title</label>
                         <input type="text" id="title" name="title" class="form-control" placeholder="Enter your job title">
                     </div>
                     <div class="form-group">
                         <label for="industrySelect">Select your industry</label>
                         <select class="form-control" id="industrySelect" name="industry" size="3">
                             <option value="Agriculture" selected>Agriculture</option>
                             <option value="Basic Metal Production">Basic Metal Production</option>
                             <option value="Chemical Industries">Chemical Industries</option>
                             <option value="Commerce">Commerce</option>
                             <option value="Construction">Construction</option>
                             <option value="Education">Education</option>
                             <option value="Financial / Professional Services">Financial / Professional Services</option>
                             <option value="F & B">F & B</option>
                             <option value="Forestry">Forestry</option>
                             <option value="Health Services">Health Services</option>
                             <option value="Tourism">Tourism</option>
                             <option value="Mining">Mining</option>
                             <option value="Mechanical and Electrical Engineering">Mechanical and Electrical Engineering</option>
                             <option value="Media / Culture">Media / Culture</option>
                             <option value="Oil and Gas">Oil and Gas</option>
                             <option value="Telecommunications">Telecommunications</option>
                             <option value="Public Service">Public Service</option>
                             <option value="Shipping">Shipping</option>
                             <option value="Textiles">Textiles</option>
                             <option value="Transport">Transport</option>
                             <option value="Utilities">Utilities</option>
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
                        <p class="text-center">Have an account? <a href="./managerlogin">Login here</a></p>
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
