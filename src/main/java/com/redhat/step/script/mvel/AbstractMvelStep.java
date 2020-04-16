package com.redhat.step.script.mvel;

import com.redhat.pipeline.PipelineContext;
import com.redhat.step.script.AbstractScriptStep;
import java.util.Map;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;

/**
 * Base class for doing MVEL related work.
 *
 * @author sfloess
 */
public abstract class AbstractMvelStep extends AbstractScriptStep {

    protected AbstractMvelStep() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object executeScriptStatement(final PipelineContext context, final Map scriptVars, final String scriptStatement) throws Exception {
        return MVEL.executeExpression(MVEL.compileExpression(scriptStatement, new ParserContext()), scriptVars);
    }
}
