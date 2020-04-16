package com.redhat.step.definitions;

import com.redhat.common.AbstractJsonDefinitions;
import com.redhat.common.utils.JsonUtils;
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
    public Step create(final String name, final JSONObject initState) throws Exception {
        return (Step) JsonUtils.jsonObjectToObject(initState, getDefMap().get(ensureDefinitionName(name)));
    }
}
