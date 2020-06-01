package com.redhat.global.context;

import com.redhat.common.context.DefaultVarContext;
import com.redhat.common.context.VarContext;
import com.redhat.step.StepContext;

/**
 * Default implementation of a pipeline context.
 *
 * @author sfloess
 */
public class DefaultGlobalContext extends AbstractGlobalContext {
    public DefaultGlobalContext(final VarContext globalVars) {
        super(globalVars);
    }

    public DefaultGlobalContext() {
        super(new DefaultVarContext());
    }
}