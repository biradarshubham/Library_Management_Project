<%@page import="java.util.List"%>
<%@page import="edu.lib.Entity.Library"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Management home</title>
    <link rel="stylesheet" type="text/css" href="home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script>
	    function searchBooks() {
	        document.getElementById("searchForm").submit();
	    }

    </script>
</head>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <h2>LIBRARY <b>MANAGEMENT</b> <i class="fa-solid fa-book" style="color:purple"></i></h2>
                <form id="searchForm" action="search">
				    <div class="searching">
				        <input type="text" id="searchInput" name="search" placeholder="Enter for searching">
				        <button type="submit" onclick="searchBooks()"><i class="fa-solid fa-magnifying-glass"></i></button>
				    </div>
				</form>

            </div>
            <div class="Add">
                <a href="addbook.html" class="btn btn-success"><span>Add New Book</span></a>
            </div>
            <h1 class="addbook">Get All <a href="fetchbooks">Books</a></h1>
            
            <%
                String searchQuery = request.getParameter("search");
                List<Library> list = (searchQuery != null && !searchQuery.trim().isEmpty()) ? 
                                     (List<Library>) session.getAttribute("searchbooks") : 
                                     (List<Library>) session.getAttribute("library");
                if (list != null) {
            %>
            
            <table class="table">
                <thead>
                    <tr>
                        <th>Book Image</th>
                        <th>Book Id</th>
                        <th>Book Name</th>
                        <th>Author Name</th>
                        <th>Book Price</th>
                        <th>Book Pages</th>
                        <th>Publication Date</th>
                        <th>Operation</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Library e1 : list) {
                    %>
                    <tr>
                        <td><img src="images/<%= e1.getImage() %>" alt="<%= e1.getImage() %>" width="100" height="120"></td>
                        <td><%= e1.getBookId() %></td>
                        <td><%= e1.getBookname() %></td>
                        <td><%= e1.getAuthor() %></td>
                        <td><%= e1.getPrice() %></td>
                        <td><%= e1.getPages() %></td>
                        <td><%= e1.getPublicationdate() %></td>
                        <td class="operations">
                            <a href="findById?bookid=<%= e1.getBookId() %>" class="update">Update</a>
                            <a href="remove?bookid=<%= e1.getBookId() %>&image=<%= e1.getImage() %>" class="delete">Delete</a>
                        </td>
                    </tr>
                    <%
                        } // End for-each loop
                    %>
                </tbody>
            </table>
            <%
                } // End if block
            %>
            
            <%
                String updatedMsg = (String) session.getAttribute("updatedmsg");
                String removedMsg = (String) session.getAttribute("removed");
                String addBookMsg = (String) session.getAttribute("addbook");
                String nobookfromadd =(String) session.getAttribute("nobookfromad");
                String nobookfromfetch =(String) session.getAttribute("nobookfromfetch");
               	String removebookno =(String) session.getAttribute("removebookno");
                String nobookfromsearch=(String) session.getAttribute("nobookfromsearch");
               	String updatebook=(String) session.getAttribute("updatebook");
               	
                String finalMsg = null;
                
                if (updatedMsg != null) {
                    finalMsg = updatedMsg;
                    session.removeAttribute("updatedmsg");
                } else if (removedMsg != null) {
                    finalMsg = removedMsg;
                    session.removeAttribute("removed");
                } else if (addBookMsg != null) {
                    finalMsg = addBookMsg;
                    session.removeAttribute("addbook");
                }else if (nobookfromadd!=null){
                	finalMsg = addBookMsg;
                    session.removeAttribute("nobookfromad");
                }else if(nobookfromfetch!=null){
               		finalMsg = addBookMsg;
                    session.removeAttribute("nobookfromfetch");
                }else if(removebookno!=null){
               		finalMsg = addBookMsg;
                    session.removeAttribute("removebookno");
                }else if(nobookfromsearch!=null){
                	finalMsg = addBookMsg;
                    session.removeAttribute("nobookfromsearch");
                }else if(updatebook!=null){
                	finalMsg = addBookMsg;
                    session.removeAttribute("updatebook");
                }

                
                if (finalMsg != null) {
            %>
            <h1 class="msg" id="msgBox"><%= finalMsg %></h1>
            
            <script>
                setTimeout(function() {
                    var msgBox = document.getElementById("msgBox");
                    if (msgBox) {
                        msgBox.style.display = "none";
                    }
                }, 2000);
            </script>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
