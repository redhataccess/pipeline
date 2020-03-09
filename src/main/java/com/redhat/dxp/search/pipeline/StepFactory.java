package com.redhat.dxp.search.pipeline;

/**
 * The StepFactory provides a way to get a step by its name and initiates dependency injection for steps.
 */
public interface StepFactory {
    /**
     * @param stepName The short name of the step given in yaml
     * @return An instance of the singular class which implements the named step
     */
    Step getStep(String stepName);
}
