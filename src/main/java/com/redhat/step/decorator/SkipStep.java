package com.redhat.step.decorator;

import com.redhat.step.StepContext;

/**
 * This step simply ignores processing anything.
 *
 * @author sfloess
 */
public class SkipStep extends AbstractDecoratorStep {

    public SkipStep() {
    }

    @Override
    public StepContext process(final StepContext context) {
        logInfo("Skipping step [", getName(), "] -> [", super.getDecoratee(), "]");

        return context;
    }
}
