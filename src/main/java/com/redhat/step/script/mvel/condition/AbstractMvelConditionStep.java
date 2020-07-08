package com.redhat.step.script.mvel.condition;

import com.redhat.step.script.mvel.AbstractMvelBlockStep;
import org.apache.commons.lang3.StringUtils;

/**
 * Abstract base class for MVEL conditionals.
 *
 * @author sfloess
 */
public abstract class AbstractMvelConditionStep extends AbstractMvelBlockStep {

    private String condition;

    protected void ensureCondition(final String errorMsg) {
        if (StringUtils.isBlank(condition)) {
            logError(errorMsg);

            throw new IllegalArgumentException(errorMsg);
        }
    }

    protected void ensureCondition() {
        ensureCondition("Missing condition!");
    }

    protected boolean isConditionMet(final String conditionStr) {
        return !StringUtils.isAllBlank(conditionStr) && ("true".equalsIgnoreCase(conditionStr) || "1".equals(conditionStr));
    }

    protected boolean isConditionMet(final Object condition) {
        return null != condition && isConditionMet(condition.toString());
    }

    protected AbstractMvelConditionStep() {
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(final String condition) {
        this.condition = condition;
    }
}
