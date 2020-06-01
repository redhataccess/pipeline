package com.redhat.step;

/**
 *
 * @author sfloess
 */
public class StepException extends RuntimeException {
    public StepException() {
        super();
    }

    public StepException(final String message) {
        super(message);
    }

    public StepException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public StepException(final Throwable cause) {
        super(cause);
    }
}
