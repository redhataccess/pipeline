package com.redhat.step.block;

import com.redhat.pipeline.PipelineContext;

public class TimerStep extends AbstractBlockStep {

    private String var;

    void setVar(final PipelineContext context, final long totalTime) {
        context.getStepContext().getStepVars().set(getVar(), totalTime);

        logIfDebug("timer [", getVar(), "] -> [", totalTime, "]");
    }

    void setVar(final PipelineContext context, final long startTime, final long endTime) {
        setVar(context, endTime - startTime);
    }

    void processContext(final PipelineContext context, final long startTime) throws Exception {
        super.process(context);

        setVar(context, startTime, System.currentTimeMillis());
    }

    public TimerStep() {
        this.var = generateUniqueVariableName("timer");
    }

    public String getVar() {
        return var;
    }

    public void setVar(final String var) {
        this.var = var;
    }

    @Override
    public PipelineContext process(final PipelineContext context) throws Exception {
        processContext(context, System.currentTimeMillis());

        return context;
    }
}
