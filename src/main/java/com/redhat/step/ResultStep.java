package com.redhat.step;

import com.redhat.common.utils.VariableExpansionUtils;

public class ResultStep extends AbstractStep {
    private String value;

    public ResultStep() {
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public StepContext process(final StepContext context) {
        context.getPipelineContext().setResult(VariableExpansionUtils.replaceVars(getValue(), context));

        return context;
    }
}
