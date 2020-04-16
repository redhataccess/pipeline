package com.redhat.step.decorator;

import com.redhat.step.AbstractStep;
import com.redhat.step.Step;

public abstract class AbstractDecoratorStep extends AbstractStep {

    private String name;
    private Step decoratee;

    public Step setDecoratee(final String name, final Step decoratee) {
        this.name = name;
        this.decoratee = decoratee;

        return decoratee;
    }

    public String getName() {
        return name;
    }

    public Step getDecoratee() {
        return decoratee;
    }

    protected AbstractDecoratorStep() {
    }
}
