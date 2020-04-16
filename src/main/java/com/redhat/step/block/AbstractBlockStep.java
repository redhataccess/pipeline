package com.redhat.step.block;

import com.redhat.pipeline.PipelineContext;
import com.redhat.step.AbstractStep;
import java.util.List;
import java.util.Map;

public abstract class AbstractBlockStep extends AbstractStep {

    private Map block;

    protected AbstractBlockStep() {
    }

    public Map getBlock() {
        return block;
    }

    public void setBlock(final Map block) {
        this.block = block;
    }

    @Override
    public PipelineContext process(final PipelineContext context) throws Exception {
        try {
            context.getPipelineExecutor().runMapPipeline((List<Map>) getBlock().get("steps"), context);
        } catch (final Exception exception) {
            logError(exception, "Trouble running block!");

            throw exception;
        }

        return context;
    }
}
