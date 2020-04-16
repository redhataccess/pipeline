package com.redhat.step.context;

import com.redhat.common.context.VarContext;
import com.redhat.step.StepContext;
import com.redhat.step.StepDefinitions;
import com.redhat.step.StepPreprocessor;

/**
 * Abstract base class of a StepContext.
 *
 * @author sfloess
 */
public abstract class AbstractStepContext implements StepContext {
    private final StepDefinitions stepDefinitions;
    private final StepPreprocessor stepPreprocessor;

    private final VarContext stepVars;

    protected AbstractStepContext(final StepDefinitions stepDefinitions, final StepPreprocessor stepPreprocessor, final VarContext stepVars) {
        this.stepDefinitions = stepDefinitions;
        this.stepPreprocessor = stepPreprocessor;
        this.stepVars = stepVars;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StepDefinitions getStepDefinitions() {
        return stepDefinitions;
    }

    @Override
    public StepPreprocessor getStepPreprocessor() {
        return stepPreprocessor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VarContext getStepVars() {
        return stepVars;
    }
}
