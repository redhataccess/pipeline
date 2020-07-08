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

    public void setVar(final VarContext context, final Object value) {
        context.set(getVarName(), value);
    }

    public void setVar(final PipelineContext context, final Object value) {
        setVar(context.getPipelineVars(), value);
    }
}
