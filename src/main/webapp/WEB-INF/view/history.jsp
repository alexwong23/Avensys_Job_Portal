<%@ page import ="java.util.*" %>
<%@ page import ="com.jobseek.model.Account" %>
<%@ page import ="com.jobseek.model.Job" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<!DOCTYPE html>
<html>
    <tag:header/>
    <body>
        <% Account currentAccount = (Account) session.getAttribute("currentAccount"); %>
        <tag:navbar userSession="<%= currentAccount %>">
        </tag:navbar>

        <div class="jumbotron">
            <h1>Job Market</h1>
            <p>All available jobs are listed below.</p>
        </div>

        <div class="container">
            <div class="row justify-content-md-center">
              <table class="table">
                <thead class="thead-light">
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Company</th>
                    <th scope="col">Title</th>
                    <th scope="col">Salary($)</th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                    <form method="post" action="seekerjobs" id="jobForm">
                    <input type="hidden" id="hiddenJobID" name="jobID" value="">
                    <% List<Job> availableJobs = (List<Job>) request.getAttribute("jobs"); %>
                    <% for(Job j: availableJobs) { %>
                        <tr>
                        <th scope="row"><%= j.getID() %></th>
                        <td><%= j.getCompany() %></td>
                        <td><%= j.getTitle() %></td>
                        <td>$<%= j.getSalary() %></td>
                        <% if(currentAccount.getType().equals("seeker")) { %>
                            <td><button type="submit" id="applyJob" class="btn btn-primary" value=<%= j.getID() %> >Apply</button></td>
                        <% } else if (currentAccount.getType().equals("manager")) { %>
                            <td><button type="submit" id="removeJob" class="btn btn-danger" value=<%= j.getID() %> >Remove</button></td>
                        <% } else { %>
                            <td><p>No action allowed</p></td>
                        <% } %>
                        </tr>
                    <% } %>
                </tbody>
              </table>
            </div>
        </div>

        <br>

        <tag:footer/>
        <tag:script/>
    </body>
</html>
