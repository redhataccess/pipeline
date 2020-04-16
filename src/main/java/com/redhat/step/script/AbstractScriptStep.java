package com.redhat.step.script;

import com.redhat.pipeline.PipelineContext;
import com.redhat.step.AbstractStep;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for doing MVEL related work.
 *
 * @author sfloess
 */
public abstract class AbstractScriptStep extends AbstractStep {

    /**
     * Override if you want to do any special processing with the result.
     */
    protected Object processScriptResult(final PipelineContext context, final Object result) {
        return result;
    }

    /**
     * Override if you want to use different variables than context, queryParams
     * and all stageVars...
     */
    protected Map populateScriptVars(final PipelineContext context, final Map scriptVars) {
        scriptVars.put("context", context);
        scriptVars.putAll(context.getStepContext().getStepVars().asMap());

        return scriptVars;
    }

    /**
     * Override if you want to take care of creating and populating the MVEL
     * variables.
     */
    protected Map createScriptVars(final PipelineContext context) {
        return populateScriptVars(context, new HashMap<>());
    }

    /**
     * Execute the script statement. scriptVars will have all the variables for
     * your script's execution environment.
     */
    protected abstract Object executeScriptStatement(final PipelineContext context, final Map scriptVars, final String scriptStatement) throws Exception;

    /**
     * Will execute script statement and return the result of execution.
     */
    protected Object executeScriptStatement(final PipelineContext context, final String scriptStatement) throws Exception {
        logIfDebug("Script statement to execute:  [", scriptStatement, "]");

        return processScriptResult(context, executeScriptStatement(context, createScriptVars(context), scriptStatement));
    }

    /**
     * Default constructor.
     */
    protected AbstractScriptStep() {
    }
}
