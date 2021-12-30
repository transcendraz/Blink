package Blink;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/getSurveyPageDataBLINK")
public class getSurveyPageDataBLINK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userSignedIn = session.getAttribute("username").toString();
		String data = "";
		
		//test
		System.out.println("!!!");
		System.out.println(userSignedIn);
		System.out.println("!!!");
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Statement st1 = null;
		ResultSet rs1 = null;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Data?user=root&password=root&useSSL=false");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * from Users where email='" + userSignedIn + "'");
			while (rs.next()) 
			{
				System.out.println("match");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String fullName = fname + " " + lname;
				
				data += fullName + ",";
			}
			
			st1 = conn.createStatement();
			rs1 = st.executeQuery("SELECT * from Survey where email='" + userSignedIn + "'");
			while (rs1.next()) 
			{
				System.out.println("match1");
				String age = rs.getString("age");
				String gender = rs.getString("gender");
				String genderPreference = rs.getString("genderPreference");
				String indoorOutdoor = rs.getString("indoorOutdoor");
				String earlyBirdNightOwl = rs.getString("earlyBirdNightOwl");
				String catDog = rs.getString("catDog");
				String music = rs.getString("music");
				String sports = rs.getString("sports");
				String workout = rs.getString("workout");
				String idealDate = rs.getString("idealDate");
				
				data += age + "," + gender + "," + genderPreference + "," + indoorOutdoor + "," + earlyBirdNightOwl + "," + catDog + "," + music + "," + sports + "," + workout + "," + idealDate;
				
			}
			
		}
		catch (SQLException sqle) 
		{
			System.out.println (sqle.getMessage());
		} 
		catch (ClassNotFoundException cnfe) 
		{
			System.out.println (cnfe.getMessage());
		} 
		finally 
		{
			try 
			{
				if (rs != null) 
				{ 
					rs.close(); 
				}
				if (st != null) 
				{ 
					st.close(); 
				}
				if (conn != null) 
				{ 
					conn.close(); 
				}
			} 
			catch (SQLException sqle) 
			{
				System.out.println(sqle.getMessage());
			}
		}
		
		PrintWriter writer = response.getWriter();
		writer.append(data);
		writer.flush();
	}
}
