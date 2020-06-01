package com.redhat.step.script.mvel.condition.loop;

import com.redhat.step.StepContext;
import com.redhat.step.script.mvel.condition.AbstractMvelConditionStep;

/**
 * Acts as a basic do-while loop.
 *
 * @author sfloess
 */
public class DoWhileStep extends AbstractMvelConditionStep {

    public DoWhileStep() {
    }

    public String getWhile() {
        return super.getCondition();
    }

    public void setWhile(final String condition) {
        super.setCondition(condition);
    }

    @Override
    public StepContext process(final StepContext context) {
        ensureCondition("Missing while element!");
        ensureBlock();

        do {
            logInfo("Step vars:      ", context.getStepVars().asMap());
            logInfo("Pipeline vars:  ", context.getPipelineContext().getPipelineVars().asMap());
            logInfo("Global vars:    ", context.getPipelineContext().getGlobalContext().getGlobalVars().asMap(), "\n\n\n\n\n");

            context.getPipelineContext().getPipelineExecutor().executeMetaStepMaps(context.getPipelineContext(), getBlock());
        } while (!context.getPipelineContext().isDone() && isConditionMet(executeScriptStatement(context, getWhile())));

        return context;
    }
}
