package com.redhat.pipeline.context;

import com.redhat.common.AbstractBase;
import com.redhat.common.context.VarContext;
import com.redhat.global.GlobalContext;
import com.redhat.pipeline.PipelineContext;
import com.redhat.pipeline.PipelineDefinitions;
import com.redhat.pipeline.PipelineExecutor;
import java.util.Objects;

/**
 * Abstract implementation of a PipelineContext.
 *
 * @author sfloess
 */
public abstract class AbstractPipelineContext extends AbstractBase implements PipelineContext {
    /**
     * Can execute pipelines.
     */
    private final PipelineExecutor pipelineExecutor;

    /**
     * All our pipeline definitions.
     */
    private final PipelineDefinitions pipelineDefintions;

    /**
     * Variables shared across steps;
     */
    private final VarContext pipelineVars;

    /**
     * The result of running a pipeline.
     */
    private Object result;

    /**
     * Flag used to denote if pipeline processing has completed.
     */
    private boolean isDone;

    /**
     * Our global context.
     */
    private final GlobalContext globalContext;

    /**
     * Mutator constructor.
     */
    public AbstractPipelineContext(final PipelineExecutor pipelineExecutor, final PipelineDefinitions pipelineDefintions, final VarContext pipelineVars, final GlobalContext globalContext) {
        this.pipelineExecutor = Objects.requireNonNull(pipelineExecutor, "Cannot have a null pipleline executor");
        this.pipelineDefintions = Objects.requireNonNull(pipelineDefintions, "Cannot have null pipleline definitions");
        this.pipelineVars = Objects.requireNonNull(pipelineVars, "Cannot have null pipeline variables");
        this.isDone = false;
        this.globalContext = Objects.requireNonNull(globalContext, "Cannot have null global contexts");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PipelineExecutor getPipelineExecutor() {
        return pipelineExecutor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PipelineDefinitions getPipelineDefinitions() {
        return pipelineDefintions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VarContext getPipelineVars() {
        return pipelineVars;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResult(final Object result) {
        this.result = result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getResult() {
        return (T) result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIsDone(final boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDone() {
        return isDone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GlobalContext getGlobalContext() {
        return globalContext;
    }
}
