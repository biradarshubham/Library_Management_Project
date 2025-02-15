package edu.lib.LibraryCotroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mysql.cj.Session;

import edu.lib.Entity.Library;
import edu.lib.LibraryDao.LibraryDao;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/addbook")
public class AddBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("bookid"));
        String bookname = req.getParameter("bookname");
        String author = req.getParameter("author");
        double price = Double.parseDouble(req.getParameter("price"));
        int pages = Integer.parseInt(req.getParameter("pages"));
        Date publicationdate = Date.valueOf(req.getParameter("publicationdate"));
        
        Part filePart = req.getPart("image");//get selected images file name 
        String imgfilename=filePart.getSubmittedFileName();
        
        Library library = new Library();
        library.setBookId(id);
        library.setBookname(bookname);
        library.setAuthor(author);
        library.setPrice(price);
        library.setPages(pages);
        library.setPublicationdate(publicationdate);
        library.setImage(imgfilename);
        
        String uploadfile="C:/Users/shubh/OneDrive/Documents/advancejava/javaServerPages/Library_Management/src/main/webapp/images/"+imgfilename;//upload path where we have to upload our actual image
		FileOutputStream fileOutputStream=new FileOutputStream(uploadfile);
		
		InputStream inputStream = filePart.getInputStream();
		
		byte[] data=new byte[inputStream.available()];
		inputStream.read(data);
		fileOutputStream.write(data);
		fileOutputStream.close();

        try {
            LibraryDao dao = new LibraryDao();
            int res = dao.addbook(library);
            HttpSession session=req.getSession();
            if (res != 0) {
                session.setAttribute("addbook", "book added successfully....");
                resp.sendRedirect("fetchbooks");
                
            } else {
            	session.setAttribute("nobookfromad", "No Books Are Available");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
