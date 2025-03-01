package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OutOfBoundsException.class)
    public ResponseEntity<Map<String, String>> handleOutOfBoundsException(OutOfBoundsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());  // JSON { "error": "The number of a row or a column is out of bounds!" }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyPurchasedException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyPurchasedException(AlreadyPurchasedException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());  // JSON { "error": "The ticket has been already purchased!" }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<Map<String, String>> handleExpiredTokenException(ExpiredTokenException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());  // JSON { "error": "Token has expired" }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPasswordException(InvalidPasswordException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());  // JSON { "error": "Token has expired" }
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
