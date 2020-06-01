package com.redhat.pipeline.definitions;

import com.redhat.common.AbstractJsonDefinitions;
import com.redhat.common.utils.JsonUtils;
import com.redhat.pipeline.DefaultPipeline;
import com.redhat.pipeline.Pipeline;
import com.redhat.pipeline.PipelineDefinitions;
import org.json.JSONObject;

/**
 * Our default pipeline implementation. Manages all pipelines.
 *
 * @author sfloess
 */
public abstract class AbstractPipelineDefinitions extends AbstractJsonDefinitions<JSONObject> implements PipelineDefinitions {
    /**
     * Default constructor.
     */
    protected AbstractPipelineDefinitions() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void defineJsonPipeline(final String name, final String jsonStr) {
        getDefMap().put(name, JsonUtils.jsonStrToJsonObject(jsonStr));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void defineXmlPipeline(final String name, final String xmlStr) {
        getDefMap().put(name, JsonUtils.xmlStrToJsonObject(xmlStr));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void defineYamlPipeline(final String name, final String yamlStr) {
        getDefMap().put(name, JsonUtils.yamlStrToJsonObject(yamlStr));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pipeline create(String name) {
        return new DefaultPipeline(getDefMap().get(ensureDefinitionName(name)));
    }
}
