package com.redhat.step;

public class VarStep extends AbstractStep {

    protected void setVar(final StepContext context, final String name, final String value) {
        logIfDebug("Setting variable [", name, "] -> [", value, "]");
        context.getPipelineContext().getPipelineVars().set(name, value);
    }

    public VarStep() {
    }

    @Override
    public StepContext process(final StepContext context) {
        logIfDebug("Setting variables using: ", getAdditionalProperties());

        for (final String key : getOrderedAdditionalProperties()) {
            setVar(context, key, getAdditionalProperties().get(key).toString());
        }

        return context;
    }
}
