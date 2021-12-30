package Blink;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class displayDataServlet
 */
@WebServlet("/displayDataServlet")
public class displayDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String result = "";
		
		System.out.println(userId);
		
		JDBCDriver driver = new JDBCDriver();
		boolean parse = JDBCDriver.setConn();
		if(parse == false)
		{
			System.out.println("Cannot Access Database");
		}
		else {
			System.out.println("Access");
			result = driver.grabProfileData(userId);
			System.out.println("result:" + result);
			//HttpSession userChosen = request.getSession(true);
			//userChosen.setAttribute("fullName", fullName);
		}
		PrintWriter writer = response.getWriter();
		writer.append(result);
		writer.flush();
		
	}

}
