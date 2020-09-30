package com.redhat.pipeline.jee.ejb;

import com.redhat.common.context.DefaultVarContext;
import com.redhat.common.context.VarContext;
import com.redhat.global.GlobalContext;
import com.redhat.global.context.DefaultGlobalContext;
import com.redhat.pipeline.PipelineDefinitions;
import com.redhat.pipeline.PipelineExecutor;
import com.redhat.pipeline.definitions.DefaultPipelineDefinitions;
import com.redhat.pipeline.executor.DefaultPipelineExecutor;
import com.redhat.step.StepContext;
import com.redhat.step.context.DefaultStepContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;

/**
 * Default implementation - albeit a very naive version.
 *
 * @author sfloess
 */
public class DefaultPipelineSvcSingleton extends AbstractPipelineSvcSingleton {
    private Map<String, PipelineDefinitions> nameSpace;
    private GlobalContext globalContext;
    private PipelineExecutor pipelineExecutor;

    @PostConstruct
    @Override
    protected void init() {
        super.init();

        nameSpace = new ConcurrentHashMap<>();
        globalContext = new DefaultGlobalContext();
        pipelineExecutor = new DefaultPipelineExecutor();

        logInfo("Constructed and ready");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, PipelineDefinitions> getNameSpace() {
        return nameSpace;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GlobalContext getGlobalContext() {
        return globalContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PipelineExecutor getPipelineExecutor() {
        return pipelineExecutor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected VarContext createPipelineVars() {
        return new DefaultVarContext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PipelineDefinitions createPipelineDefinitions() {
        return new DefaultPipelineDefinitions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected StepContext createStepContext() {
        return new DefaultStepContext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void pipelineNotFound(final String pipelineNameSpace, final String pipelineName) {
        logError("Pipeline not found [", pipelineNameSpace, " : ", pipelineName, "]");

        throw new IllegalArgumentException("Pipeline not found:  [" + pipelineNameSpace + " : " + pipelineName + "]");
    }
}
