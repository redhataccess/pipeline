package com.redhat.step.script.jsr223.groovy;

import com.redhat.step.script.jsr223.AbstractJsr223ScriptStep;

/**
 * Base class for executing a JSR compatible script.
 *
 * @author sfloess
 */
public abstract class AbstractGroovyStep extends AbstractJsr223ScriptStep {

    /**
     * Default constructor.
     */
    protected AbstractGroovyStep() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getScriptShortName() {
        return "groovy";
    }
}
