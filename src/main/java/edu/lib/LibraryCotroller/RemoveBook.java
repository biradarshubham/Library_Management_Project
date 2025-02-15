package edu.lib.LibraryCotroller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import edu.lib.LibraryDao.LibraryDao;

@WebServlet("/remove")
public class RemoveBook extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  int id=Integer.parseInt(req.getParameter("bookid"));
		  String imageName=req.getParameter("image");
		  
		  try {
			LibraryDao dao=new LibraryDao();
			int res=dao.remove(id);
			
			// Delete the image file if it exists
			String imagePath="C:/Users/shubh/OneDrive/Documents/advancejava/javaServerPages/Library_Management/src/main/webapp/images/"+imageName;
			File imageFile = new File(imagePath);

			if (imageFile.exists() && imageFile.isFile()) {
			    System.out.println("File exists: " + imagePath);
			    if (imageFile.delete()) {
			        System.out.println("Image deleted successfully: " + imagePath);
			    } else {
			        System.out.println("Failed to delete image. Check permissions.");
			    }
			} else {
			    System.out.println("File does not exist or is not a file: " + imagePath);
			}
			HttpSession session=req.getSession();
			if(res!=0) {
				session.setAttribute("removed", "book removed successfully....");
				resp.sendRedirect("fetchbooks");
			}
			else {
				session.setAttribute("removebookno", "opps something wents wrong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
