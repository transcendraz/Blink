package Blink;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn=null;
		Statement st= null;
		ResultSet rs=null;
		ResultSet rs2=null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		HttpSession s= request.getSession();
		PrintWriter out= response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BlinkData?user=root&password=Yudeveloper1506!&useSSL=false");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			ps=conn.prepareStatement("SELECT * FROM Users WHERE username=?");
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(!rs.next()) {
				out.println("This username doesn't exist. Click the botton below to create a new user.");
			}else {
				ps2=conn.prepareStatement("SELECT * FROM Users WHERE username=? AND password=?");
				ps2.setString(1, username);
				ps2.setString(2, password);
				rs2=ps2.executeQuery();
				if(!rs2.next()) { 
					out.println("Password doesn't match the username.");
				}else {
					s.setAttribute("username",username);
					out.println("");
				}
			}
		}catch(SQLException sqle) {
			out.println("sqle "+ sqle.getMessage());
		}catch(ClassNotFoundException cnfe) {
			out.println("cnfe "+ cnfe.getMessage());
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(st != null) {
					st.close();
				}
				if(conn != null) {
					conn.close();
				}
				if(ps!=null) {
					ps.close();
				}
				if(ps2!=null) {
					ps2.close();
				}

			}catch(SQLException sqle) {
				System.out.println("sqle closing streams: "+sqle.getMessage());
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
