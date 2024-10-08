<%--
  Created by IntelliJ IDEA.
  User: givem
  Date: 10/5/2024
  Time: 2:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>HelpDesk - Create Account</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="../CSS/login.css">
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      color: white;
      overflow: hidden;
    }
    .split-page {
      display: flex;
      height: 100vh;
    }
    .left-half {
      flex: 1;
      background-color: #2c3e50;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .right-half {
      flex: 1;
      background-color: #34495e;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .signup-container {
      width: 80%;
      max-width: 400px;
    }
    .signup-logo img {
      width: 120px;
      height: auto;
      margin-bottom: 20px;
      align-content: center;
    }
    h2 {
      font-size: 2.5rem;
      margin-bottom: 30px;
      color: white;
      font-weight: bolder;
    }
    .form-label {
      font-size: 1rem;
    }
    .form-control {
      background-color: rgba(255, 255, 255, 0.1);
      border: none;
      color: white;
    }
    .form-control::placeholder {
      color: rgba(255, 255, 255, 0.5);
    }
    .btn-custom {
      background-color: #3498db;
      color: white;
      font-size: 1.1rem;
      padding: 10px;
    }
    .btn-custom:hover {
      background-color: #2980b9;
    }
    .right-half img {
      max-width: 80%;
      height: auto;
    }
  </style>
</head>
<body>

<div class="split-page">
  <!-- Left side for signup -->
  <div class="left-half">
    <div class="signup-container">
      <h2>Create Account</h2>
      <form action="/UserControllerServlet" method="POST">
        <input type="hidden" name="command" value="CREATE_ACCOUNT">
        <div class="mb-3">
          <label for="name" class="form-label">Name</label>
          <input type="text" class="form-control" name="fullName" required placeholder="Enter your full name">
        </div>
        <div class="mb-3">
          <label for="email" class="form-label">Email address</label>
          <input type="email" class="form-control" name="email" required placeholder="Enter your email">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input type="password" class="form-control" name="password" required placeholder="Enter your password">
        </div>
        <div class="mb-3">
          <label for="confirmPassword" class="form-label">Confirm Password</label>
          <input type="password" class="form-control" name="confirmPassword" required placeholder="Confirm your password">
        </div>
        <br>
        <button type="submit" class="btn btn-custom w-100">Create Account</button>
      </form>

      <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
      %>
      <div style="color: #e74c3c; margin-top: 15px;">
        <%= errorMessage %>
      </div>
      <%
        }
      %>

    </div>
  </div>

  <!-- Right side for image -->
  <div class="right-half">
    <img src="../img/login.svg" alt="Right Side Image">
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>