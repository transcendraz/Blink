

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sendSurveyPageData")
public class sendSurveyPageData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userSignedIn = session.getAttribute("username").toString();
		String name = request.getParameter("hiddenInput1");
		String age = request.getParameter("hiddenInput2");
		String gender = request.getParameter("hiddenInput3");
		String genderPreference = request.getParameter("hiddenInput4");
		String indoorOutdoor = request.getParameter("hiddenInput5");
		String earlyBirdNightOwl = request.getParameter("hiddenInput6");
		String catDog = request.getParameter("hiddenInput7");
		String music = request.getParameter("hiddenInput8");
		String sports = request.getParameter("hiddenInput9");
		String workout = request.getParameter("hiddenInput10");
		String idealDate = request.getParameter("hiddenInput11");
		
		String success = "failed to send survey page data";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Data?user=root&password=root&useSSL=false");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * from Survey where email='" + userSignedIn + "'");
			while (rs.next()) 
			{
				ps = conn.prepareStatement("UPDATE Users SET name = ? WHERE email = ?");
				ps.setString(1, name);
				ps.setString(2, userSignedIn);
				ps.executeUpdate();
				
				ps = conn.prepareStatement("UPDATE Users SET age = ? WHERE email = ?");
				ps.setString(1, age);
				ps.setString(2, userSignedIn);
				ps.executeUpdate();
				
				ps = conn.prepareStatement("UPDATE Users SET gender = ? WHERE email = ?");
				ps.setString(1, gender);
				ps.setString(2, userSignedIn);
				ps.executeUpdate();
				
				ps = conn.prepareStatement("UPDATE Users SET genderPreference = ? WHERE email = ?");
				ps.setString(1, genderPreference);
				ps.setString(2, userSignedIn);
				ps.executeUpdate();
				
				ps = conn.prepareStatement("UPDATE Users SET indoorOutdoor = ? WHERE email = ?");
				ps.setString(1, indoorOutdoor);
				ps.setString(2, userSignedIn);
				ps.executeUpdate();
				
				ps = conn.prepareStatement("UPDATE Users SET earlyBirdNightOwl = ? WHERE email = ?");
				ps.setString(1, earlyBirdNightOwl);
				ps.setString(2, userSignedIn);
				ps.executeUpdate();
				
				ps = conn.prepareStatement("UPDATE Users SET catDog = ? WHERE email = ?");
				ps.setString(1, catDog);
				ps.setString(2, userSignedIn);
				ps.executeUpdate();
				
				ps = conn.prepareStatement("UPDATE Users SET music = ? WHERE email = ?");
				ps.setString(1, music);
				ps.setString(2, userSignedIn);
				ps.executeUpdate();
				
				ps = conn.prepareStatement("UPDATE Users SET sports = ? WHERE email = ?");
				ps.setString(1, sports);
				ps.setString(2, userSignedIn);
				ps.executeUpdate();
				
				ps = conn.prepareStatement("UPDATE Users SET workout = ? WHERE email = ?");
				ps.setString(1, workout);
				ps.setString(2, userSignedIn);
				ps.executeUpdate();
				
				ps = conn.prepareStatement("UPDATE Users SET idealDate = ? WHERE email = ?");
				ps.setString(1, idealDate);
				ps.setString(2, userSignedIn);
				ps.executeUpdate();
				
				success = "successfully sent survey page data";
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
		writer.append(success);
		writer.flush();
	}
}
