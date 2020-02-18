package com.redhat.dxp.search.pipeline;

public interface PipelineStep {
    Object execute(PipelineExecutionContext context);
}
