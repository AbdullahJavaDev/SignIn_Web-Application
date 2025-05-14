package org.svpcet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SvpcetLogin extends HttpServlet{
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet res;
	
	//initialisation
	@Override
	public void init() throws ServletException {
		String url="jdbc:mysql://localhost:3306/Svpcet";
		String userName="root";
		String passWord="SHAIKSQL123";
		//database-connectivity
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,userName,passWord);
		} 
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//service method
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//logic for get the request from the client and fetch the details from the database
		
		String passWord=req.getParameter("Password");
		String email=req.getParameter("Email");
		System.out.println(passWord);
		System.out.println(email);
		
		//write the query
		String query="select*from studentsLogin where Email=? and passWord=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,email);
			pstmt.setString(2,passWord);
			res=pstmt.executeQuery();
			if(res.next()) {
				resp.setContentType("text/html");
				PrintWriter writer=resp.getWriter();
				writer.println("<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "  <meta charset=\"UTF-8\">\r\n"
						+ "  <title>Welcome to SVPCET</title>\r\n"
						+ "  <style>\r\n"
						+ "    body {\r\n"
						+ "      background: linear-gradient(135deg, #4e54c8, #8f94fb);\r\n"
						+ "      color: white;\r\n"
						+ "      font-family: 'Segoe UI', sans-serif;\r\n"
						+ "      display: flex;\r\n"
						+ "      justify-content: center;\r\n"
						+ "      align-items: center;\r\n"
						+ "      height: 100vh;\r\n"
						+ "      margin: 0;\r\n"
						+ "      overflow: hidden;\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    .welcome-box {\r\n"
						+ "      background: rgba(255, 255, 255, 0.1);\r\n"
						+ "      padding: 40px 60px;\r\n"
						+ "      border-radius: 20px;\r\n"
						+ "      box-shadow: 0 10px 30px rgba(0,0,0,0.2);\r\n"
						+ "      text-align: center;\r\n"
						+ "      animation: fadeIn 2s ease-in-out;\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    .welcome-box h1 {\r\n"
						+ "      font-size: 3em;\r\n"
						+ "      margin: 0;\r\n"
						+ "      animation: popIn 1s ease-out;\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    @keyframes fadeIn {\r\n"
						+ "      from { opacity: 0; transform: scale(0.9); }\r\n"
						+ "      to { opacity: 1; transform: scale(1); }\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    @keyframes popIn {\r\n"
						+ "      0% { transform: scale(0.5); opacity: 0; }\r\n"
						+ "      100% { transform: scale(1); opacity: 1; }\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    .sparkle {\r\n"
						+ "      font-size: 1.5em;\r\n"
						+ "      animation: blink 1s infinite alternate;\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    @keyframes blink {\r\n"
						+ "      from { opacity: 0.5; }\r\n"
						+ "      to { opacity: 1; }\r\n"
						+ "    }\r\n"
						+ "  </style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "  <div class=\"welcome-box\">\r\n"
						+ "    <h1>"+(res.getString(1)).toUpperCase()+" WELCOME TO SVPCET CAMPUS</h1>\r\n"
						+ "    <p class=\"sparkle\">We are excited to have you here!</p>\r\n"
						+ "  </div>\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n"
						+ "");
			}
			else {
				resp.setContentType("text/html");
				PrintWriter writer=resp.getWriter();
				writer.println("<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "  <meta charset=\"UTF-8\">\r\n"
						+ "  <title>Invalid User</title>\r\n"
						+ "  <style>\r\n"
						+ "    body {\r\n"
						+ "      background: linear-gradient(135deg, #ff6a6a, #ff9999);\r\n"
						+ "      font-family: 'Segoe UI', sans-serif;\r\n"
						+ "      display: flex;\r\n"
						+ "      justify-content: center;\r\n"
						+ "      align-items: center;\r\n"
						+ "      height: 100vh;\r\n"
						+ "      margin: 0;\r\n"
						+ "      color: white;\r\n"
						+ "      overflow: hidden;\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    .error-box {\r\n"
						+ "      background: rgba(255, 255, 255, 0.15);\r\n"
						+ "      padding: 40px 60px;\r\n"
						+ "      border-radius: 20px;\r\n"
						+ "      box-shadow: 0 8px 25px rgba(0,0,0,0.2);\r\n"
						+ "      text-align: center;\r\n"
						+ "      animation: slideDown 1.5s ease-out;\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    .error-box h1 {\r\n"
						+ "      font-size: 2.8em;\r\n"
						+ "      margin-bottom: 10px;\r\n"
						+ "      animation: shake 0.6s infinite alternate;\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    .error-box p {\r\n"
						+ "      font-size: 1.2em;\r\n"
						+ "      opacity: 0.9;\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    @keyframes slideDown {\r\n"
						+ "      from { transform: translateY(-50px); opacity: 0; }\r\n"
						+ "      to { transform: translateY(0); opacity: 1; }\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    @keyframes shake {\r\n"
						+ "      0% { transform: translateX(-5px); }\r\n"
						+ "      100% { transform: translateX(5px); }\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    .retry-button {\r\n"
						+ "      margin-top: 20px;\r\n"
						+ "      padding: 10px 25px;\r\n"
						+ "      font-size: 1em;\r\n"
						+ "      color: #ff6a6a;\r\n"
						+ "      background: white;\r\n"
						+ "      border: none;\r\n"
						+ "      border-radius: 8px;\r\n"
						+ "      cursor: pointer;\r\n"
						+ "      transition: background 0.3s, transform 0.2s;\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ "    .retry-button:hover {\r\n"
						+ "      background: #ffe5e5;\r\n"
						+ "      transform: scale(1.05);\r\n"
						+ "    }\r\n"
						+ "  </style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "  <div class=\"error-box\">\r\n"
						+ "    <h1>🚫 Sorry!</h1>\r\n"
						+ "    <p>Invalid User Details</p>\r\n"
						+ "    <button class=\"retry-button\" onclick=\"history.back()\">Try Again</button>\r\n"
						+ "  </div>\r\n"
						+ "</body>\r\n"
						+ "</html>");
			}
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//destroy method
	@Override
	public void destroy() {
		//close the connection to avoid data leakage problem
		System.out.println("destroy method get called");
		
	}
	
}
