package edu.lib.LibraryCotroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.lib.Entity.AdminLogin;
import edu.lib.LibraryDao.LibraryDao;
import lombok.SneakyThrows;

@WebServlet("/register")
public class Register extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	LibraryDao dao=new LibraryDao();
	AdminLogin aLogin=new AdminLogin();
	
	int id=Integer.parseInt(req.getParameter("id"));
	String firstName=req.getParameter("firstName");
	String lastName=req.getParameter("lastName");
	String email=req.getParameter("email");
	long phoneno=Long.parseLong(req.getParameter("phoneno"));
	String password=req.getParameter("password");
	String confirmPassword=req.getParameter("confirmPassword");
	
	aLogin.setId(id);
	aLogin.setFirstName(firstName);
	aLogin.setLastName(lastName);
	aLogin.setEmail(email);
	aLogin.setPhoneNo(phoneno);
	aLogin.setPassword(password);
	aLogin.setConfirmPassword(confirmPassword);
	
	
		try {
			int res =dao.register(aLogin);
			if(res!=0) {
				
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("login.jsp");
				requestDispatcher.include(req, resp);
			}
			else {
				PrintWriter writer=resp.getWriter();
				writer.println("opps something wents wrong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
