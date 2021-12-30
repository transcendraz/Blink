

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSurvey
 */
@WebServlet("/GetSurvey")
public class GetSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSurvey() {
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
		// TODO Auto-generated method stub
		String id  = request.getParameter("id");
		JDBCDriver driver = new JDBCDriver();
		boolean parse = JDBCDriver.setConn();
		String data = null;
		if(parse == false)
		{
			System.out.println("Cannot access database");
		}
		else
		{
			data = driver.getSurvey(Integer.parseInt(id));
		}
		PrintWriter writer = response.getWriter();
		writer.append(data);
		writer.flush();
	}

	
}
