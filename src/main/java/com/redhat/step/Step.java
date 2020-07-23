package com.redhat.step;

import com.redhat.common.processor.Processor;
import com.redhat.pipeline.PipelineContext;
import java.util.Objects;

/**
 * Interface for defining a pipeline step. Please note a step may be a pipeline step or a pipeline itself.
 *
 * @author sfloess
 */
public interface Step extends Processor<StepContext> {
    /**
     * Requests the step to process a pipeline context. However, we will only process the step context
     * found inside the pipeline context.
     */
    default public PipelineContext process(PipelineContext context) {
        process(Objects.requireNonNull(context, "Must provide a non-null pipeline context!").getStepContext());

        return context;
    }
}
