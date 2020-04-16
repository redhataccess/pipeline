package com.redhat.common;

import java.util.List;

/**
 * When creating a definition, the following is required:
 * <ul>
 * <li>A name</li>
 * <li>An entity definition.Think of this like a string representing a pipeline or a class denoting a step impl</li>
 * </ul>
 * <p>
 * When creating an instance of a definition, the following is required:
 * <ul>
 * <li>The name from of a definition (denoted when creating a definition</li>
 * <li>An entity representing the intial state of the definition so the definition can be constructed</li>
 * </ul>
 * <p>
 *
 * @param <T> the type of definition.
 *
 * @author sfloess
 */
public interface JsonDefinitions<T> {
    /**
     * Retrieve all definitions.
     *
     * @return all definitions.
     */
    List<T> getDefinitions();

    /**
     * Retrieve a definition named <code>name</code>.
     *
     * @param name
     *
     * @return
     */
    T getDefinition(String name);

    /**
     * Remove a definition named <code>name</code>.
     *
     * @param name the name of the definition to removeDefinition.
     *
     * @return the removed definition.
     *
     * @throws Exception if any problems arise removing the definition.
     */
    T removeDefinition(String name) throws Exception;
}
