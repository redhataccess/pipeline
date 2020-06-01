package com.redhat.common.utils;

import com.redhat.common.context.VarContext;
import com.redhat.step.StepContext;
import java.util.Map;

/**
 *
 * @author sfloess
 */
public final class VariableExpansionUtils {
    /**
     * Default constructor disallowed.
     */
    private VariableExpansionUtils() {
    }

    /**
     * Replaces the variables in <code>str</code> using the values found in <code>variables</code>.
     */
    public static String replaceVars(final String str, final Map<String, ?> variables) {
        String retVal = str;

        for (final String varName : variables.keySet()) {
            retVal = retVal.replaceAll("\\$\\{" + varName + "}", variables.get(varName).toString());
        }

        return retVal;
    }

    /**
     * Replaces the variables in <code>str</code> using the values found in <code>variables</code>.
     */
    public static String replaceVars(final String str, final VarContext variables) {
        return replaceVars(str, variables.asMap());
    }

    /**
     * Replace all variables in <code>toPrepare</code> using the var contexts from <code>context</code>
     */
    public static String replaceVars(final String toPrepare, final StepContext context) {
        return replaceVars(replaceVars(replaceVars(toPrepare, context.getStepVars()), context.getPipelineContext().getPipelineVars()),
                context.getPipelineContext().getGlobalContext().getGlobalVars()
        );
    }
}
