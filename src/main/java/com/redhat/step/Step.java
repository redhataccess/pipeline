package com.redhat.step;

import com.redhat.common.processor.Processor;

/**
 * Interface for defining a pipeline step. Please note a step may be a pipeline step or a pipeline itself.
 *
 * @author sfloess
 */
public interface Step extends Processor<StepContext> {
}
