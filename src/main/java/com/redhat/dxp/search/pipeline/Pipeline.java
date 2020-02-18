package com.redhat.dxp.search.pipeline;

public interface Pipeline {
    Object execute(PipelineExecutionContext context);
}
