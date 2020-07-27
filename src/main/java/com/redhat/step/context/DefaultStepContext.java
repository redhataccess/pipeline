package com.redhat.step.context;

import com.redhat.common.context.DefaultVarContext;
import com.redhat.common.context.VarContext;
import com.redhat.pipeline.PipelineContext;
import com.redhat.step.StepDefinitions;
import com.redhat.step.StepExecutor;
import com.redhat.step.StepPreprocessor;
import com.redhat.step.VariableExpansionStepPreprocessor;
import com.redhat.step.definitions.DefaultStepDefinitions;
import com.redhat.step.executor.DefaultStepExecutor;
import java.util.Objects;

/**
 * Default implementation of a StepContext.
 *
 * @author sfloess
 */
public class DefaultStepContext extends AbstractStepContext {
    private PipelineContext pipelineContext;

    public DefaultStepContext(final StepExecutor stepExecutor, final StepDefinitions stepDefinitions, final StepPreprocessor stepPreprocessor, final VarContext stepVars, final PipelineContext pipelineContext) {
        super(stepExecutor, stepDefinitions, stepPreprocessor, stepVars);

        this.pipelineContext = Objects.requireNonNull(pipelineContext, "Cannot have null pipeline contexts");
    }

    public DefaultStepContext(final PipelineContext pipelineContext) {
        this(new DefaultStepExecutor(), new DefaultStepDefinitions(), new VariableExpansionStepPreprocessor(), new DefaultVarContext(), pipelineContext);
    }

    public DefaultStepContext() {
        super(new DefaultStepExecutor(), new DefaultStepDefinitions(), new VariableExpansionStepPreprocessor(), new DefaultVarContext());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PipelineContext getPipelineContext() {
        return pipelineContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPipelineContext(PipelineContext context) {
        this.pipelineContext = Objects.requireNonNull(pipelineContext, "Cannot have null pipeline contexts");
    }
}
