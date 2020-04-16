package com.redhat.step.control;

import com.redhat.pipeline.PipelineContext;
import com.redhat.step.AbstractStep;

/**
 * Assigns the pipeline results to the context results variable and marks the process done.
 * <p>
 * Consumes:
 * <ul>
 * <li>var
 * </ul>
 * Produces:
 * <ul>
 * </ul>
 *
 * @author sfloess
 */
public class ReturnStep extends AbstractStep {

    /**
     * The variable that contains the results of pipeline to return to caller.
     */
    private String var;

    public ReturnStep() {
    }

    public String getVar() {
        return var;
    }

    public void setVar(final String var) {
        this.var = var;
    }

    @Override
    public PipelineContext process(final PipelineContext context) {
        context.setResult(context.getStepContext().getStepVars().get(var));
        context.setIsDone(true);

        return context;
    }
}
