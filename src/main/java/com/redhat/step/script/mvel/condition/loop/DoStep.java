package com.redhat.step.script.mvel.condition.loop;

import com.redhat.pipeline.PipelineContext;
import com.redhat.step.script.mvel.condition.AbstractMvelConditionStep;

/**
 * Acts as a basic do-while loop.
 *
 * @author sfloess
 */
public class DoStep extends AbstractMvelConditionStep {

    public DoStep() {
    }

    public String getWhile() {
        return super.getCondition();
    }

    public void setWhile(final String condition) {
        super.setCondition(condition);
    }

    @Override
    public PipelineContext process(final PipelineContext context) throws Exception {
        ensureCondition("Missing while element!");
        ensureBlock();

        do {
            try {
                context.getPipelineExecutor().runJsonPipeline(getBlock(), context);
            } catch (final Exception exception) {
                logError(exception, "Trouble running do-while loop's block for condition [", getCondition(), "].  Block = ", getBlock());

                throw exception;
            }
        } while (!context.isDone() && isConditionMet(executeScriptStatement(context, getCondition())));

        return context;
    }
}
