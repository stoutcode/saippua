package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Accountinfo_servlet")
public class Accountinfo_servlet extends HttpServlet {
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
		String account_id = request.getParameter("accountid").toString();
		
		PrintWriter out = response.getWriter();
		
		try {
			getAccountInfo(token, account_id, out);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	public void getAccountInfo(String tokenStr, String accountIDStr, PrintWriter out) throws URISyntaxException, IOException {
		String access_token = ""+tokenStr;
		String content = "{\"account_id\":\"" + accountIDStr + "\"}";
		
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		
		URL url = new URL("https://api.dropboxapi.com/2/users/get_account");
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		String queryResult = "";
		
		try {
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + access_token);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Length", "" + content.length());
			
			System.out.println("JA NYT LÄHTEE PYYNTÖ");
			
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			outputStreamWriter.write(content);
			outputStreamWriter.flush();
			
			System.out.println("SINNE MENI");
			
			// Tässä tulee bad request virhe
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			System.out.println("SIINÄ TULI BUFFEREDREADER");
			
			String inputLine;
			
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				
				System.out.println("SITTEN LUETAAN PALAUTUSTA");
				
				response.append(inputLine);
			}
			
			System.out.println("PALAUTUS LUETTU");
			
			in.close();
			
			queryResult= response.toString(); 
			
		} finally {
			connection.disconnect();
	
		}
		
		out.write(queryResult);
		
		out.flush();
		out.close();
	}

}
