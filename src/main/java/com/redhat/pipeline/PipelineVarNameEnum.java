package com.redhat.pipeline;

import com.redhat.common.context.VarContext;

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

    public <T> T getVar(final VarContext context) {
        return context.get(getVarName());
    }

    public VarContext setVar(final VarContext context, final Object value) {
        context.set(getVarName(), value);

        return context;
    }

    public PipelineContext setVar(final PipelineContext context, final Object value) {
        setVar(context.getPipelineVars(), value);

        return context;
    }
}
