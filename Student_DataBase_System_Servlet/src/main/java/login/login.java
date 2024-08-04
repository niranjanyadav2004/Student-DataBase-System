package login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pWriter = response.getWriter();
		
		String unameString = request.getParameter("un");
		String pswString = request.getParameter("psw");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/servlet1","root","root");
			
			preparedStatement = connection.prepareStatement("select * from account where username=? and password=?");
			preparedStatement.setString(1,unameString);
			preparedStatement.setString(2, pswString);
			
			resultset = preparedStatement.executeQuery();
			
			if(resultset.next()) {
				response.sendRedirect("http://localhost:8080/Student_DataBase_System_Servlet/studentManagement.html");
			}
			else {
               pWriter.println("<!doctype html>\r\n"
               		+ "<html lang=\"en\">\r\n"
               		+ "\r\n"
               		+ "<head>\r\n"
               		+ "	<meta charset=\"utf-8\">\r\n"
               		+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
               		+ "	<title>Login Form</title>\r\n"
               		+ "	<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n"
               		+ "		integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\r\n"
               		+ "</head>\r\n"
               		+ "\r\n"
               		+ "<body class=\"view\" style=\"background-image: url('https://images.unsplash.com/photo-1432821596592-e2c18b78144f?auto=format&fit=crop&q=80&w=1000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8bG9naW58ZW58MHx8MHx8fDA%3D'); background-repeat: no-repeat; background-size: cover; background-position: center center; height: 800px;\">\r\n"
               		+ "\r\n"
               		+ "	<div class=\"container border border-4 p-5 mt-5\" style=\"width: 400px;\">\r\n"
               		+ "\r\n"
               		+ "		<h1 class=\"text-center pb-4 text-white\">Admin Login</h1>\r\n"
               		+ "		\r\n"
               		+ "		<form method=\"post\" action=\"Login\">\r\n"
               		+ "\r\n"
               		+ "			<div class=\"mb-3\">\r\n"
               		+ "				<label for=\"exampleInputEmail1\" class=\"form-label text-white font-weight-bold\">Username</label>\r\n"
               		+ "				<input type=\"text\" name=\"un\" class=\"form-control\" id=\"exampleInputEmail1\" aria-describedby=\"emailHelp\">\r\n"
               		+ "			</div>\r\n"
               		+ "			<div class=\"mb-3 pb-3\">\r\n"
               		+ "				<label for=\"exampleInputPassword1\" class=\"form-label text-white font-weight-bold\">Password</label>\r\n"
               		+ "				<input type=\"password\" name=\"psw\" class=\"form-control\" id=\"exampleInputPassword1\">\r\n"
               		+ "			</div>\r\n"
               		+ "\r\n"
               		+ "			<div class=\"d-grid gap-2 pb-5\">\r\n"
               		+ "				<input type=\"submit\" class=\"btn btn-primary\" value=\"Login\">\r\n"
               		+ "			</div>\r\n"
               		+ "\r\n"
               		+ "			<p class=\"text-center\">Don't have account?<a class=\"link-opacity-100\"\r\n"
               		+ "					href=\"http://localhost:8080/Student_DataBase_System_Servlet/signup.html\">Sign up</a></p>\r\n"
               		+ "\r\n"
               		+ "\r\n"
               		+ "\r\n"
               		+ "	</div>\r\n"
               		+ "\r\n"
               		+ "	<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js\"\r\n"
               		+ "		integrity=\"sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL\"\r\n"
               		+ "		crossorigin=\"anonymous\"></script>\r\n"
               		+ "\r\n"
               		+ "   <script async>\r\n"
               		+ "       alert(\"Invalid username or password !\");\r\n"
               		+ "   </script> \r\n"
               		+ "</body>\r\n"
               		+ "\r\n"
               		+ "</html>");
			}
			
			connection.close();
			
		} catch (Exception e) {
			System.out.println(e);
			pWriter.println("Problem in login !!");
		}

		pWriter.close();
	}

}
