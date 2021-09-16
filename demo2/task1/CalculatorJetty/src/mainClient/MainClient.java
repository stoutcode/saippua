package mainClient;

import java.math.BigInteger;

import com.soap.converter.NumberConversion;
import com.soap.converter.NumberConversionSoapType;

public class MainClient {
	
	public String convert2word(String inputStr) {
		BigInteger input_N = new BigInteger(inputStr);
		NumberConversion NC_service = new NumberConversion(); //created service object
        NumberConversionSoapType NC_serviceSOAP = NC_service.getNumberConversionSoap(); //create SOAP object (a port of the service)
        String result = NC_serviceSOAP.numberToWords(input_N);
        return result;
	}
}
