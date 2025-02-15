package edu.lib.LibraryCotroller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.lib.LibraryDao.LibraryDao;

@WebServlet("/updatepass")
public class updatePass extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password=req.getParameter("password");
		String confirmpassword=req.getParameter("confirmpassword");
		HttpSession session=req.getSession();
		String email=(String) session.getAttribute("email");
		
		LibraryDao dao=new LibraryDao();
		try {
			int res=dao.updatepass(password,confirmpassword,email);
			if(res!=0) {
				resp.sendRedirect("login.jsp");
				session.setAttribute("pass", "password changed successfully...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
