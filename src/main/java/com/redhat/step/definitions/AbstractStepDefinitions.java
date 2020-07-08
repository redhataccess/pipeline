package com.redhat.step.definitions;

import com.redhat.common.AbstractJsonDefinitions;
import com.redhat.common.json.utils.JsonUtils;
import com.redhat.step.Step;
import com.redhat.step.StepDefinitions;
import java.util.Objects;
import org.json.JSONObject;

/**
 * Abstract base class for step definitions.
 *
 * @author sfloess
 */
public abstract class AbstractStepDefinitions extends AbstractJsonDefinitions<Class> implements StepDefinitions {

    /**
     * Creates an actual Step whose name is name.
     *
     * @param name      the name of the step (not class name).
     * @param initState the initial state of the step when constructed.
     * @param stepClass the Step's class impl.
     *
     * @return a Step.
     */
    Step create(final String name, final JSONObject initState, final Class<Step> stepClass) {
        return JsonUtils.jsonObjectToObject(initState, Objects.requireNonNull(stepClass, "No step entitled [" + name + "]"));
    }

    /**
     * Default constructor.
     */
    protected AbstractStepDefinitions() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void define(String name, Class klass) {
        getDefMap().put(ensureDefinitionName(name), Objects.requireNonNull(klass, "Must providing a backing classname for a step!"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Step create(final String name, final JSONObject initState) {
        return create(ensureDefinitionName(name), initState, getDefMap().get(ensureDefinitionName(name)));
    }
}
