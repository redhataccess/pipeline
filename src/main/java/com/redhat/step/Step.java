package com.redhat.step;

import com.redhat.common.Processor;
import com.redhat.pipeline.PipelineContext;

/**
 * Interface for defining a pipeline step. Please note a step may be a pipeline step or a pipeline itself.
 *
 * @author sfloess
 */
@FunctionalInterface
public interface Step extends Processor<PipelineContext> {
    default public String getName() {
        return "";
    }

    default public String getDescription() {
        return "";
    }
}
