package org.ties.SaippuaRESTws.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ParamException;
import org.ties.SaippuaRESTws.models.ErrorMessage;


@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{
	@Override
	public Response toResponse(Throwable ex) {
		System.out.println(ex);
		ex.printStackTrace();
		String message = ex.getMessage();
		Status status = Status.BAD_REQUEST;
		String documentation = "http://google.com";
		
		// Filter specific exceptions here as needed.
		if ((ex instanceof DataNotFoundException) || (ex instanceof ParamException)) {
			status = Status.NOT_FOUND;
		} else if (ex instanceof NoSuchFieldException) {
			status = Status.NOT_FOUND;
		} else if (ex instanceof CreateException) {
			status = Status.SEE_OTHER;
		} else if (ex instanceof UpdateException) {
			status = Status.CONFLICT;
		}
		
		int code = status.getStatusCode();
		ErrorMessage errorMessage = new ErrorMessage(message, code, documentation);
		return Response.status(status).entity(errorMessage).build();
	}
}
