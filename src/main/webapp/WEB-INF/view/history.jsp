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
            <h1>My Jobs</h1>
            <p>My jobs are listed below.</p>
        </div>

        <div class="container">
            <div class="row justify-content-md-center">
              <table class="table">
                <thead class="thead-light">
                  <tr>
                    <th scope="col">Job ID</th>
                    <th scope="col">Company</th>
                    <th scope="col">Industry</th>
                    <th scope="col">Title</th>
                    <th scope="col">Salary($)</th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                    <form method="post" action="" id="jobForm">
                    <input type="hidden" id="hiddenJobID" name="jobID" value="">
                    <% List<Job> availableJobs = (List<Job>) request.getAttribute("jobs"); %>
                    <% for(Job j: availableJobs) { %>
                        <tr>
                        <th scope="row"><%= j.getJobID() %></th>
                        <td><%= j.getCompany() %></td>
                        <td><%= j.getIndustry() %></td>
                        <td><%= j.getTitle() %></td>
                        <td>$<%= j.getSalary() %></td>
                        <% if(currentAccount.getType().equals("seeker")) { %>
                            <td><button type="submit" id="seekerRemoveJob" class="btn btn-danger" value=<%= j.getJobID() %> >Remove</button></td>
                        <% } else if (currentAccount.getType().equals("manager")) { %>
                            <td>
                                <button id="managerViewApplications" class="btn btn-secondary" onclick="location.href='/jobseek/jobs/<%= j.getJobID() %>'" type="button">
                                    Applications
                                </button>
                                <button type="submit" id="managerRemoveJob" class="btn btn-danger" value=<%= j.getJobID() %>>Remove</button>
                            </td>
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
