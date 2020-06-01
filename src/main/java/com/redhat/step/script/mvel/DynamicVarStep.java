package com.redhat.step.script.mvel;

import com.redhat.step.StepContext;

/**
 * Allows for defining variables in a dynamic way.
 * <p>
 * As an example: { "var1": 10, "var2": 20, "var3": "var1 + var2" }
 *
 * @author sfloess
 */
public class DynamicVarStep extends AbstractMvelStep {

    public DynamicVarStep() {
    }

    @Override
    public StepContext process(final StepContext context) {
        logIfDebug("Processing MVEL dynamic variables using: ", getAdditionalProperties());

        for (final String key : getOrderedAdditionalProperties()) {
            logInfo("Key:  ", key);
            logInfo("Pipeline vars:  ", context.getPipelineContext().getPipelineVars().asMap());
            context.getPipelineContext().getPipelineVars().set(key, executeScriptStatement(context, getAdditionalProperties().get(key).toString()));
        }

        return context;
    }
}
