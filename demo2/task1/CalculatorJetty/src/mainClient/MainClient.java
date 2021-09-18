package mainClient;

import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.soap.converter.NumberConversion;
import com.soap.converter.NumberConversionSoapType;
import com.soap.selfmadecalculator.CalculatorImplementService;

public class MainClient {
	
	public void handleCalculationResponse(HttpServletRequest request, HttpServletResponse response, String operator) throws ServletException, IOException {
		int status = 400;
		String message = "Bad request. Check your params";
		try {
			String num1 = request.getParameter("num1").toString();
			String num2 = request.getParameter("num2").toString();
			
			int result = -1;
			result = this.calculate(Integer.parseInt(num1), Integer.parseInt(num2), operator);
			String answer_string = this.convert2word(result);
			status = 200;
			message = answer_string;
		} catch (Exception e) {}
		Map<String, String> options = new LinkedHashMap<>();
    	options.put("message", message);
	    String json = new Gson().toJson(options);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.setStatus(status);
	    response.getWriter().write(json.toString());
	}
	
	public int calculate(int first, int second, String operator) {
		var calculator = new CalculatorImplementService();
		var calculator_soap = calculator.getCalculatorImplementPort();
		int answer = -1;
		switch (operator) {
			case "add":
				answer = calculator_soap.add(first, second);
				break;
			case "subtract":
				answer = calculator_soap.subtract(first, second);
				break;
			case "multiply":
				answer = calculator_soap.multiply(first, second);
				break;
			case "divide":
				answer = calculator_soap.divide(first, second);
				break;
			default:
				break;
		}
		if (answer == -1) {
			throw new IllegalArgumentException();
		}
		return answer;
	}
	
	public String convert2word(int input) {
		BigInteger input_N = BigInteger.valueOf(input);
		NumberConversion NC_service = new NumberConversion();
        NumberConversionSoapType NC_serviceSOAP = NC_service.getNumberConversionSoap();
        String result = NC_serviceSOAP.numberToWords(input_N);
        return result;
	}
}
