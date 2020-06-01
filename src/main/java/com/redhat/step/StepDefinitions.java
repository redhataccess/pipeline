package com.redhat.step;

import com.redhat.common.JsonDefinitions;
import org.json.JSONObject;

/**
 * Manages step definitions. Steps are defined by name and a backing class. To instantiate a step, a name is needed as
 * well as a backing JSON object. The class denoted for the name is used to construct a PipelineStep. This is created
 * using the JSONObject as a "serialized" form of the instance.
 *
 * @author sfloess
 */
public interface StepDefinitions extends JsonDefinitions<Class> {
    /**
     * Define a step definition whose backing class is klass.
     *
     * @param name  the name of the step.
     * @param klass the backing class for the step.
     */
    void define(final String name, final Class klass);

    /**
     * Using the definition named <code>name</code> and <code>initState</code> createDefinition a new result.
     *
     * @param name      the name of a previously defined definition.
     * @param initState an object used in creating the result.
     *
     * @return the a new instance of a result.
     *
     */
    Step create(String name, JSONObject initState);
}
