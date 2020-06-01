package com.redhat.step;

import com.redhat.common.processor.executor.Executor;
import java.util.Map;
import java.util.Objects;
import org.json.JSONObject;

/**
 * Allows one to execute a step.
 *
 * @author sfloess
 */
public interface StepExecutor extends Executor<StepContext, Step> {
    /**
     * Executes a Step who is externalized as <code>metaStep</code>.
     *
     * @param context  the object to process.
     * @param metaStep the externalized form of a Step.
     *
     * @return the result of processing <code>context</code>.
     */
    default StepContext executeMetaStep(StepContext toProcess, JSONObject metaStep) {
        return toProcess;
    }

    /**
     * Executes a Step who is externalized as <code>metaStep</code>.
     *
     * @param context  the object to process.
     * @param metaStep the externalized form of a Step.
     *
     * @return the result of processing <code>context</code>.
     */
    default StepContext executeMetaStepMap(StepContext toProcess, Map metaStep) {
        return executeMetaStep(Objects.requireNonNull(toProcess, "Cannot have a null context!"), new JSONObject(Objects.requireNonNull(metaStep, "Cannot have a null map!")));
    }
}
