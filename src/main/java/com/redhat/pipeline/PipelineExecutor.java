package com.redhat.pipeline;

import com.redhat.common.processor.executor.Executor;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Allows one to run a pipeline.
 *
 * @author sfloess
 */
public interface PipelineExecutor extends Executor<PipelineContext, Pipeline> {
    /**
     * <code>processor</code> will be used to iterate across <code>metaSteps</code> to process <code>toProcess</code>.
     *
     * @param toProcess    the thing to process.
     * @param metaPipeline contains the steps to run.
     *
     * @return the result of processing.
     */
    default PipelineContext executeMetaPipeline(PipelineContext toProcess, JSONObject metaPipeline) {
        return toProcess;
    }

    /**
     * <code>processor</code> will be used to iterate across <code>metaSteps</code> to process <code>toProcess</code>.
     *
     * @param toProcess the thing to process.
     * @param metaSteps contains the JSONObjects to process.
     *
     * @return the result of processing.
     */
    default PipelineContext executeMetaStepList(PipelineContext toProcess, List<JSONObject> metaSteps) {
        return toProcess;
    }

    /**
     * Execute against metaPipeline.
     * g.
     *
     * @param toProcess the thing to process.
     * @param metaSteps Contains
     *
     * @return the result of processing.
     */
    default PipelineContext executeMetaStepArray(PipelineContext toProcess, JSONArray metaSteps) {
        return toProcess;
    }

    /**
     * Execute against metaPipeline.
     *
     * @param toProcess the thing to process.
     * @param metaSteps contains the JSONObject to process.
     *
     * @return the result of processing.
     */
    default PipelineContext executeMetaStepMaps(PipelineContext toProcess, List<Map> metaSteps) {
        return toProcess;
    }

    /**
     * Run a pipeline whose name is <code>name</code> and return the result.
     *
     * @param name      the name of the pipeline to execute.
     * @param toProcess the object to use when running a pipeline.
     *
     * @return the result of a pipeline run.
     *
     */
    default PipelineContext executeNamed(String name, PipelineContext toProcess) {
        return toProcess;
    }
}
