package myServlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import mainClient.MainClient;


@WebServlet("/DummyClass")
public class Divide extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public Divide() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dummy = request.getParameter("dummy").toString();
		
		var client = new MainClient();
		dummy = client.convert2word(dummy);
		
		Map<String, String> options = new LinkedHashMap<>();
    	options.put("message", dummy);
	    String json = new Gson().toJson(options);
		
    	 	
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.setStatus(666);
	    response.getWriter().write(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
/*		
		PrintWriter out = response.getWriter();
		out.write("Responce post:searchPublication");  
		out.flush();
	    out.close();
*/		
		
	}

}