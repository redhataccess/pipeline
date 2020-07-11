package com.redhat.pipeline.jee.ejb;

import com.redhat.global.GlobalContext;
import com.redhat.global.context.DefaultGlobalContext;
import com.redhat.pipeline.PipelineDefinitions;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;

/**
 * Default implementation - albeit a very naive version.
 *
 * @author sfloess
 */
public class DefaultPipelineSvcSingleton extends AbstractPipelineSvcSingleton {
    private GlobalContext globalContext;
    private Map<String, PipelineDefinitions> nameSpace;

    @PostConstruct
    protected void init() {
        super.init();

        nameSpace = new ConcurrentHashMap<>();
        globalContext = new DefaultGlobalContext();

        logInfo("Constructed and ready");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GlobalContext getGlobalContext() {
        return globalContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, PipelineDefinitions> getNameSpace() {
        return nameSpace;
    }
}
