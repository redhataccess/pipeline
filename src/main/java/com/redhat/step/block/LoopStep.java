package com.redhat.step.block;

import com.redhat.pipeline.PipelineContext;

public class LoopStep extends AbstractBlockStep {

    private String var;
    private int times;

    int ensureTimes(final int times) {
        if (times < 0) {
            logError("Cannot set a negative number of times!");
            throw new IllegalArgumentException("Cannot use a negative number of times!");
        }

        return times;
    }

    public LoopStep() {
        this.var = generateUniqueVariableName("loop");
        this.times = 0;
    }

    public String getVar() {
        return var;
    }

    public void setVar(final String var) {
        this.var = var;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(final int times) {
        this.times = ensureTimes(times);
    }

    @Override
    public PipelineContext process(final PipelineContext context) throws Exception {
        logInfo("Executing for [", getTimes(), "] iterations");

        for (int index = 0; index < getTimes() && !context.isDone(); index++) {
            context.getStepContext().getStepVars().set(var, index);
            super.process(context);
        }

        return context;
    }
}
