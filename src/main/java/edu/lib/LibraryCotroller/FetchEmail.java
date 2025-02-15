package edu.lib.LibraryCotroller;

import java.io.IOException;
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

@WebServlet("/fetchemail")
public class FetchEmail extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		HttpSession session=req.getSession();
		LibraryDao dao=new LibraryDao();
		try {
			ResultSet res=dao.fetchByEmail(email);
			if(res.isBeforeFirst()) {
				while(res.next()) {
				session.setAttribute("email", res.getString("email"));
				resp.sendRedirect("changepass.html");
			}
			}
			else {
				session.setAttribute("invalidmail", "your enter email is invalid...please enter valid email");
				resp.sendRedirect("forget.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
