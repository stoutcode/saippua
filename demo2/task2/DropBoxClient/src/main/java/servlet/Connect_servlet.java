package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.URI;
import java.net.URLEncoder;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Connect_servlet")
public class Connect_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		// set content type so firefox doesn't complain about 'xml parsing error'
		response.setContentType("text/plain"); 
		
		PrintWriter out = response.getWriter();
		
		try {
			sendRequest(out);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();

		}
				
	}
	
	public void sendRequest(PrintWriter out) throws URISyntaxException, IOException {
		// basically builds corresponding GET request that will be returnd to the front-end... 
		String appKey = "245s603yn0y2rol"; //get from AppConsole when create the DropBox App
		String redirectURI="http://localhost:8080/DropBoxClient/"; //any url to where you want to redirect the user
		
		URI uri = new URI("https://www.dropbox.com/oauth2/authorize");
		StringBuilder requestUri=new StringBuilder(uri.toString());
		
		requestUri.append("?client_id=");
		requestUri.append(URLEncoder.encode(appKey,"UTF-8"));
		requestUri.append("&response_type=code");
		requestUri.append("&redirect_uri="+redirectURI.toString());
		
		String queryResult= requestUri.toString();
		
		out.write(queryResult);
		
		out.flush();
		out.close();
		
	}

}
