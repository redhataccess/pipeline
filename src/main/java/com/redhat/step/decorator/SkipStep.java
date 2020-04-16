package com.redhat.step.decorator;

import com.redhat.pipeline.PipelineContext;

/**
 * This step simply ignores processing anything.
 *
 * @author sfloess
 */
public class SkipStep extends AbstractDecoratorStep {

    public SkipStep() {
    }

    @Override
    public PipelineContext process(final PipelineContext context) throws Exception {
        logInfo("Skipping step [", getName(), "] -> [", super.getDecoratee(), "]");

        return context;
    }
}
