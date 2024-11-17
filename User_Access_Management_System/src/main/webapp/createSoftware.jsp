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
    box-sizing: border-box;
    margin-bottom: 15px;
}

input[type="checkbox"] {
    margin-right: 5px;
}

p {
    font-size: 14px;
    margin: 10px 0;
}

</style>
<body>
 <h2>Create New Software</h2>

    <form action="createSoftware">
        <!-- Software Name -->
        <label for="name">Software Name:</label>
        <input type="text" id="name" name="name" required><br><br>

        <!-- Description -->
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" cols="50" required></textarea><br><br>

        <!-- Access Levels -->
        <label>Access Levels:</label><br>
        <input type="checkbox" id="read" name="access_levels" value="Read">
        <label for="read">Read</label><br>

        <input type="checkbox" id="write" name="access_levels" value="Write">
        <label for="write">Write</label><br>

        <input type="checkbox" id="admin" name="access_levels" value="Admin">
        <label for="admin">Admin</label><br><br>

        <!-- Submit Button -->
        <button type="submit">Add Software</button>
    </form>

</body>
</html>