package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService (targetNamespace="hhtp://CalcWS")
public class SubtractImpl implements Subtract{

	@Override
	@WebMethod
	public int subtract(int num1, int num2) {
		return num1 - num2;
	}

}
