package myServlets;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class RunServlets {

	public static void main(String[] args) throws Exception {

		ServletHandler handler = new ServletHandler();
		handler.addServletWithMapping(Add.class, "/add");
		handler.addServletWithMapping(Subtract.class, "/subtract");
		handler.addServletWithMapping(Multiply.class, "/multiply");
		handler.addServletWithMapping(Divide.class, "/divide");

        Server server = new Server(1234);
		server.setHandler(handler);
		server.start();
		server.dumpStdErr();
		server.join();
	}

}
