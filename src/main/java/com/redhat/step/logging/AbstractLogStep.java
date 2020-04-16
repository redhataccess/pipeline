package com.redhat.step.logging;

import com.redhat.pipeline.PipelineContext;
import com.redhat.step.AbstractStep;

/**
 * Abstract base class if we want to log something as a message plus the thing
 * to log. As an example, we want to log "Here is the pipeline context" and the
 * pipeline context.
 *
 * @author sfloess
 */
public abstract class AbstractLogStep extends AbstractStep {

    private String msg;

    protected abstract String computeMsgValue(PipelineContext context);

    protected AbstractLogStep() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    @Override
    public PipelineContext process(final PipelineContext context) {
        logInfo(null == getMsg() ? "" : msg, ": ", computeMsgValue(context));

        return context;
    }
}
