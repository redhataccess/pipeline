package com.redhat.pipeline.context;

import com.redhat.common.context.DefaultVarContext;
import com.redhat.common.context.VarContext;
import com.redhat.pipeline.PipelineDefinitions;
import com.redhat.pipeline.PipelineExecutor;
import com.redhat.pipeline.definitions.DefaultPipelineDefinitions;
import com.redhat.pipeline.executor.DefaultPipelineExecutor;
import com.redhat.step.context.DefaultStepContext;
import com.redhat.step.StepContext;

/**
 * Default implementation of a pipeline context.
 *
 * @author sfloess
 */
public class DefaultPipelineContext extends AbstractPipelineContext {
    public DefaultPipelineContext(PipelineExecutor pipelineExecutor, PipelineDefinitions pipelineDefintions, final VarContext globalVars, final VarContext pipelineVars, StepContext stepContext) {
        super(pipelineExecutor, pipelineDefintions, globalVars, pipelineVars, stepContext);
    }

    public DefaultPipelineContext() {
        this(new DefaultPipelineExecutor(), new DefaultPipelineDefinitions(), new DefaultVarContext(), new DefaultVarContext(), new DefaultStepContext());
    }
}
