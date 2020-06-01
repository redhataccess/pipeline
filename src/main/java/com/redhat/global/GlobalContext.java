package com.redhat.global;

import com.redhat.common.context.VarContext;

/**
 * Defines what a pipeline and steps will operate upon.
 *
 * @author sfloess
 */
public interface GlobalContext {
    /**
     * Return our global variables. These are variables shared across all pipeline instances.
     */
    VarContext getGlobalVars();
}
