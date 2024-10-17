<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Video Tutorial</title>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #121212;
            color: #e0e0e0;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #1a73e8;
            padding: 20px;
            color: white;
            text-align: center;
            font-size: 28px;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #1e1e1e;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: white;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            font-size: 16px;
            margin-bottom: 8px;
            color: #e0e0e0;
        }
        input[type="text"], select {
            padding: 10px;
            border-radius: 5px;
            border: none;
            margin-bottom: 15px;
            background-color: #333;
            color: #e0e0e0;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #1a73e8;
            color: white;
            padding: 10px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.2s ease;
        }
        input[type="submit"]:hover {
            background-color: #135cbf;
        }
    </style>
    
    
    
</head>
<body>

<script src="js/InsertValidation.js"></script>
    <div class="header">Add Video Tutorial</div>
 
    <div class="container">
        <h2>Insert Video Details</h2>
        <form action="insertVideo" method="post" onsubmit="return validateForm()">
            <label for="title">Title</label>
            <input type="text" id="title" name="title" >

            <label for="youtubeUrl">YouTube URL</label>
            <input type="text" id="youtubeUrl" name="youtubeUrl" >

            <label for="category">Category</label>
            <select id="category" name="category" >
                <option value="">Select a category</option>
                <option value="Career Development">Career Development</option>
                <option value="IT Security and Best Practices">IT Security and Best Practices</option>
                <option value="Technical Skills">Technical Skills</option>
                <option value="Creative Skills">Creative Skills</option>
                <option value="Professional Development">Professional Development</option>
               
            </select>

            <input type="submit" value="Add Video" name="submit">
        </form>
    </div>
    
   
</body>
</html>
