package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import java.net.URL;

import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Storage_servlet ")
public class Storage_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		// set content type so firefox doesn't complain about 'xml parsing error'
		response.setContentType("text/plain");
		
		String token = request.getParameter("token").toString();
		
		PrintWriter out = response.getWriter();
		
		try {
			getAccountStorage(token, out);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	
		
	}
	
	public void getAccountStorage(String token, PrintWriter out) throws URISyntaxException, IOException {
		String access_token = ""+token; 
		

		URL url = new URL("https://api.dropboxapi.com/2/users/get_space_usage/");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		String queryResult = "";
		
		try {
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer "+access_token);
			
			OutputStream outputStream = connection.getOutputStream();

			outputStream.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			queryResult = response.toString();
				
		} finally {
			connection.disconnect();
		}
		
		out.write(queryResult);
		
		out.flush();
		out.close();
		
	}
	

}
