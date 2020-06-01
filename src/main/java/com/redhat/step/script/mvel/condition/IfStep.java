package com.redhat.step.script.mvel.condition;

import com.redhat.step.StepContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Acts as a basic if/else statement. While silly the then block can be empty
 *
 * @author sfloess
 */
public class IfStep extends AbstractMvelConditionStep {

    private List<Map> elseBlock;

    StepContext runBlock(final StepContext context, final List<Map> blockToRun, final String blockType) {
        context.getPipelineContext().getPipelineExecutor().executeMetaStepMaps(context.getPipelineContext(), blockToRun);

        return context;
    }

    StepContext runBlock(final StepContext context, final boolean testCondition) {
        return runBlock(context, testCondition ? getThen() : getElse(), testCondition ? "if" : "else");
    }

    public IfStep() {
        elseBlock = new ArrayList<>();
    }

    public void setThen(final List<Map> thenBlock) {
        setBlock(thenBlock);
    }

    public List<Map> getThen() {
        return getBlock();
    }

    public void setElse(final List<Map> elseBlock) {
        this.elseBlock = elseBlock;
    }

    public List<Map> getElse() {
        return elseBlock;
    }

    @Override
    public StepContext process(final StepContext context) {
        ensureCondition("Missing 'then' block for if element!");

        return runBlock(context, isConditionMet(executeScriptStatement(context, getCondition())));
    }
}
