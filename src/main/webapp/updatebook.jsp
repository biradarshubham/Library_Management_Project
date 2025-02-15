<%@page import="edu.lib.Entity.Library"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Book..</title>
<style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f5f5f5;
            padding: 20px;
        }
        .update-title{
        	padding: 20px;
        	font-size: 1.7rem;
        	text-align: center;
        }

        .form-container {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
        }

        .form-header {
            display: flex;
            align-items: center;
            gap: 10px;
            margin-bottom: 2rem;
        }

        .book-icon {
            width: 24px;
            height: 24px;
        }

        .form-title {
            font-size: 1.5rem;
            color: #333;
        }

        .form-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 1rem;
        }

        @media (max-width: 600px) {
            .form-grid {
                grid-template-columns: 1fr;
            }
        }

        .form-group {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }

        .form-group.full-width {
            grid-column: 1 / -1;
        }

        label {
            font-size: 0.9rem;
            color: #666;
        }

        input, textarea {
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
            width: 100%;
        }

        textarea {
            resize: vertical;
            min-height: 100px;
        }

        button {
            background-color: #2ecc71;
            color: white;
            padding: 0.75rem 2rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.2s;
        }

        button:hover {
            background-color: #27ae60;
        }
    </style>
</head>
<body>
	
	<div class="form-container">
	    <%
		Library lib=(Library)request.getAttribute("library");
		%>
		<h1 class="update-title"> ..UPDATE BOOK..</h1>
		
        <form action="updatebook" enctype="multipart/form-data" method="get">
            <div class="form-grid">
            	<div class="form-group">
                    <label for="bookid">Book Id</label>
                    <input type="number" id="bookid" name="bookid" value="<%=lib.getBookId() %>" readonly=true>
                </div>
                
                <div class="form-group">
                    <label for="bookname">Book Name</label>
                    <input type="text" id="bookname" name="bookname" value="<%=lib.getBookname() %>">
                </div>

                <div class="form-group">
                    <label for="author">Author</label>
                    <input type="text" id="author" name="author" value="<%=lib.getAuthor() %>">
                </div>
                
                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" step="0.01" id="price" name="price" value="<%= lib.getPrice()%>">
                </div>

                <div class="form-group">
                    <label for="pages">Number of Pages</label>
                    <input type="number" id="pages" name="pages" value="<%= lib.getPages() %>">
                </div>

                <div class="form-group">
                    <label for="publicationdate">Publication Date</label>
                    <input type="date" id="publicationDate" name="publicationdate" value="<%=lib.getPublicationdate()  %>">
                </div>
                
                <div class="form-group full-width">
                    <button type="submit" value="updatebook">Submit</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
               