package com.redhat.step;

import com.redhat.pipeline.PipelineContext;
import org.json.JSONObject;

/**
 * This preprocesses steps being run.
 *
 * @author sfloess
 */
public interface StepPreprocessor {
    JSONObject preprocess(JSONObject toPreprocess, PipelineContext context) throws Exception;
}
