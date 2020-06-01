package com.redhat.step;

import com.redhat.common.AbstractBase;
import com.redhat.common.utils.Strings;
import com.redhat.common.utils.VariableExpansionUtils;
import java.util.Objects;
import org.json.JSONObject;

/**
 * Simple variable expansion preprocessor.
 *
 * @author sfloess
 */
public class VariableExpansionStepPreprocessor extends AbstractBase implements StepPreprocessor {

    /**
     * Replace the variables denoted in key if the value of key is a string.
     */
    JSONObject replaceVars(final String key, final JSONObject toPrepare, final StepContext context) {
        if (Strings.isString(toPrepare.get(key))) {
            toPrepare.put(key, VariableExpansionUtils.replaceVars(toPrepare.getString(key), context));
        }

        return toPrepare;
    }

    /**
     * Using the variable contexts in <code>context</code>, replace any variable values in <code>toPrepare</code>.
     */
    JSONObject replaceVars(final JSONObject toPrepare, final StepContext context) {
        Objects.requireNonNull(toPrepare, "Must provide a JSON object to preprocess");
        Objects.requireNonNull(context, "Must provide a context to preprocess");

        for (final String key : toPrepare.keySet()) {
            replaceVars(key, toPrepare, context);
        }

        return toPrepare;
    }

    /**
     * {@inheritDoc}
     */
    public JSONObject prepare(final JSONObject toPrepare, final StepContext context) {
        Objects.requireNonNull(toPrepare, "Must provide a JSON object to preprocess");
        Objects.requireNonNull(context, "Must provide a context to preprocess");

        return replaceVars(new JSONObject(toPrepare.toString()), context);
    }
}
