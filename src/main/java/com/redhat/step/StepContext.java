package com.redhat.step;

import com.redhat.common.context.VarContext;

/**
 * Context for executing a pipeline. Maintains external state for the pipeline and its steps.
 *
 * @author sfloess
 */
public interface StepContext {
    /**
     * All our step definitions.
     */
    StepDefinitions getStepDefinitions();

    /**
     * The object in charge of preprocessing and preparing a step to be used for processing.
     */
    StepPreprocessor getStepPreprocessor();

    /**
     * Return all our step vars. These are variables only visible to the currently executing step.
     */
    VarContext getStepVars();
}
