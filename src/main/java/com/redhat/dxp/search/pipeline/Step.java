package com.redhat.dxp.search.pipeline;

import java.util.Map;

/**
 * A unit of work in the pipeline.
 *
 * Step instances are managed by the {@link StepFactory}.
 */
public interface Step {
    /**
     * Do what needs to be done.
     *
     * The execution context is a map with string keys and object values.  The following keys are guaranteed to be
     * populated as described, per the API contract.
     *
     * <dl>
     *     <dt>parameters</dt><dd>A Map of String and Object containing the parameters that were provided to this
     *     step in the pipline definition, with variables substituted.</dd>
     *     <dt>payload</dt><dd>A Map of String and Object containing the payload from which the step takes its content.</dd>
     *     <dt>env</dt><dd>An unmodifiable Map of String and Object, containing parameters passed in to the pipline at
     *        the beginning of execution and, within that, a key "sys" which includes the system environment variables.</dd>
     * </dl>
     *
     * A specific implementation of the engine may offer other values, but reliance upon those will
     * render the step not portable among implementations.
     *
     * @param executionContext all the step needs to know to do its work
     * @return Any object will be stored at the location named by the "target" value within this step's
     *    definition in the pipeline.
     */
    Object execute(Map<String, Object> executionContext);
}
