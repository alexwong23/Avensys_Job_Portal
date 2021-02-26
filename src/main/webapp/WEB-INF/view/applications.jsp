<%@ page import ="java.util.*" %>
<%@ page import ="com.jobseek.model.Account" %>
<%@ page import ="com.jobseek.model.Seeker" %>
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
            <% Job job = (Job) request.getAttribute("job"); %>
            <h1>Application Details</h1>
            <h5><%= job.getTitle() %> at <%= job.getCompany() %> for $<%= job.getSalary() %></h5>
        </div>

        <div class="container">
            <div class="row justify-content-md-center">
              <table class="table">
                <thead class="thead-light">
                  <tr>
                    <th scope="col">Seeker ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">Education Level</th>
                    <th scope="col">School</th>
                    <th scope="col">Year Graduated</th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                    <form method="post" action="acceptseeker" id="applicationForm">
                    <input type="hidden" id="hiddenSeekerID" name="seekerID" value="">
                    <input type="hidden" id="hiddenJobID" name="jobID" value=value=<%= job.getJobID() %>>
                    <% List<Seeker> seekers = (List<Seeker>) request.getAttribute("seekers"); %>
                    <% for(Seeker s: seekers) { %>
                        <tr>
                        <th scope="row"><%= s.getAccountID() %></th>
                        <td><%= s.getUsername() %></td>
                        <td><%= s.getEducationLevel() %></td>
                        <td><%= s.getSchool() %></td>
                        <td>$<%= s.getYearGraduated() %></td>
                        <td>
                            <button type="submit" id="acceptSeeker" class="btn btn-success" value=<%= s.getAccountID() %>>Accept</button>
                            <button type="submit" id="rejectSeeker" class="btn btn-danger" value=<%= s.getAccountID() %>>Reject</button>
                        </td>
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
