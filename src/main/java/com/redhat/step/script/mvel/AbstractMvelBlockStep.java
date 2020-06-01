package com.redhat.step.script.mvel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractMvelBlockStep extends AbstractMvelStep {

    private List<Map> block;

    protected void ensureBlock() {
        if (null == getBlock()) {
            logError("Block cannot be missing!");

            throw new IllegalArgumentException("Missing block!");
        }
    }

    protected AbstractMvelBlockStep() {
    }

    public void setBlock(final List<Map> block) {
        this.block = block;
    }

    public List<Map> getBlock() {
        return new ArrayList<>(block);
    }
}
