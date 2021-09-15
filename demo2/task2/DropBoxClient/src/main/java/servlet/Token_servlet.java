package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Token_servlet")
public class Token_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		// set content type so firefox doesn't complain about 'xml parsing error'
		response.setContentType("text/plain");
		
		String code = request.getParameter("code").toString();
		
		PrintWriter out = response.getWriter();
		
		try {
			accessToken(code, out);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	public void accessToken(String codeStr, PrintWriter out) throws URISyntaxException, IOException {
		String code = ""+codeStr; //code get from previous step 
		String appKey = "iqhiwgqtw48a98l"; //get from AppConsole when create the DropBox App
		String appSecret = "c5wikaabqnfvdt2"; //get from AppConsole when create the DropBox App 
		String redirectURI="http://localhost:8080/DropBoxClient/"; //any url to where you want to redirect the user
		StringBuilder tokenUri=new StringBuilder("code=");
		tokenUri.append(URLEncoder.encode(code,"UTF-8"));
		tokenUri.append("&grant_type=");
		tokenUri.append(URLEncoder.encode("authorization_code","UTF-8"));
		tokenUri.append("&client_id=");
		tokenUri.append(URLEncoder.encode(appKey,"UTF-8"));
		tokenUri.append("&client_secret=");
		tokenUri.append(URLEncoder.encode(appSecret,"UTF-8"));
		tokenUri.append("&redirect_uri="+redirectURI);
		URL url=new URL("https://api.dropbox.com/oauth2/token");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		String queryResult = "";
		
		try {
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "" + tokenUri.toString().length());
			
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			outputStreamWriter.write(tokenUri.toString());
			outputStreamWriter.flush();
			
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
