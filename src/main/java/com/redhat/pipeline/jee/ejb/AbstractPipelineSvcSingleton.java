package com.redhat.pipeline.jee.ejb;

import com.redhat.common.AbstractBase;
import com.redhat.common.context.VarContext;
import com.redhat.global.GlobalContext;
import com.redhat.pipeline.PipelineContext;
import com.redhat.pipeline.PipelineDefinitions;
import com.redhat.pipeline.PipelineExecutor;
import com.redhat.pipeline.PipelineVarNameEnum;
import com.redhat.pipeline.context.DefaultPipelineContext;
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
     * Subclasses are responsible for computing/returning the namespace.
     */
    protected abstract Map<String, PipelineDefinitions> getNameSpace();

    /**
     * Subclasses are responsible for computing/returning the global context.
     */
    protected abstract GlobalContext getGlobalContext();

    /**
     * Subclasses are responsible for computing/returning the pipeline executor.
     */
    protected abstract PipelineExecutor getPipelineExecutor();

    /**
     * Subclasses are responsible for computing/returning pipeline variables.
     */
    protected abstract VarContext createPipelineVars();

    /**
     * Allow subclasses to override and return their own impls.
     */
    protected abstract PipelineDefinitions createPipelineDefinitions();

    /**
     * Default impl to return a pipeline definition.
     */
    protected PipelineDefinitions getPipelineDefinitions(final String nameSpace) {
        return getNameSpace().computeIfAbsent(nameSpace, context -> {
            return createPipelineDefinitions();
        });
    }

    /**
     * Default impl to create a context.
     */
    protected PipelineContext createContext(final String nameSpace) {
        return new DefaultPipelineContext(getPipelineExecutor(), getPipelineDefinitions(nameSpace), createPipelineVars(), getGlobalContext());
    }

    /**
     * Default impl to create a pipeline context with just the query params set.
     */
    protected PipelineContext createContext(final String nameSpace, final Map<String, String[]> queryParams) {
        return PipelineVarNameEnum.QUERY_PARAMS.setVar(createContext(nameSpace), queryParams);
    }

    /**
     * Default imp to create a pipeline context with query params and payload set.
     */
    protected PipelineContext createContext(final String nameSpace, final Map<String, String[]> queryParams, final Object payload) {
        return PipelineVarNameEnum.PAYLOAD.setVar(createContext(nameSpace, queryParams), payload);
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
    public <T> T runPipeline(final String nameSpace, final String pipelineName, final Map<String, String[]> queryParams, final Object payload) {
        return getPipelineDefinitions(nameSpace).create(pipelineName).process(createContext(nameSpace, queryParams, payload)).getResult();
    }

    /**
     * This will run a pipeline named name.
     */
    public <T> T runPipeline(final String nameSpace, final String pipelineName, final Map<String, String[]> queryParams) {
        return getPipelineDefinitions(nameSpace).create(pipelineName).process(createContext(nameSpace, queryParams)).getResult();
    }
}
