package com.redhat.step.script.jsr223.jython;

import com.redhat.step.script.jsr223.AbstractJsr223ScriptStep;

/**
 * Base class for executing a JSR compatible script.
 *
 * @author sfloess
 */
public abstract class AbstractJythonStep extends AbstractJsr223ScriptStep {

    /**
     * Default constructor.
     */
    protected AbstractJythonStep() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getScriptShortName() {
        return "python";
    }
}
