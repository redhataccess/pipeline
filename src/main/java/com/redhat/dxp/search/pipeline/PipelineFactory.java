package com.redhat.dxp.search.pipeline;

/**
 * The PipelineFactory is responsible for creating a pipeline from its yaml representation.
 *
 * The pipeline is laid out like this:
 *
 * [source,yaml]
 * --
 * ---
 * steps:
 *   - step: splitNumbers
 *     description: This will split all the numbers into an array
 *     target: numbers
 *   - step: nameSum
 *     description: this adds up the numbers found in the array addends
 *     args:
 *        addends: “${{numbers}}”
 *     target: result
 * --
 *
 * Each step has a short name which identifies a unique implementation within the implementation.
 * The name is dereferenced to an actual instance of the class within the {@link StepFactory}.
 *
 */
public interface PipelineFactory {

    /**
     * Return a pipeline from a YAML declaration.  The value may be new or cached.
     *
     * @param yamlPipeline A set of steps defined in yaml like the example at the top of this class
     * @return A Pipeline instance for the given string
     */
    Pipeline getPipeline(String yamlPipeline);
}
