package elxsi.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Requested customer is inactive")
public class NoRecordFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoRecordFoundException() {
	}

	public NoRecordFoundException(String message) {
		super(message);
	}
}
