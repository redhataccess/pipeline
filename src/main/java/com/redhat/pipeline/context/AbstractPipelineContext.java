package com.redhat.pipeline.context;

import com.redhat.common.AbstractBase;
import com.redhat.common.context.VarContext;
import com.redhat.pipeline.PipelineContext;
import com.redhat.pipeline.PipelineDefinitions;
import com.redhat.pipeline.PipelineExecutor;
import com.redhat.step.StepContext;
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
     * All global variables.
     */
    private final VarContext globalVars;

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
     * When executing a step in a pipeline, this is related to step processing.
     */
    private final StepContext stepContext;

    /**
     * Mutator constructor.
     */
    public AbstractPipelineContext(final PipelineExecutor pipelineExecutor, final PipelineDefinitions pipelineDefintions, final VarContext globalVars, final VarContext pipelineVars, final StepContext stepContext) {
        this.pipelineExecutor = Objects.requireNonNull(pipelineExecutor, "Cannot have a null pipleline executor");
        this.pipelineDefintions = Objects.requireNonNull(pipelineDefintions, "Cannot have null pipleline definitions");
        this.globalVars = Objects.requireNonNull(globalVars, "Cannot have null global variables");
        this.pipelineVars = Objects.requireNonNull(pipelineVars, "Cannot have null pipeline variables");
        this.stepContext = Objects.requireNonNull(stepContext, "Cannot have a null step context");
        this.isDone = false;
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
    public VarContext getGlobalVars() {
        return globalVars;
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
    public StepContext getStepContext() {
        return stepContext;
    }
}
