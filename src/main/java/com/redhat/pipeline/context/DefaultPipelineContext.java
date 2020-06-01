package com.redhat.pipeline.context;

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
import java.util.Objects;

/**
 * Default implementation of a pipeline context.
 *
 * @author sfloess
 */
public class DefaultPipelineContext extends AbstractPipelineContext {

    /**
     * When executing a step in a pipeline, this is related to step processing.
     */
    private final StepContext stepContext;

    public DefaultPipelineContext(final PipelineExecutor pipelineExecutor, final PipelineDefinitions pipelineDefintions, final VarContext pipelineVars, final GlobalContext globalContext, final StepContext stepContext) {
        super(pipelineExecutor, pipelineDefintions, pipelineVars, globalContext);

        this.stepContext = Objects.requireNonNull(stepContext, "Cannot have a null step context");
    }

    public DefaultPipelineContext(final GlobalContext globalContext) {
        super(new DefaultPipelineExecutor(), new DefaultPipelineDefinitions(), new DefaultVarContext(), globalContext);

        this.stepContext = new DefaultStepContext(this);
    }

    public DefaultPipelineContext() {
        this(new DefaultGlobalContext());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StepContext getStepContext() {
        return stepContext;
    }
}
