package edu.lib.LibraryCotroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.lib.LibraryDao.LibraryDao;

@WebServlet("/login")
public class Login extends HttpServlet {
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String useremail=req.getParameter("email");
		String userpassword=req.getParameter("password");
		
		
		LibraryDao dao=new LibraryDao();
		try {
			ResultSet res=dao.login(useremail, userpassword);
			HttpSession session=req.getSession();
			
			if(res.isBeforeFirst()) {
				resp.sendRedirect("home.jsp");
			}
			else {
				session.setAttribute("loginError", "invalid  creadentials");
				resp.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
