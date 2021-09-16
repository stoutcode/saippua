package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService (targetNamespace="hhtp://CalcWS")
public class MultiplyImpl implements Multiply{

	@Override
	@WebMethod
	public int multiply(int num1, int num2) {
		return num1 * num2;
	}

}
