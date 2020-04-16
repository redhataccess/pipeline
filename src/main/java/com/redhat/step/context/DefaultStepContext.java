package com.redhat.step.context;

import com.redhat.common.context.DefaultVarContext;
import com.redhat.common.context.VarContext;
import com.redhat.step.StepDefinitions;
import com.redhat.step.StepPreprocessor;
import com.redhat.step.VariableExpansionStepPreprocessor;
import com.redhat.step.definitions.DefaultStepDefinitions;

/**
 * Default implementation of a StepContext.
 *
 * @author sfloess
 */
public class DefaultStepContext extends AbstractStepContext {
    public DefaultStepContext(final StepDefinitions stepDefinitions, final StepPreprocessor stepPreprocessor, final VarContext stepVars) {
        super(stepDefinitions, stepPreprocessor, stepVars);
    }

    public DefaultStepContext() {
        this(new DefaultStepDefinitions(), new VariableExpansionStepPreprocessor(), new DefaultVarContext());
    }
}
