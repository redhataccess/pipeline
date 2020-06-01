package com.redhat.step;

import com.redhat.common.processor.AbstractProcessor;

/**
 * Abstract base class for a pipeline stage.
 *
 * @author sfloess
 */
public abstract class AbstractStep extends AbstractProcessor<StepContext> implements Step {
    /**
     * Default constructor.
     */
    protected AbstractStep() {
        logIfDebug("Constructing step [", getClass().getName(), "]");
    }
}
