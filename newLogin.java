

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class newLogin
 */
@WebServlet("/newLogin")
public class newLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newLogin() {
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
		Connection conn=null;
		Statement st= null;
		ResultSet rs=null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		HttpSession s= request.getSession();
		PrintWriter out= response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/BlinkData?user=root&password=root&useSSL=false&allowPublicKeyRetrieval=true");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			if(username.length()==0) {
				out.println("<br> Please enter a username");
			}else if(password.length()==0) {
				out.println("<br> Please enter a password");
			}else {
				ps=conn.prepareStatement("SELECT * FROM Users WHERE username=?");
				ps.setString(1, username);
				rs=ps.executeQuery();
				if(rs.next()) {
					out.println("<br> This username already exist. Please pick another one.");
				}else {
					ps2=conn.prepareStatement("INSERT INTO `BlinkData`.`Users` (`username`, `password`) VALUES (?, ?);");
					ps2.setString(1, username);
					ps2.setString(2, password);
					ps2.executeUpdate();
					out.println("");
					s.removeAttribute("username");
					s.setAttribute("username",username);
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
