package ws;

import javax.xml.ws.Endpoint;

public class Exporter {

	public static void main(String[] args) {
		
		Endpoint.publish("http://localhost:8080/CalculatorWS/add", new AddImpl());
		Endpoint.publish("http://localhost:8080/CalculatorWS/subtract", new SubtractImpl());
		Endpoint.publish("http://localhost:8080/CalculatorWS/multiply", new MultiplyImpl());
		Endpoint.publish("http://localhost:8080/CalculatorWS/divide", new DivideImpl());
		
	}
	
}
