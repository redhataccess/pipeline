package com.redhat.step.script.jsr223.javascript;

import com.redhat.step.script.jsr223.AbstractJsr223ScriptStep;

/**
 * Base class for executing a JSR compatible script.
 *
 * @author sfloess
 */
public abstract class AbstractJavascriptStep extends AbstractJsr223ScriptStep {

    /**
     * Default constructor.
     */
    protected AbstractJavascriptStep() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getScriptShortName() {
        return "nashorn";
    }
}
