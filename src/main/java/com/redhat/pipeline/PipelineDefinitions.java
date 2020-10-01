package com.redhat.pipeline;

import com.redhat.common.JsonDefinitions;
import org.json.JSONObject;

/**
 * Pipeline definitions.
 *
 * @author sfloess
 */
public interface PipelineDefinitions extends JsonDefinitions<JSONObject> {
    /**
     * Defines a pipeline using XML,
     *
     * @param name   the name of the pipeline.
     * @param xmlStr the pipeline represented as XML in string format.
     */
    void defineXmlPipeline(String name, String xmlStr);

    /**
     * Defines a pipeline using XML,
     *
     * @param name    the name of the pipeline.
     * @param jsonStr the pipeline represented as JSON in string format.
     */
    void defineJsonPipeline(String name, String jsonStr);

    /**
     * Defines a pipeline using XML,
     *
     * @param name    the name of the pipeline.
     * @param yamlStr the pipeline represented as YAML in string format.
     */
    void defineYamlPipeline(String name, String yamlStr);

    /**
     * Using the definition named <code>name</code>, create a new pipeline.
     *
     * @param name the name of the pipeline to create
     *
     * @return the a new instance of a pipeline.
     *
     */
    Pipeline create(String name);

    /**
     * Do we have a pipeline definition?
     *
     * @param name is the name of the pipeline to examine for being defined.
     *
     * @return true if the pipeline is defined, or false if not.
     */
    boolean isPipelineDefined(String name);
}
