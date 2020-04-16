package com.redhat.step.control;

import com.redhat.pipeline.PipelineContext;
import com.redhat.step.AbstractStep;

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
    public PipelineContext process(final PipelineContext context) {
        try {
            context.getPipelineExecutor().runPipeline(getName(), context);
        } catch (final Exception exception) {
            throw new RuntimeException("Problem calling pipeline [" + getName() + "]", exception);
        }

        return context;
    }
}
