package com.redhat.step.script.mvel.condition;

import com.redhat.pipeline.PipelineContext;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 * Acts as a basic if/else statement. While silly the then block can be empty
 *
 * @author sfloess
 */
public class IfStep extends AbstractMvelConditionStep {

    private List<JSONObject> elseBlock;

    void runBlock(final PipelineContext context, final List<JSONObject> blockToRun, final String blockType) throws Exception {
        try {
            context.getPipelineExecutor().runJsonPipeline(blockToRun, context);
        } catch (final Exception exception) {
            logError(exception, "Trouble running [", blockType, "]");

            throw exception;
        }
    }

    void runBlock(final PipelineContext context, final boolean testCondition) throws Exception {
        runBlock(context, testCondition ? getThen() : getElse(), testCondition ? "if" : "else");
    }

    public IfStep() {
        elseBlock = new ArrayList<>();
    }

    public void setThen(final List<JSONObject> thenBlock) {
        setBlock(thenBlock);
    }

    public List<JSONObject> getThen() {
        return getBlock();
    }

    public void setElse(final List<JSONObject> elseBlock) {
        this.elseBlock = elseBlock;
    }

    public List<JSONObject> getElse() {
        return elseBlock;
    }

    @Override
    public PipelineContext process(final PipelineContext context) throws Exception {
        ensureCondition("Missing 'then' block for if element!");

        runBlock(context, isConditionMet(executeScriptStatement(context, getCondition())));

        return context;
    }
}
