package com.redhat.pipeline;

import com.redhat.common.processor.Processor;
import org.json.JSONObject;

/**
 * This interface represents a pipeline.
 *
 * @author sfloess
 *
 * Copyright © 2019 Red Hat, Inc.
 */
public interface Pipeline extends Processor<PipelineContext> {
    /**
     * Returns the meta form of self.
     *
     * @return the meta form of self.
     */
    JSONObject asJsonObject();
}
