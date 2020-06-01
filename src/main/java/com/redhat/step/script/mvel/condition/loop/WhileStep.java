package com.redhat.step.script.mvel.condition.loop;

import com.redhat.step.StepContext;
import com.redhat.step.script.mvel.condition.AbstractMvelConditionStep;

/**
 * Acts as a basic while loop.
 *
 * @author sfloess
 */
public class WhileStep extends AbstractMvelConditionStep {

    public WhileStep() {
    }

    @Override
    public StepContext process(final StepContext context) {
        ensureCondition();
        ensureBlock();

        while (!context.getPipelineContext().isDone() && isConditionMet(executeScriptStatement(context, getCondition()))) {
            context.getPipelineContext().getPipelineExecutor().executeMetaStepMaps(context.getPipelineContext(), getBlock());
        }

        return context;
    }
}
