package edu.lib.LibraryCotroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.lib.Entity.Library;
import edu.lib.LibraryDao.LibraryDao;

@WebServlet("/search")
public class Searchbook extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String data=req.getParameter("search");
		
		LibraryDao dao=new LibraryDao();
		try {
			List<Library> books=dao.searchbooks(data);
			HttpSession session=req.getSession();
			if(books!=null) {
			
			session.setAttribute("searchbooks", books);
//			resp.sendRedirect("home.jsp");
			RequestDispatcher dispatcher=req.getRequestDispatcher("home.jsp");
			dispatcher.include(req, resp);
			}
			else {
				session.setAttribute("nobookfromsearch ", "No Bokk Avaialable");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
