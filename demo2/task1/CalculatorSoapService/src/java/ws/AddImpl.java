package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService (targetNamespace="hhtp://CalcWS")
public class AddImpl implements Add{

	@Override
	@WebMethod
	public int add(int num1, int num2) {
		return num1 + num2;
	}

}
