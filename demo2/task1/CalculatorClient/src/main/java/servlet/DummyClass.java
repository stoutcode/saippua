package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.XML;

import com.google.gson.Gson;

import calculator_client.CalculatorClient;


@WebServlet("/DummyClass")
public class DummyClass extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public DummyClass() {
		super();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("I'm a dummy function!");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String dummy = request.getParameter("dummy").toString();

		CalculatorClient calculator = new CalculatorClient();
		String result_string = String.valueOf(calculator.add(1, 0));
		
		if(!result_string.equals("null")){

			org.json.JSONObject json = new org.json.JSONObject();
			try {
				json = XML.toJSONObject(result_string);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
/*			Map<String, String> options = new LinkedHashMap<>();
	    	options.put("id", "01");
		    options.put("title", "Publication1");
		    options.put("author", "me");
		    String json = new Gson().toJson(options);
*/			
/*		    
		    JSONObject json_ = new JSONObject();
		    json_.put("test1", "value1");
*/		    
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json.toString());
		    
		    
		    
		}else{
			
			Map<String, String> options = new LinkedHashMap<>();
	    	options.put("message", "Is not found");
		    String json = new Gson().toJson(options);
			
	    	 	
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.setStatus(404);
		    response.getWriter().write(json.toString());
			
		}
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