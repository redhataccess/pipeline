package com.redhat.step;

import org.json.JSONObject;

/**
 * This step preprocessor does nothing: a no op.
 *
 * @author sfloess
 */
public final class NoOpStepPreprocessor implements StepPreprocessor {
    /**
     * {@inheritDoc}
     */
    public JSONObject prepare(final JSONObject toPreprocess, final StepContext context) {
        return toPreprocess;
    }
}
