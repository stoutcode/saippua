package servlet;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

@WebServlet("/")
public class RunServlets {
	
	public static void main(String[] args) throws Exception{
		ServletHandler handler = new ServletHandler();
		//add all servlet to use to the handler, the second argument is the path (e.g. http://localhost:8080/searchPublication)
		handler.addServletWithMapping(DummyClass.class, "/invokeDummy");
		//Create a new Server, add the handler to it and start
		Server server = new Server(8080);
		System.out.println("jetty server started");
		server.setHandler(handler);
		server.start();
		//this dumps a lot of debug output to the console.
		server.dumpStdErr();
		server.join();
	}

}
