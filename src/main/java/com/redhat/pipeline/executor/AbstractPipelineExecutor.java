package com.redhat.pipeline.executor;

import com.redhat.common.processor.executor.AbstractExecutor;
import com.redhat.common.utils.Strings;
import com.redhat.pipeline.Pipeline;
import com.redhat.pipeline.PipelineContext;
import com.redhat.pipeline.PipelineExecutor;
import com.redhat.pipeline.PipelineMarkupEnum;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author sfloess
 *
 * Copyright © 2019 Red Hat, Inc.
 */
public abstract class AbstractPipelineExecutor extends AbstractExecutor<PipelineContext, Pipeline> implements PipelineExecutor {
    /**
     * Default constructor.
     */
    protected AbstractPipelineExecutor() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PipelineContext doProcess(Pipeline processor, PipelineContext toProcess) {
        return executeMetaPipeline(toProcess, processor.asJsonObject());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PipelineContext executeMetaPipeline(final PipelineContext toProcess, final JSONObject metaPipeline) {
        Objects.requireNonNull(toProcess, "Cannot have a null object to process!");
        Objects.requireNonNull(metaPipeline, "Cannot have a null meta pipeline!");

        return executeMetaStepArray(toProcess, metaPipeline.getJSONArray(PipelineMarkupEnum.STEPS.getName()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PipelineContext executeMetaStepList(final PipelineContext toProcess, final List<JSONObject> metaSteps) {
        Objects.requireNonNull(toProcess, "Cannot have a null object to process!");
        Objects.requireNonNull(metaSteps, "Cannot have null meta steps!");

        for (final JSONObject metaStep : metaSteps) {
            toProcess.getStepContext().getStepExecutor().executeMetaStep(toProcess.getStepContext(), metaStep);
        }

        return toProcess;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PipelineContext executeMetaStepArray(final PipelineContext toProcess, final JSONArray metaSteps) {
        Objects.requireNonNull(toProcess, "Cannot have a null object to process!");
        Objects.requireNonNull(metaSteps, "Cannot have null meta steps!");

        for (int index = 0; index < metaSteps.length(); index++) {
            toProcess.getStepContext().getStepExecutor().executeMetaStep(toProcess.getStepContext(), metaSteps.getJSONObject(index));
        }

        return toProcess;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PipelineContext executeMetaStepMaps(final PipelineContext toProcess, final List<Map> metaSteps) {
        Objects.requireNonNull(toProcess, "Cannot have a null object to process!");
        Objects.requireNonNull(metaSteps, "Cannot have null meta steps!");

        for (final Map map : metaSteps) {
            toProcess.getStepContext().getStepExecutor().executeMetaStep(toProcess.getStepContext(), new JSONObject(map));
        }

        return toProcess;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PipelineContext executeNamed(final String name, final PipelineContext toProcess) {
        Strings.require(name, "Must provide a name of a pipeline to execute!");
        Objects.requireNonNull(toProcess, "Must provide a pipeline context to process!");

        return executeProcessor(toProcess.getPipelineDefinitions().create(name), toProcess);
    }
}
