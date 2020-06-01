package com.redhat.step.control;

import com.redhat.step.AbstractStep;
import com.redhat.step.StepContext;

public class CallStep extends AbstractStep {

    private String name;

    public CallStep() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public StepContext process(final StepContext context) {
        logInfo("Preparing to call pipeline [", getName(), "[");

        try {
            context.getPipelineContext().getPipelineExecutor().executeNamed(getName(), context.getPipelineContext());
        } catch (final Exception exception) {
            throw new RuntimeException("Problem calling pipeline [" + getName() + "]", exception);
        }

        return context;
    }
}
