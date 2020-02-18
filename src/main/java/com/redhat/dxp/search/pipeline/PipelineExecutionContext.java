package com.redhat.dxp.search.pipeline;

import java.util.Map;

public interface PipelineExecutionContext {
    Map<String,Object> getStepParameters();
    PipelinePayload getPayload();
    Map<String,Object> getSystemParameters();
}
