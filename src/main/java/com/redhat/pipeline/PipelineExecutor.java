package com.redhat.pipeline;

import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * Allows one to run a pipeline.
 *
 * @author sfloess
 */
public interface PipelineExecutor {
    /**
     * Run a pipeline as denoted in the meta format <code>metaPipeline</code>.
     *
     * @param <R>          the type of the result of a pipeline run.
     *
     * @param metaPipeline the meta version of a pipeline to execute.
     * @param context      the object to use when running a pipeline.
     *
     * @return the result of a pipeline run.
     *
     * @throws java.lang.Exception if any problems arise running the pipeline entitled <code>name</code> to process <code>context</code>.
     */
    <R> R runMetaPipeline(JSONObject metaPipeline, PipelineContext context) throws Exception;

    /**
     * Run a pipeline and return the result.
     *
     * @param <R>     the type of the result of a pipeline run.
     *
     * @param name    the name of the pipeline to execute.
     * @param context the object to use when running a pipeline.
     *
     * @return the result of a pipeline run.
     *
     * @throws java.lang.Exception if any problems arise running the pipeline entitled <code>name</code> to process <code>context</code>.
     */
    <R> R runPipeline(String name, PipelineContext context) throws Exception;

    /**
     * Run a pipeline as denoted in the meta format <code>metaPipeline</code>. What we will do is run all the JSONObjects in metaPipeline as steps.
     * Think of it as an anonymous pipeline (no name).
     *
     * @param <R>          the type of the result of a pipeline run.
     *
     * @param metaPipeline the meta version of a pipeline to execute.
     * @param context      the object to use when running a pipeline.
     *
     * @return the result of a pipeline run.
     *
     * @throws java.lang.Exception if any problems arise running the pipeline entitled <code>name</code> to process <code>context</code>.
     */
    <R> R runJsonPipeline(List<JSONObject> metaPipeline, PipelineContext context) throws Exception;

    /**
     * Run a pipeline as denoted in the meta format <code>metaPipeline</code>. What we will do is run all the JSONObjects in metaPipeline as steps.
     * Think of it as an anonymous pipeline (no name).
     *
     * @param <R>          the type of the result of a pipeline run.
     *
     * @param metaPipeline the meta version of a pipeline to execute.
     * @param context      the object to use when running a pipeline.
     *
     * @return the result of a pipeline run.
     *
     * @throws java.lang.Exception if any problems arise running the pipeline entitled <code>name</code> to process <code>context</code>.
     */
    <R> R runMapPipeline(List<Map> metaPipeline, PipelineContext context) throws Exception;
}
