package com.redhat.step.logging;

import com.redhat.pipeline.PipelineContext;

public class LogContextStep extends AbstractLogStep {

    @Override
    protected String computeMsgValue(final PipelineContext context) {
        return context.toString();
    }
}
