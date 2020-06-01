package com.redhat.step.block;

import com.redhat.common.utils.Strings;
import com.redhat.step.StepContext;

public class TimerStep extends AbstractBlockStep {

    private String var;

    void setVar(final StepContext context, final long totalTime) {
        context.getStepVars().set(getVar(), totalTime);

        logIfDebug("timer [", getVar(), "] -> [", totalTime, "]");
    }

    void setVar(final StepContext context, final long startTime, final long endTime) {
        setVar(context, endTime - startTime);
    }

    StepContext processContext(final StepContext context, final long startTime) {
        super.process(context);

        setVar(context, startTime, System.currentTimeMillis());

        return context;
    }

    public TimerStep() {
        this.var = Strings.generateUniqueStringForPrefix("timer");
    }

    public String getVar() {
        return var;
    }

    public void setVar(final String var) {
        this.var = var;
    }

    @Override
    public StepContext process(final StepContext context) {
        return processContext(context, System.currentTimeMillis());
    }
}
