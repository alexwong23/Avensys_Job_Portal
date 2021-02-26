<%@ page import ="com.jobseek.model.Account" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<!DOCTYPE html>
<html lang="en">
    <tag:header/>
    <body>
        <% Account currentAccount = (Account) session.getAttribute("currentAccount"); %>
        <tag:navbar userSession="<%= currentAccount %>">
        </tag:navbar>

        <div class="homepage" style="background-image: url('/jobseek/images/find_job.jpg'); height: 500px; filter: brightness(90%); ">
            <a class="btn btn-success center" href="/jobseek/jobs" role="button">View Jobs</a>
        </div>

        <tag:footer/>
        <tag:script/>
    </body>
</html>
