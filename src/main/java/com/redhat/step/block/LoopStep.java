package com.redhat.step.block;

import com.redhat.common.utils.Strings;
import com.redhat.step.StepContext;

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
        this.var = Strings.generateUniqueStringForPrefix("loop");
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
    public StepContext process(final StepContext context) {
        logInfo("Executing for [", getTimes(), "] iterations");

        for (int index = 0; index < getTimes() && !context.getPipelineContext().isDone(); index++) {
            context.getStepVars().set(getVar(), index);
            super.process(context);
        }

        return context;
    }
}
