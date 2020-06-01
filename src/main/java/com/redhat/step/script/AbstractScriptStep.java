package com.redhat.step.script;

import com.redhat.step.AbstractStep;
import com.redhat.step.StepContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for doing MVEL related work.
 *
 * @author sfloess
 */
public abstract class AbstractScriptStep extends AbstractStep {
    public static final String CONTEXT_SCRIPT_VAR = "context";

    /**
     * Taking the result, we lof both the script statement and the result.
     */
    Object logAndReturnScriptResult(final Object result, final String scriptStatement) {
        logIfDebug("Result of executing [", scriptStatement, "] = [", result, "]");

        return result;
    }

    /**
     * Override if you want to do any special processing with the result.
     */
    protected Object processScriptResult(final StepContext context, final Object result) {
        return result;
    }

    /**
     * Override if you want to use different variables than context, queryParams
     * and all stageVars...
     */
    protected Map populateScriptVars(final StepContext context, final Map scriptVars) {
        scriptVars.put(CONTEXT_SCRIPT_VAR, context);

        scriptVars.putAll(context.getPipelineContext().getGlobalContext().getGlobalVars().asMap());
        scriptVars.putAll(context.getPipelineContext().getPipelineVars().asMap());
        scriptVars.putAll(context.getStepVars().asMap());

        return scriptVars;
    }

    /**
     * Override if you want to take care of creating and populating the MVEL
     * variables.
     */
    protected Map createScriptVars(final StepContext context) {
        return populateScriptVars(context, new HashMap<>());
    }

    /**
     * Execute the script statement. scriptVars will have all the variables for
     * your script's execution environment.
     */
    protected abstract Object executeScriptStatement(final StepContext context, final Map scriptVars, final String scriptStatement);

    /**
     * Will execute script statement and return the result of execution.
     */
    protected Object executeScriptStatement(final StepContext context, final String scriptStatement) {
        logIfDebug("Script statement to execute:  [", scriptStatement, "]");

        return logAndReturnScriptResult(processScriptResult(context, executeScriptStatement(context, createScriptVars(context), scriptStatement)), scriptStatement);
    }

    /**
     * Default constructor.
     */
    protected AbstractScriptStep() {
    }
}
