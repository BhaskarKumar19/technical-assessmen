package elxsi.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Requested customer is inactive")
public class InactiveCustomerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InactiveCustomerException() {
	}

	public InactiveCustomerException(String message) {
		super(message);
	}
}
