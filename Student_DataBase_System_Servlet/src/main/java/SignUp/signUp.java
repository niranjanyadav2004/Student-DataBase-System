package SignUp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class signUp extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		String usernameString = request.getParameter("un");
		String fullnameString = request.getParameter("name");
		String emailString = request.getParameter("email");
		String passwordString = request.getParameter("psw");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/servlet1","root","root");
			
			preparedStatement = connection.prepareStatement("insert into account values (?,?,?,?)");
			preparedStatement.setString(1,fullnameString);
			preparedStatement.setString(2, usernameString);
			preparedStatement.setString(3, emailString);
			preparedStatement.setString(4, passwordString);
			
			int status = -1;

			try {
				
				status = preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				printWriter.println("You already have an account !!");
			}
			
			if(status==1) {
				printWriter.println("Account is created successfully !!");
			}
			
			connection.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
			printWriter.println("Problem in registration !!");
		}

		printWriter.close();
	}

}
