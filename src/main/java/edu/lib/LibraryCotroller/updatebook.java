package edu.lib.LibraryCotroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import edu.lib.Entity.Library;
import edu.lib.LibraryDao.LibraryDao;

@WebServlet("/updatebook")
@MultipartConfig(maxFileSize = 16177215) // Handles file uploads
public class updatebook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookid = Integer.parseInt(req.getParameter("bookid"));
        String bookname = req.getParameter("bookname");
        String author = req.getParameter("author");
        double price = Double.parseDouble(req.getParameter("price"));
        int pages = Integer.parseInt(req.getParameter("pages"));
        Date publicationdate = Date.valueOf(req.getParameter("publicationdate"));
	
        Library library = new Library();
        library.setBookId(bookid);
        library.setBookname(bookname);
        library.setAuthor(author);
        library.setPrice(price);
        library.setPages(pages);
        library.setPublicationdate(publicationdate);

        try {
        	LibraryDao dao=new LibraryDao();
            int res = dao.updatebook(library);
            HttpSession session=req.getSession();
            if (res != 0) {
                session.setAttribute("updatedmsg", "Book updated successfully...");
                resp.sendRedirect("fetchbooks");
            } else {
                session.setAttribute("updatebook", "Opps Something Wents Wrong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
