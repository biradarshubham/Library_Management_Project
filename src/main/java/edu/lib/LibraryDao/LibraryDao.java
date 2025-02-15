package edu.lib.LibraryDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.lib.Entity.AdminLogin;
import edu.lib.Entity.Library;


public class LibraryDao {
		private Connection connection;
		private PreparedStatement pstmt;
		
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management?user=root&password=sql123");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//first method for registration

	public int register(AdminLogin login) throws SQLException {
		String query="insert into adminlogin values(?,?,?,?,?,?,?)";
	    PreparedStatement pstmt =connection.prepareStatement(query);
	
	    pstmt.setInt(1, login.getId());
		pstmt.setString(2, login.getFirstName());
		pstmt.setString(3, login.getLastName());
		pstmt.setString(4, login.getEmail());
		pstmt.setLong(5, login.getPhoneNo());
		pstmt.setString(6, login.getPassword());
		pstmt.setString(7, login.getConfirmPassword());
		
		int res=pstmt.executeUpdate();
		
		return res;
		
	}
	
	//second method for login
	
	public ResultSet login(String email,String password) throws SQLException {
		String query="Select * from adminlogin where email=? and password=?";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		
		ResultSet res=pstmt.executeQuery();
		return res;
	}

	//third method for add book
	
	public int addbook(Library library) throws SQLException, IOException {
		String query="insert into library values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setInt(1,library.getBookId());
		pstmt.setString(2, library.getBookname());
		pstmt.setString(3, library.getAuthor());
		pstmt.setDouble(4, library.getPrice());
		pstmt.setInt(5, library.getPages());
		pstmt.setDate(6, library.getPublicationdate());
		pstmt.setString(7, library.getImage());
		
		int res=pstmt.executeUpdate();
		
		return res;
		
	}
	
	//fourth method for fetch all books

	public List<Library> fetchAllBooks() throws SQLException {
		
		String query="SELECT * FROM library ";
		
		PreparedStatement pstmt=connection.prepareStatement(query);
		ResultSet res=pstmt.executeQuery();
		
		if(res.isBeforeFirst()) {
			List<Library> list=new ArrayList<Library>();
			while(res.next()) {
			Library library=new Library();
			library.setBookId(res.getInt("bookid"));
			library.setBookname(res.getString("bookname"));
			library.setAuthor(res.getString("author"));
			library.setPrice(res.getDouble("price"));
			library.setPages(res.getInt("pages"));
			library.setPublicationdate(res.getDate("publicationdate"));
			library.setImage(res.getString("image"));
			list.add(library);
			}
			return list;
		}
		return null;
	}

	public int remove(int bookid) throws SQLException {
		String query="delete from library where bookid=?";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setInt(1, bookid);
		int res=pstmt.executeUpdate();
		return res;
	}

	public Library findById(int bookid) throws SQLException {
		String query="select * from library where bookid=?";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setInt(1, bookid);
		ResultSet res=pstmt.executeQuery();
		
			while(res.next()) {
			Library library=new Library();
			library.setBookId(res.getInt("bookid"));
			library.setBookname(res.getString("bookname"));
			library.setAuthor(res.getString("author"));
			library.setPrice(res.getDouble("price"));
			library.setPages(res.getInt("pages"));
			library.setPublicationdate(res.getDate("publicationdate"));
			library.setImage(res.getString("image"));
			return library;
			}
		return null;
	}
	
	public int updatebook(Library library) throws SQLException, IOException {
		String query="UPDATE library SET bookname=?, author=?, price=?, pages=?, publicationdate=? WHERE bookid=?";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setString(1, library.getBookname());
		pstmt.setString(2, library.getAuthor());
		pstmt.setDouble(3, library.getPrice());
		pstmt.setInt(4, library.getPages());
		pstmt.setDate(5, library.getPublicationdate());
		pstmt.setInt(6,library.getBookId());
		
		
		int res=pstmt.executeUpdate();
		
		return res;
		
	}

	public ResultSet fetchByEmail(String email) throws SQLException {
		String query="select * from adminlogin where email=?";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setString(1, email);
		ResultSet rs=pstmt.executeQuery();
		return rs;
	}

	public int updatepass(String password, String confirmpassword, String email) throws SQLException {
		
		String query="update adminlogin set password=?,confirmpassword=? where email=?";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setString(1, password);
		pstmt.setString(2, confirmpassword);
		pstmt.setString(3, email);
		
		int res=pstmt.executeUpdate();
		
		return res;
	}

	public List<Library> searchbooks(String data) throws SQLException {
		String query="SELECT * FROM library WHERE bookname = ?";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setString(1, data);
		ResultSet res=pstmt.executeQuery();
		if(res.isBeforeFirst()) {
			List<Library> list=new ArrayList<Library>();
			while(res.next()) {
			Library library=new Library();
			library.setBookId(res.getInt("bookid"));
			library.setBookname(res.getString("bookname"));
			library.setAuthor(res.getString("author"));
			library.setPrice(res.getDouble("price"));
			library.setPages(res.getInt("pages"));
			library.setPublicationdate(res.getDate("publicationdate"));
			library.setImage(res.getString("image"));
			list.add(library);
			}
			return list;
		}
		return null;
	}
	
	
}
	
