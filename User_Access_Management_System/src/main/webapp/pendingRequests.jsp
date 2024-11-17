<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Pending Requests</title>
</head>
<body>
    <h2>Pending Requests</h2>

    <!-- Display Success or Error Messages -->
   

    <!-- Table of Pending Requests -->
    <table border="1">
        <tr>
            <th>Request ID</th>
            <th>Employee Name</th>
            <th>Software</th>
            <th>Access Type</th>
            <th>Reason</th>
            <th>Action</th>
        </tr>
       
            <tr>
                <td>${request.id}</td>
                <td>${request.employeeName}</td>
                <td>${request.softwareName}</td>
                <td>${request.accessType}</td>
                <td>${request.reason}</td>
                <td>
                    <form action="approvalServlet"  style="display: inline;">
                        <input type="hidden" name="requestId" value="${request.id}" />
                        <button type="submit" name="action" value="approve">Approve</button>
                    </form>
                    <form action="approvalServlet"  style="display: inline;">
                        <input type="hidden" name="requestId" value="${request.id}" />
                        <button type="submit" name="action" value="reject">Reject</button>
                    </form>
                </td>
            </tr>
        
    </table>
</body>
</html>
