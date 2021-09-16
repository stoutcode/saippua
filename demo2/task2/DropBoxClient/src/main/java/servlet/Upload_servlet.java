package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Upload_servlet")
public class Upload_servlet extends HttpServlet {
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
		String path = request.getParameter("path").toString();
		
		PrintWriter out = response.getWriter();
		
		try {
			uploadFile(token, path, out);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
	
	public void uploadFile(String token, String path, PrintWriter out) throws URISyntaxException, IOException {
		String access_token = ""+token; 
		String sourcePath = ""+path; //required file path on local file system
		Path pathFile = Paths.get(sourcePath);
		byte[] data = Files.readAllBytes(pathFile);
		
		String content = "{\"path\": \"images/image_initial_uploaded.png\",\"mode\":\"add\",\"autorename\": true,\"mute\": false,\"strict_conflict\": false}";
		URL url = new URL("https://content.dropboxapi.com/2/files/upload");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		String queryResult = "";
		
		try {
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer "+access_token);
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestProperty("Dropbox-API-Arg", "" + content);
			connection.setRequestProperty("Content-Length", String.valueOf(data.length));
			
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(data);
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
