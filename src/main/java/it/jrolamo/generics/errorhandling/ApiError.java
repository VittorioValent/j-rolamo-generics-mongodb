package it.jrolamo.generics.errorhandling;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Vittorio Valent
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;

    private String message;

    private List<String> errors;

    /**
     *
     * @param status
     * @param message
     * @param error
     */
    public ApiError(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    /**
     *
     * @param error
     */
    public void setError(final String error) {
        errors = Arrays.asList(error);
    }
}
