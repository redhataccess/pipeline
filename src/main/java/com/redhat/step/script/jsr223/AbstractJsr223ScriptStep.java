package com.redhat.step.script.jsr223;

import com.redhat.pipeline.PipelineContext;
import com.redhat.step.script.AbstractScriptStep;
import java.util.Map;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Base class for executing a JSR compatible script.
 *
 * @author sfloess
 */
public abstract class AbstractJsr223ScriptStep extends AbstractScriptStep {

    /**
     * Sets all variables from scriptVars into scriptContext.
     */
    void setVars(final Map scriptVars, final ScriptContext scriptContext) {
        scriptVars.forEach((k, v) -> scriptContext.setAttribute(k.toString(), v, ScriptContext.ENGINE_SCOPE));
    }

    /**
     * Sets all the variables from scriptVars into the script engine.
     */
    void setVars(final Map scriptVars, final ScriptEngine retVal) {
        setVars(scriptVars, retVal.getContext());
    }

    /**
     * Executes the script statement using script engine.
     */
    Object executeScriptStatement(final PipelineContext context, final Map scriptVars, final ScriptEngine scriptEngine, final String scriptStatement) throws Exception {
        setVars(scriptVars, scriptEngine);

        return scriptEngine.eval(scriptStatement);
    }

    /**
     * Will execute a JSR supported script statement and return the result of
     * execution.
     */
    protected Object executeScriptStatement(final PipelineContext context, final Map scriptVars, final String scriptStatement) throws Exception {
        return executeScriptStatement(context, scriptVars, new ScriptEngineManager().getEngineByName(getScriptShortName()), scriptStatement);
    }

    /**
     * Return the short name of the scriping language. As an example JavaScript,
     * Beanshell, Groovy, etc.
     */
    protected abstract String getScriptShortName();

    /**
     * Default constructor.
     */
    protected AbstractJsr223ScriptStep() {
    }
}
