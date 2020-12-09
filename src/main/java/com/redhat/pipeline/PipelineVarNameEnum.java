package com.redhat.pipeline;

import com.redhat.common.context.VarContext;
import com.redhat.step.StepContext;

/**
 *
 * @author sfloess
 */
public enum PipelineVarNameEnum {
    QUERY_PARAMS("queryParams"),
    PAYLOAD("payload");

    private final String varName;

    private PipelineVarNameEnum(final String varName) {
        this.varName = varName;
    }

    public String getVarName() {
        return varName;
    }

    public <T> T getVar(final VarContext context, final T defaultValue) {
        return context.get(getVarName(), defaultValue);
    }

    public <T> T getPipelineVar(final PipelineContext context, final T defaultValue) {
        return getVar(context.getPipelineVars(), defaultValue);
    }

    public <T> T getPipelineVar(final StepContext context, final T defaultValue) {
        return PipelineVarNameEnum.this.getPipelineVar(context.getPipelineContext(), defaultValue);
    }

    public <T> T getVar(final VarContext context) {
        return context.get(getVarName());
    }

    public <T> T getPipelineVar(final PipelineContext context) {
        return getVar(context.getPipelineVars());
    }

    public <T> T getPipelineVar(final StepContext context) {
        return PipelineVarNameEnum.this.getPipelineVar(context.getPipelineContext());
    }

    public VarContext setVar(final VarContext context, final Object value) {
        context.set(getVarName(), value);

        return context;
    }

    public PipelineContext setPipelineVar(final PipelineContext context, final Object value) {
        setVar(context.getPipelineVars(), value);

        return context;
    }

    public StepContext setPipelineVar(final StepContext context, final Object value) {
        setPipelineVar(context.getPipelineContext(), value);

        return context;
    }

}
