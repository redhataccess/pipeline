package com.redhat.step.script.jsr223.jruby;

import com.redhat.step.script.jsr223.AbstractJsr223ScriptStep;

/**
 * Base class for executing a JSR compatible script.
 *
 * @author sfloess
 */
public abstract class AbstractJrubyStep extends AbstractJsr223ScriptStep {

    /**
     * Default constructor.
     */
    protected AbstractJrubyStep() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getScriptShortName() {
        return "ruby";
    }
}
