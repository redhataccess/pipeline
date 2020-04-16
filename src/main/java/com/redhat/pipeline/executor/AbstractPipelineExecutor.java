package com.redhat.pipeline.executor;

import com.redhat.common.AbstractBase;
import com.redhat.common.utils.JsonUtils;
import com.redhat.common.utils.Strings;
import com.redhat.pipeline.DefaultPipeline;
import com.redhat.pipeline.Pipeline;
import com.redhat.pipeline.PipelineContext;
import com.redhat.pipeline.PipelineExecutor;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONObject;

/**
 *
 * @author sfloess
 *
 * Copyright © 2019 Red Hat, Inc.
 */
public class AbstractPipelineExecutor extends AbstractBase implements PipelineExecutor {
    /**
     * This will run <code>pipeline</code>.
     *
     * @param <R>      the type to return.
     *
     * @param pipeline the pipeline to run.
     * @param context  the argument to pass to the pipeline and all it's steps for processing.
     *
     * @return the result of the pipeline run.
     *
     * @throws Exception if any problems arise while processing <code>context</code>.
     */
    <R> R runPipeline(final Pipeline pipeline, final PipelineContext context) throws Exception {
        Objects.requireNonNull(pipeline, "Cannot run a null pipeline!");
        Objects.requireNonNull(context, "Cannot process a null context!");

        try {
            pipeline.preProcess(context);
            pipeline.process(context);
            pipeline.postProcess(context);
        } catch (final Exception exception) {
            logError("*** Trouble running pipeline ***", exception);

            pipeline.postProcessFailure(context, exception);

            throw exception;
        }

        return context.getResult();
    }

    /**
     * Default constructor.
     */
    protected AbstractPipelineExecutor() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R runMetaPipeline(JSONObject metaPipeline, PipelineContext context) throws Exception {
        Objects.requireNonNull(metaPipeline, "Cannot have a null meta pipeline!");
        Objects.requireNonNull(context, "Cannot have a null context!");

        return runPipeline(new DefaultPipeline(metaPipeline), context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R runPipeline(String name, PipelineContext context) throws Exception {
        Strings.require(name, "Cannot have an empty/blank/null name!");
        Objects.requireNonNull(context, "Cannot have a null context!");

        return runPipeline(context.getPipelineDefinitions().create(name), context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R runJsonPipeline(List<JSONObject> metaPipeline, PipelineContext context) throws Exception {
        Objects.requireNonNull(metaPipeline, "Cannot have a null meta steps!");
        Objects.requireNonNull(context, "Cannot have a null context!");

        return runPipeline(new DefaultPipeline(metaPipeline), context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R runMapPipeline(List<Map> metaPipeline, PipelineContext context) throws Exception {
        Objects.requireNonNull(metaPipeline, "Cannot have a null meta steps!");
        Objects.requireNonNull(context, "Cannot have a null context!");

        return runPipeline(new DefaultPipeline(JsonUtils.toJsonList(metaPipeline), false), context);
    }
}
