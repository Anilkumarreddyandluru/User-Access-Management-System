<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #cccccc;
    border-radius: 4px;
    font-size: 14px;
    margin-bottom: 15px;
}

select {
    width: 100%;
    padding: 10px;
    border: 1px solid #cccccc;
    border-radius: 4px;
    font-size: 14px;
    margin-bottom: 15px;
}

</style>
<body>


        <h2>Request Access to Software</h2>
    <form action="requestAccess" method="post">
        <label for="softwareName">Software:</label>
        <select id="softwareName" name="softwareName" required>
            <option value="Software2">java</option>
            <option value="Software2">python</option>
            <!-- Populate dynamically if needed -->
        </select>
        <br><br>

        <label for="accessType">Access Type:</label>
        <select id="accessType" name="accessType" required>
            <option value="Read">Read</option>
            <option value="Write">Write</option>
            <option value="Admin">Admin</option>
        </select>
        <br><br>

        <label for="reason">Reason:</label>
        <textarea id="reason" name="reason" rows="4" cols="50" required></textarea>
        <br><br>

        <button type="submit">Submit Request</button>
    </form>

</body>
</html>