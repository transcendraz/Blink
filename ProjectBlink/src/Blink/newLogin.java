package Blink;


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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Connected to newLoginServlet");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		//System.out.println(userId);
		
		JDBCDriver driver = new JDBCDriver();
		boolean parse = JDBCDriver.setConn();
		
		String result = "";
		
		if(parse == false)
		{
			System.out.println("Cannot Access Database");
		}
		else {
			System.out.println("Access");
			result = driver.newLogin(username, password);
			System.out.println("result:" + result);
		}
		HttpSession s= request.getSession();
		s.setAttribute("username",username);
		PrintWriter writer = response.getWriter();
		writer.append(result);
		writer.flush();
	}

}
