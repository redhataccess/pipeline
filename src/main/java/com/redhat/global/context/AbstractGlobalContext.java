package com.redhat.global.context;

import com.redhat.common.AbstractBase;
import com.redhat.common.context.VarContext;
import com.redhat.global.GlobalContext;
import java.util.Objects;

/**
 * Abstract implementation of a PipelineContext.
 *
 * @author sfloess
 */
public abstract class AbstractGlobalContext extends AbstractBase implements GlobalContext {
    /**
     * All global variables.
     */
    private final VarContext globalVars;

    /**
     * Mutator constructor.
     */
    public AbstractGlobalContext(final VarContext globalVars) {
        this.globalVars = Objects.requireNonNull(globalVars, "Cannot have null global variables");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VarContext getGlobalVars() {
        return globalVars;
    }
}
