package com.redhat.step;

import com.redhat.common.AbstractJsonBase;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

/**
 * Abstract base class for a pipeline stage.
 *
 * @author sfloess
 */
public abstract class AbstractStep extends AbstractJsonBase implements Step {

    /**
     * Our name.
     */
    private String name;

    /**
     * Our description.
     */
    private String description;

    /**
     * Use this if you have a step where you may want to store something as a variable but the user does not provide a
     * name for the variable.
     */
    protected static String generateUniqueVariableName(final String prefix) {
        return StringUtils.join(prefix, UUID.randomUUID().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processUnmanagedProperty(final String name, final Object value) {
    }

    /**
     * Default constructor.
     */
    protected AbstractStep() {
        logIfDebug("Constructing step [" + getClass().getName() + "]");
        description = "";
    }

    /**
     * Set a name for the step.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Return the name for the step.
     */
    public String getName() {
        return name;
    }

    /**
     * Set a description for the step.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Return the description for the step.
     */
    public String getDescription() {
        return description;
    }
}
