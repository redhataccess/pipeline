package com.redhat.step.logging;

import com.redhat.step.AbstractStep;
import com.redhat.step.StepContext;

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
    public StepContext process(final StepContext context) {
        logInfo("msg [", getMsg(), "]");

        return context;
    }
}