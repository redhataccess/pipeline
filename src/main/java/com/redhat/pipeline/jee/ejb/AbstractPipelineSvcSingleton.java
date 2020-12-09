package com.redhat.pipeline.jee.ejb;

import com.redhat.common.AbstractBase;
import com.redhat.common.context.VarContext;
import com.redhat.common.utils.Strings;
import com.redhat.global.GlobalContext;
import com.redhat.pipeline.Pipeline;
import com.redhat.pipeline.PipelineContext;
import com.redhat.pipeline.PipelineDefinitions;
import com.redhat.pipeline.PipelineExecutor;
import com.redhat.pipeline.PipelineVarNameEnum;
import com.redhat.pipeline.context.DefaultPipelineContext;
import com.redhat.step.StepContext;
import java.util.Collections;
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
     * Determine if <code>pipelineName</code> exists in <code>pipelineDefinitions</code>.
     */
    boolean isPipelinePresent(final PipelineDefinitions pipelineDefinitions, final String pipelineName) {
        return null != pipelineDefinitions && pipelineDefinitions.isPipelineDefined(pipelineName);
    }

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
     * Subclasses are responsible for computing/returning step contexts.
     */
    protected abstract StepContext createStepContext();

    /**
     * Subclasses are responsible for computing/returning the namespace.
     */
    protected abstract Map<String, PipelineDefinitions> getNameSpace();

    /**
     * Called when a pipeline is to be run, but isn't found.
     */
    protected abstract void pipelineNotFound(String pipelineNameSpace, String pipelineName);

    /**
     * Ensures a pipeline is fond for namespace and name.
     */
    protected Pipeline ensurePipeline(final String pipelineNameSpace, final String pipelineName) {
        if (!isPipelinePresent(pipelineNameSpace, pipelineName)) {
            logError("Cannot find pipeline [", pipelineNameSpace, " : ", pipelineName, "]");

            pipelineNotFound(pipelineNameSpace, pipelineName);
        }

        logIfInfo("Creating pipeline [", pipelineNameSpace, " : ", pipelineName, "]");

        return getPipelineDefinitions(pipelineNameSpace).create(pipelineName);
    }

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
        return new DefaultPipelineContext(getPipelineExecutor(), getPipelineDefinitions(nameSpace), createPipelineVars(), getGlobalContext(), createStepContext());
    }

    /**
     * Default impl to create a pipeline context with just the query params set.
     */
    protected PipelineContext createContext(final String nameSpace, final Map<String, String[]> queryParams) {
        return PipelineVarNameEnum.QUERY_PARAMS.setPipelineVar(createContext(nameSpace), queryParams);
    }

    /**
     * Default imp to create a pipeline context with query params and payload set.
     */
    protected PipelineContext createContext(final String nameSpace, final Map<String, String[]> queryParams, final Object payload) {
        return PipelineVarNameEnum.PAYLOAD.setPipelineVar(createContext(nameSpace, queryParams), payload);
    }

    /**
     * Default constructor.
     */
    protected AbstractPipelineSvcSingleton() {
    }

    /**
     * Subclasses create the namespace collection, but this opens it to those who want the raw object. Please note: it is unmodifiable.
     */
    public Map<String, PipelineDefinitions> getNameSpaceCopy() {
        return Collections.unmodifiableMap(getNameSpace());
    }

    /**
     * Return the total number of namespaces or zero if no namespaces exist (or is null).
     */
    public int getTotalNameSpaces() {
        return null != getNameSpaceCopy() ? getNameSpaceCopy().size() : 0;
    }

    /**
     * Determine if <code>pipeline</code> exists in the <code>pipelineNameSpace</code>.
     */
    public boolean isPipelinePresent(final String pipelineNameSpace, final String pipelineName) {
        return !Strings.isBlank(pipelineNameSpace) && isPipelinePresent(getNameSpaceCopy().get(pipelineNameSpace), pipelineName);
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
    public <T> T runPipeline(final String pipelineNameSpace, final String pipelineName, final Map<String, String[]> queryParams, final Object payload) {
        return ensurePipeline(pipelineNameSpace, pipelineName).process(createContext(pipelineNameSpace, queryParams, payload)).getResult();
    }

    /**
     * This will run a pipeline named name.
     */
    public <T> T runPipeline(final String pipelineNameSpace, final String pipelineName, final Map<String, String[]> queryParams) {
        return ensurePipeline(pipelineNameSpace, pipelineName).process(createContext(pipelineNameSpace, queryParams)).getResult();
    }
}
