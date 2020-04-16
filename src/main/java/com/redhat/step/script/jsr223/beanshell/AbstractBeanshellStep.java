package com.redhat.step.script.jsr223.beanshell;

import com.redhat.step.script.jsr223.AbstractJsr223ScriptStep;

/**
 * Base class for executing a JSR compatible script.
 *
 * @author sfloess
 */
public abstract class AbstractBeanshellStep extends AbstractJsr223ScriptStep {

    /**
     * Default constructor.
     */
    protected AbstractBeanshellStep() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getScriptShortName() {
        return "beanshell";
    }
}
