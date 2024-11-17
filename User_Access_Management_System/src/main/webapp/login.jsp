<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Signinpage</title>
<style>
/* General styling for body */
body {
    font-family: Arial, sans-serif;
    background-color: #f0f4f8;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

/* Styling for the login form container */
form {
    background-color: #ffffff;
    width: 300px;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

/* Form header */
h2 {
    text-align: center;
    color: #333333;
    margin-bottom: 20px;
}

/* Input fields */
label {
    font-size: 14px;
    color: #333333;
    display: block;
    margin-bottom: 8px;
}

input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #cccccc;
    border-radius: 4px;
    margin-bottom: 15px;
    font-size: 14px;
    box-sizing: border-box;
}

/* Submit button */
button[type="submit"] {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: #ffffff;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
}

button[type="submit"]:hover {
    background-color: #0056b3;
}

/* Error message styling */
.error {
    color: #d9534f;
    font-size: 14px;
    text-align: center;
    margin-bottom: 10px;
}


</style>

</head>
<body>
 <h2>Login</h2>
    <form action="login">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <button type="submit">Login</button>
    </form>

</body>
</html>