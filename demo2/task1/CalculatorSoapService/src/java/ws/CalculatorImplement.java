package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService (targetNamespace="hhtp://CalcWS")
public class CalculatorImplement implements Calculator{

	@Override
	@WebMethod
	public int add(int num1, int num2) {
		return num1 + num2;
	}

	@Override
	@WebMethod
	public int subtract(int num1, int num2) {
		return num1 - num2;
	}

	@Override
	@WebMethod
	public int multiply(int num1, int num2) {
		return num1 * num2;
	}

	@Override
	@WebMethod
	public int divide(int num1, int num2) {
		return num1 / num2;
	}

}
