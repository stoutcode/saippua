package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService (targetNamespace="hhtp://CalcWS")
public class DivideImpl implements Divide{

	@Override
	@WebMethod
	public int divide(int num1, int num2) {
		return num1/num2;
	}

}
