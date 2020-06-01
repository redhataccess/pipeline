package com.redhat.step;

import org.json.JSONObject;

/**
 * This preprocesses steps being run.
 *
 * @author sfloess
 */
public interface StepPreprocessor {
    JSONObject prepare(JSONObject toPreprocess, StepContext context);
}
