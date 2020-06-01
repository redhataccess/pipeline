package com.redhat.step.context;

import com.redhat.common.context.VarContext;
import com.redhat.step.StepContext;
import com.redhat.step.StepDefinitions;
import com.redhat.step.StepExecutor;
import com.redhat.step.StepPreprocessor;
import java.util.Objects;

/**
 * Abstract base class of a StepContext.
 *
 * @author sfloess
 */
public abstract class AbstractStepContext implements StepContext {
    private final StepExecutor stepExecutor;
    private final StepDefinitions stepDefinitions;
    private final StepPreprocessor stepPreprocessor;

    private final VarContext stepVars;

    protected AbstractStepContext(final StepExecutor stepExecutor, final StepDefinitions stepDefinitions, final StepPreprocessor stepPreprocessor, final VarContext stepVars) {
        this.stepExecutor = Objects.requireNonNull(stepExecutor, "Cannot have null step executors");
        this.stepDefinitions = Objects.requireNonNull(stepDefinitions, "Cannot have null step definitions");
        this.stepPreprocessor = Objects.requireNonNull(stepPreprocessor, "Cannot have null step preprocessors");
        this.stepVars = Objects.requireNonNull(stepVars, "Cannot have null step variables");
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

    /**
     * {@inheritDoc}
     */
    @Override
    public StepExecutor getStepExecutor() {
        return stepExecutor;
    }
}
