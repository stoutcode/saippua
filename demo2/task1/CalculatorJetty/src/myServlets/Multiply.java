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


@WebServlet("/Multiply")
public class Multiply extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public Multiply() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int status = 400;
		String message = "Bad request. Check your params";
		try {
			String num1 = request.getParameter("num1").toString();
			String num2 = request.getParameter("num2").toString();
			
			var client = new MainClient();
			
			int result = -1;
			result = client.calculate(Integer.parseInt(num1), Integer.parseInt(num2), "multiply");
			String answer_string = client.convert2word(result);
			status = 200;
			message = answer_string;
		} catch (Exception e) {}
		Map<String, String> options = new LinkedHashMap<>();
    	options.put("message", message);
	    String json = new Gson().toJson(options);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.setStatus(status);
	    response.getWriter().write(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// not implemented
	}

}
