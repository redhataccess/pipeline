package com.redhat.step.script.mvel;

import com.redhat.pipeline.PipelineContext;
import java.util.Map;

/**
 * Allows for defining variables in a dynamic way.
 * <p>
 * As an example: { "var1": 10, "var2": 20, "var3": "var1 + var2" }
 *
 * @author sfloess
 */
public class DynamicVarStep extends AbstractMvelStep {

    @Override
    protected Map createScriptVars(final PipelineContext context) {
        return context.getStepContext().getStepVars().asMap();
    }

    public DynamicVarStep() {
    }

    @Override
    public PipelineContext process(final PipelineContext context) throws Exception {
        logIfDebug("Processing MVEL dynamic variables using: ", getAdditionalProperties());

        for (final String key : getOrderedAdditionalProperties()) {
            context.getStepContext().getStepVars().set(key, executeScriptStatement(context, getAdditionalProperties().get(key).toString()));
        }

        return context;
    }
}
