package com.redhat.step.block;

import com.redhat.step.AbstractStep;
import com.redhat.step.StepContext;
import java.util.List;
import java.util.Map;

public abstract class AbstractBlockStep extends AbstractStep {

    private List<Map> block;

    protected AbstractBlockStep() {
    }

    public List<Map> getBlock() {
        return block;
    }

    public void setBlock(final List<Map> block) {
        this.block = block;
    }

    @Override
    public StepContext process(final StepContext context) {
        try {
            context.getPipelineContext().getPipelineExecutor().executeMetaStepMaps(context.getPipelineContext(), getBlock());
        } catch (final Exception exception) {
            logError(exception, "Trouble running block!");

            throw exception;
        }

        return context;
    }
}
