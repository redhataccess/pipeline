package com.redhat.pipeline;

import com.redhat.common.context.VarContext;
import com.redhat.global.GlobalContext;
import com.redhat.step.StepContext;

/**
 * Defines what a pipeline and steps will operate upon.
 *
 * @author sfloess
 */
public interface PipelineContext {
    /**
     * The pipeline executor.
     *
     * @return the pipeline executor (the thing that runs pipelines).
     */
    public PipelineExecutor getPipelineExecutor();

    /**
     * Return our pipeline definitions.
     *
     * @return
     */
    PipelineDefinitions getPipelineDefinitions();

    /**
     * Return our pipeline variables. These are shared variables across a pipeline run/instance.
     */
    VarContext getPipelineVars();

    /**
     * Store the result of a pipeline run.
     *
     * @param result the result of a pipeline run.
     */
    public <T> void setResult(T result);

    /**
     * The result of a pipeline run.
     *
     * @param <T>
     *
     * @return
     */
    public <T> T getResult();

    /**
     * To stop a pipelinne running, set this to true.
     *
     * @param isDone
     */
    public void setIsDone(boolean isDone);

    /**
     * Is the pipeline done executing?
     *
     * @param isDone
     */
    boolean isDone();

    /**
     * Return the global context.
     */
    GlobalContext getGlobalContext();

    /**
     * Return the step context.
     *
     * @return
     */
    StepContext getStepContext();
}
