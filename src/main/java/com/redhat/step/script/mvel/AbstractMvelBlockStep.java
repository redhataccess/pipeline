package com.redhat.step.script.mvel;

import java.util.List;
import org.json.JSONObject;

public abstract class AbstractMvelBlockStep extends AbstractMvelStep {

    private List<JSONObject> block;

    protected void ensureBlock() {
        if (null == getBlock()) {
            logError("Block cannot be missing!");

            throw new IllegalArgumentException("Missing block!");
        }
    }

    protected AbstractMvelBlockStep() {
    }

    public void setBlock(final List<JSONObject> block) {
        this.block = block;
    }

    public List<JSONObject> getBlock() {
        return block;
    }
}
