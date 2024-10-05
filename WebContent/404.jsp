<%--
  Created by IntelliJ IDEA.
  User: givem
  Date: 10/5/2024
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Page Not Found</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
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
        .error-container {
            width: 80%;
            max-width: 400px;
            text-align: center;
        }
        .error-logo img {
            width: 120px;
            height: auto;
            margin-bottom: 20px;
        }
        h1 {
            font-size: 4rem;
            margin-bottom: 20px;
            font-weight: bolder;
        }
        h2 {
            font-size: 2rem;
            margin-bottom: 30px;
            font-weight: bolder;
        }
        p {
            font-size: 1.1rem;
            margin-bottom: 30px;
        }
        .btn-custom {
            background-color: #3498db;
            color: white;
            font-size: 1.1rem;
            padding: 10px 20px;
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
    <!-- Left side for error message -->
    <div class="left-half">
        <div class="error-container">
            <div class="error-logo">
                <img src="img/error.png" alt="HelpDesk Logo">
            </div>
            <h1>404</h1>
            <h2>Page Not Found</h2>
            <p>Oops! The page you're looking for doesn't exist or has been moved.</p>
            <a href="index.jsp" class="btn btn-custom">Go to Homepage</a>
        </div>
    </div>

    <!-- Right side for image -->
    <div class="right-half">
        <img src="img/err.svg" alt="404 Error Illustration">
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
