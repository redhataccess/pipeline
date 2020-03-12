package com.redhat.dxp.search.pipeline;

import java.util.Map;

/**
 * A pipeline serves as the executable representation of a list of steps.
 *
 * Pipelines are created by the {@link PipelineFactory}.
 */
public interface Pipeline {
    /**
     *
     * @param payload The payload which will be transformed by the pipeline
     * @param env A yaml-compatible construct (Maps, Lists, Strings, numbers) of system properties
     * @return The transformed payload
     */
    Object execute(Map<String,Object> payload, Map<String,Object> env);
}
