package updateStudent;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class updateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter printWriter = response.getWriter();
		 
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 
		 String usernameString = request.getParameter("un");
		 int rno = Integer.parseInt(request.getParameter("rno"));
		 float per = Float.parseFloat(request.getParameter("per"));
		 
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://localhost/servlet1","root","root");
			 
			 preparedStatement = connection.prepareStatement("update student set name=?,per=? where rno=?;");
			 preparedStatement.setString(1, usernameString);
			 preparedStatement.setFloat(2, per);
			 preparedStatement.setInt(3, rno);
			 
			 int status = -1;
			 
			 try {
				status = preparedStatement.executeUpdate();
				 
			} catch (Exception e) {
				printWriter.println("Record not Found !!");
			}
			 
			if(status==1) {
				response.sendRedirect("http://localhost:8080/Student_DataBase_System_Servlet/studentManagement.html");
			}else {
				printWriter.println("Record not found !!");
			}
			 
			connection.close();
			
		} catch (Exception e) {
			System.out.println(e);
			printWriter.println("Server problem !!");
		}
		 
		printWriter.close(); 
	}

}
