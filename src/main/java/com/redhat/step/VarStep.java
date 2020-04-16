package com.redhat.step;

import com.redhat.pipeline.PipelineContext;

public class VarStep extends AbstractStep {

    protected void setVar(final PipelineContext context, final String name, final String value) {
        logIfDebug("Setting variable [", name, "] -> [", value, "]");
        context.getStepContext().getStepVars().set(name, value);
    }

    public VarStep() {
    }

    @Override
    public PipelineContext process(final PipelineContext context) {
        logIfDebug("Setting variables using: ", getAdditionalProperties());

        for (final String key : getOrderedAdditionalProperties()) {
            setVar(context, key, getAdditionalProperties().get(key).toString());
        }

        return context;
    }
}
