package calculator_client;

import java.math.BigInteger;

import com.soap.ws.client.Calculator;
import com.soap.ws.client.CalculatorSoap;
import com.soap.ws.client2.NumberConversion;
import com.soap.ws.client2.NumberConversionSoapType;

public class CalculatorClient {
	
	public int add(int num1, int num2) {
		
		Calculator calc_service = new Calculator();
		CalculatorSoap calc_service_soap = calc_service.getCalculatorSoap();
		
		int answer = calc_service_soap.add(num1, num2);
		return answer;
	}

	public int subtract(int num1, int num2) {
		
		Calculator calc_service = new Calculator();
		CalculatorSoap calc_service_soap = calc_service.getCalculatorSoap();
		
		int answer = calc_service_soap.subtract(num1, num2);
		return answer;
	}
	
	public int multiply(int num1, int num2) {
		
		Calculator calc_service = new Calculator();
		CalculatorSoap calc_service_soap = calc_service.getCalculatorSoap();
		
		int answer = calc_service_soap.multiply(num1, num2);
		return answer;
	}
	
	public int divide(int num1, int num2) {
		
		Calculator calc_service = new Calculator();
		CalculatorSoap calc_service_soap = calc_service.getCalculatorSoap();
		
		int answer = calc_service_soap.divide(num1, num2);
		return answer;
	}
	
	public String convert2word(String answer) {
		// unfortunately answer has limits of integer, so no extra value comes from BigInteger
		BigInteger bigAnswer = new BigInteger(answer);
		NumberConversion conversion_service = new NumberConversion();
        NumberConversionSoapType conversion_service_soap = conversion_service.getNumberConversionSoap();
        String wordAnswer = conversion_service_soap.numberToWords(bigAnswer);  
        return wordAnswer;
	}
	
}
