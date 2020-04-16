package com.redhat.common;

/**
 * Interface for classes that can process an object.
 *
 * @author sfloess
 */
@FunctionalInterface
public interface Processor<T> {

    /**
     * Called before processing is invoked.
     */
    default void preProcess(T preProcess) {
    }

    /**
     * Perform processing.
     */
    T process(T toProcess) throws Exception;

    /**
     * Called after processing is invoked.
     */
    default void postProcess(T processed) {
    }

    /**
     * Called after processing is invoked when there is a failure.
     */
    default void postProcessFailure(T failedProcess, final Throwable failure) {
    }
}
