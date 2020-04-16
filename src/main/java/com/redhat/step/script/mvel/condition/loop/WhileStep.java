package com.redhat.step.script.mvel.condition.loop;

import com.redhat.pipeline.PipelineContext;
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
    public PipelineContext process(final PipelineContext context) throws Exception {
        ensureCondition();
        ensureBlock();

        while (!context.isDone() && isConditionMet(executeScriptStatement(context, getCondition()))) {
            try {
                context.getPipelineExecutor().runJsonPipeline(getBlock(), context);
            } catch (final Exception exception) {
                logError(exception, "Trouble running while loop's block for condition [", getCondition(), "].  Block = ", getBlock());

                throw exception;
            }
        }

        return context;
    }
}
