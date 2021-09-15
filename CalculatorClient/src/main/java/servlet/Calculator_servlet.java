package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calculator_client.CalculatorClient;


@WebServlet("/Calculator_servlet")
public class Calculator_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		// set content type so firefox doesn't complain about 'xml parsing error'
		response.setContentType("text/plain"); 
		
		// create client to work through
		CalculatorClient client = new CalculatorClient();
		
		// type=X&num1=X&num2=X&operator=X
		String type = request.getParameter("type").toString();
		String num1 = request.getParameter("num1").toString();
		String num2 = request.getParameter("num2").toString();
		String operator = request.getParameter("operator").toString();
		
		// to return response through
		PrintWriter out = response.getWriter();
		
		if (type.equals("calculation")) {
			if ( num1.equals("") || num2.equals("") )  {
				out.write("error: please provide two numbers to calculate with..");
			} else {
				
				try {
					// number can be too small/big or not even a number
					int number1 = Integer.parseInt(num1);
					int number2 = Integer.parseInt(num2);
					
					int answer = 0;
					
					String answer_string = "error: select one of the operations from selection box..";
					
					switch(operator) {
						case "add":
							answer = client.add(number1, number2);
							answer_string = String.valueOf(answer);
							break;
						case "subtract":
							answer = client.subtract(number1, number2);
							answer_string = String.valueOf(answer);
							break;
						case "multiply":
							answer = client.multiply(number1, number2);
							answer_string = String.valueOf(answer);
							break;
						case "divide":
							if (number2 < 1) {
								answer_string = "error: thats not how you divide..";
								break;
							}
							answer = client.divide(number1, number2);
							answer_string = String.valueOf(answer);
							break;
						default:
							// operation is still not one of the provided, return default error message
							break;
					}
					
					out.write(answer_string);
					
				} catch (NumberFormatException e) {
					out.write("error: not a valid number..");
				}
				
			}
			
		} else if (type.equals("conversion")) {
			try {
				Integer.parseInt(num1);
				String answer = client.convert2word(num1);
				out.write(answer);
			} catch (NumberFormatException e) {
				out.write("error: earlier error happened..");
			} 
			
		} else {
			// operation type was not calculation or conversion
			out.write("error: something went wrong :(");
		}
		
		out.flush();
		out.close();
		
		
	}

}
