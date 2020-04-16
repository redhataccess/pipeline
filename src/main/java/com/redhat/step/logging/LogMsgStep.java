package com.redhat.step.logging;

import com.redhat.pipeline.PipelineContext;
import com.redhat.step.AbstractStep;

/**
 * This class is a little different than the other AbstractLogStep subclasses.
 * Here we simply want to log a message itself with no computation.
 *
 * @author sfloess
 */
public class LogMsgStep extends AbstractStep {

    private String msg;

    public LogMsgStep() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    @Override
    public PipelineContext process(final PipelineContext context) {
        logInfo("msg [", getMsg(), "]");

        return context;
    }
}
