package edu.lib.LibraryCotroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import edu.lib.Entity.Library;
import edu.lib.LibraryDao.LibraryDao;

@WebServlet("/fetchbooks")
public class FetchAllBooks extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		LibraryDao dao=new LibraryDao();
		try {
			List<Library> list=dao.fetchAllBooks();
			HttpSession session=req.getSession();
			if(list!=null) {
			
			session.setAttribute("library", list);
			resp.sendRedirect("home.jsp");
			}
			else {
				session.setAttribute("nobookfromfetch", "It Seems There Is No Book Add IT");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
