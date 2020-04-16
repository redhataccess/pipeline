package com.redhat.step;

import com.redhat.common.context.VarContext;
import com.redhat.pipeline.PipelineContext;
import java.util.Map;
import java.util.Objects;
import org.json.JSONObject;

/**
 * Simple variable expansion preprocessor.
 *
 * @author sfloess
 */
public class VariableExpansionStepPreprocessor implements StepPreprocessor {
    String replaceVars(final String str, final Map<String, ?> variables) throws Exception {
        String retVal = str;

        for (final String varName : variables.keySet()) {
            retVal = retVal.replaceAll("\\$\\{" + varName + "}", variables.get(varName).toString());
        }

        return retVal;
    }

    String replaceVars(final String str, final VarContext variables) throws Exception {
        return replaceVars(str, variables.asMap());
    }

    /**
     * {@inheritDoc}
     */
    public JSONObject preprocess(final JSONObject toPreprocess, final PipelineContext context) throws Exception {
        Objects.requireNonNull(toPreprocess, "Must provide a JSON object to preprocess");
        Objects.requireNonNull(context, "Must provide a context to preprocess");

        return new JSONObject(
                replaceVars(
                        replaceVars(replaceVars(toPreprocess.toString(), context.getStepContext().getStepVars()), context.getPipelineVars()
                        ), context.getGlobalVars()
                )
        );
    }
}
