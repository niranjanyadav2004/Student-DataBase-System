package showRecords;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class showRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/servlet1","root","root");
			
			 printWriter.print ("<h3><b>Student Information</b></h3>");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from student;");
			
			printWriter.print ("<table width=70% border=1>");
             
            printWriter.print ("</br></br>");
            
            printWriter.print ("</br></br>");
            
            ResultSetMetaData rsmd = resultSet.getMetaData ();
            int total = rsmd.getColumnCount ();
            
            printWriter.print ("<tr>");
            
            for (int i = 1; i <= total; i++)
            {
                printWriter.print ("<th>" + rsmd.getColumnName (i) + "</th>");
            }
            
            
            printWriter.print ("</tr>");
			
            while (resultSet.next ())
            {
                printWriter.print ("<tr><td>" + resultSet.getString (1) + "</td><td>" + resultSet.getString (2) + " </td><td>" + resultSet.getString(3) + "</td></tr>");
            }
             printWriter.print ("</table>");
           
			
			resultSet.close();
			connection.close();
			
		} catch (Exception e) {
			System.out.println(e);
			printWriter.println("Server Problem !!");
		}
		finally
        {
			printWriter.close();
        }
	}

}
