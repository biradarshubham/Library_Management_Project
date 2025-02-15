package edu.lib.LibraryCotroller;

import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.lib.Entity.Library;
import edu.lib.LibraryDao.LibraryDao;

@WebServlet("/findById")
public class FindBookById extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	int bookid=Integer.parseInt(req.getParameter("bookid")); 
	LibraryDao dao=new LibraryDao();
	try {
		Library library=dao.findById(bookid);
		RequestDispatcher rd=req.getRequestDispatcher("updatebook.jsp");
		req.setAttribute("library", library);
		rd.include(req, resp);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
