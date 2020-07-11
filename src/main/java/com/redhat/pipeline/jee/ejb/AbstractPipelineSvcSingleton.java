package com.redhat.pipeline.jee.ejb;

import com.redhat.common.AbstractBase;
import com.redhat.global.GlobalContext;
import com.redhat.pipeline.PipelineContext;
import com.redhat.pipeline.PipelineDefinitions;
import com.redhat.pipeline.PipelineVarNameEnum;
import com.redhat.pipeline.context.DefaultPipelineContext;
import com.redhat.pipeline.definitions.DefaultPipelineDefinitions;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Our pipeline implementation. Manages all pipelines.
 *
 * @author sfloess
 */
@Startup
@Singleton
@Lock(LockType.READ)
public abstract class AbstractPipelineSvcSingleton extends AbstractBase {

    /**
     * Upon initialization we will define all our steps denoted by name and
     * class.
     */
    @PostConstruct
    protected void init() {
        logInfo("Constructed and ready");
    }

    /**
     * Subclasses are responsible for computing/returning the global context.
     */
    protected abstract GlobalContext getGlobalContext();

    /**
     * Subclasses are responsible for computing/returning the namespace.
     */
    protected abstract Map<String, PipelineDefinitions> getNameSpace();

    /**
     * Default impl to return a pipeline definition.
     */
    protected PipelineDefinitions getPipelineDefinitions(final String nameSpace) {
        return getNameSpace().computeIfAbsent(nameSpace, context -> new DefaultPipelineDefinitions());
    }

    /**
     * Default imp to create a pipeline context with query params and payload set.
     */
    protected PipelineContext createContext(final Map<String, String[]> queryParams, final Object payload) {
        final DefaultPipelineContext retVal = new DefaultPipelineContext(getGlobalContext());

        PipelineVarNameEnum.QUERY_PARAMS.setVar(retVal, queryParams);
        PipelineVarNameEnum.PAYLOAD.setVar(retVal, payload);

        return retVal;
    }

    /**
     * Default impl to create a pipeline context with just the query params set.
     */
    protected PipelineContext createContext(final Map<String, String[]> queryParams) {
        final DefaultPipelineContext retVal = new DefaultPipelineContext(getGlobalContext());

        PipelineVarNameEnum.QUERY_PARAMS.setVar(retVal, queryParams);

        return retVal;
    }

    /**
     * Default constructor.
     */
    protected AbstractPipelineSvcSingleton() {
    }

    /**
     * Called when a client wants to define it's own pipeline. The name is the
     * name of the pipeline and the pipeline represents a JSON Object.
     */
    public void definePipeline(final String nameSpace, final String pipelineName, String rawPipeline) {
        getPipelineDefinitions(nameSpace).defineYamlPipeline(pipelineName, rawPipeline);
    }

    /**
     * Return the pipeline named name.
     */
    public String getPipeline(final String nameSpace, final String pipelineName) {
        return getPipelineDefinitions(nameSpace).getDefinition(pipelineName).toString(4);
    }

    /**
     * Return all the defined pipelines.
     */
    public String getPipelines(final String nameSpace) {
        return getPipelineDefinitions(nameSpace).getDefinitions().toString();
    }

    /**
     * Remove the pipeline named name.
     */
    public String removePipeline(final String nameSpace, final String pipelineName) {
        return getPipelineDefinitions(nameSpace).removeDefinition(pipelineName).toString(4);
    }

    /**
     * This will run a pipeline named name.
     */
    public Object runPipeline(final String nameSpace, final String pipelineName, final Map<String, String[]> queryParams, final Object payload) {
        return getPipelineDefinitions(nameSpace).create(pipelineName).process(createContext(queryParams, payload));
    }

    /**
     * This will run a pipeline named name.
     */
    public Object runPipeline(final String nameSpace, final String pipelineName, final Map<String, String[]> queryParams) {
        return getPipelineDefinitions(nameSpace).create(pipelineName).process(createContext(queryParams));
    }
}
