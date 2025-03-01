package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyPurchasedException extends RuntimeException {
    public AlreadyPurchasedException() {
        super("The ticket has been already purchased!");
    }
}
