package mainClient;

import java.math.BigInteger;

import com.soap.converter.NumberConversion;
import com.soap.converter.NumberConversionSoapType;
import com.soap.selfmadecalculator.CalculatorImplementService;

public class MainClient {
	
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
