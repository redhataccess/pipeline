package com.redhat.pipeline.jee.rest.proxy;

import com.redhat.common.jee.rest.proxy.AbstractResourceProxy;
import static com.redhat.common.jee.rest.utils.ResponseBuilder.CREATED;
import static com.redhat.common.jee.rest.utils.ResponseBuilder.INTERNAL_SERVER_ERROR;
import static com.redhat.common.jee.rest.utils.ResponseBuilder.NOT_FOUND;
import static com.redhat.common.jee.rest.utils.ResponseBuilder.OK;
import com.redhat.pipeline.jee.ejb.PipelineSvcSingleton;
import com.redhat.pipeline.jee.rest.AbstractPipelineResource;
import java.util.function.Supplier;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author sfloess
 */
public class AbstractPipelineResourceProxy extends AbstractResourceProxy<PipelineSvcSingleton> implements AbstractPipelineResource {
    public static final String PIPELINE_MESSAGE_HEADER_NAME = "Pipeline-Message";
    public static final String PIPELINE_CODE_HEADER_NAME = "Pipeline-Code";

    /**
     * {@inheritDoc}
     */
    @Inject
    void injectService(final PipelineSvcSingleton service) {
        setService(service);
    }

    /**
     * Allow code to supply calling service code. If an exception is raised, this method will deal with it.
     */
    protected <S extends Supplier<Response>> Response callService(final S toCall) {
        try {
            return toCall.get();
        } catch (final Throwable failure) {
            logError(failure);

            return INTERNAL_SERVER_ERROR.buildResponse(
                    u -> u.header(PIPELINE_MESSAGE_HEADER_NAME, failure.getMessage()).
                            header(PIPELINE_CODE_HEADER_NAME, INTERNAL_SERVER_ERROR.getHttpStatus().getStatusCode())
            );
        }
    }

    /**
     * Do we have a pipeline?
     */
    boolean isPipelineFound(final String namespace, final String pipelineName) {
        return null != getService().getPipeline(namespace, pipelineName);
    }

    /**
     * Convenience method for denoting a pipeline is not found.
     */
    Response notFound(final String namespace, final String pipelineName) {
        return NOT_FOUND.buildResponse(u -> u.entity(StringUtils.join("Cannot retrieve pipeline [", pipelineName, "] - not found!")));
    }

    /**
     * Default constructor.
     */
    public AbstractPipelineResourceProxy() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response definePipeline(final String namespace, final String name, final String pipelineDef) {
        return callService(() -> {
            getService().definePipeline(namespace, name, pipelineDef);
            return CREATED.buildResponse(u -> u.entity(name));
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response retrievePipeline(final String namespace, final String pipelineName) {
        return callService(() -> {
            return isPipelineFound(namespace, pipelineName)
                    ? OK.buildResponse(u -> u.entity(getService().getPipeline(namespace, pipelineName)))
                    : notFound(namespace, pipelineName);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response retrievePipelines(final String namespace) {
        return callService(() -> {
            return OK.buildResponse(u -> u.entity(getService().getPipelines(namespace)));
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response deletePipeline(final String namespace, final String pipelineName) {
        return callService(() -> {
            return isPipelineFound(namespace, pipelineName)
                    ? OK.buildResponse(u -> u.entity(getService().removePipeline(namespace, pipelineName)))
                    : notFound(namespace, pipelineName);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response runPipelineWithPayload(final String namespace, final String pipelineName, final Object payload) {
        return callService(() -> {
            return isPipelineFound(namespace, pipelineName)
                    ? OK.buildResponse(u -> u.entity(getService().runPipeline(namespace, pipelineName, getQueryParams(), payload)))
                    : notFound(namespace, pipelineName);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response runPipelineNoPayload(final String namespace, final String pipelineName) {
        return callService(() -> {
            return isPipelineFound(namespace, pipelineName)
                    ? OK.buildResponse(u -> u.entity(getService().runPipeline(namespace, pipelineName, getQueryParams())))
                    : notFound(namespace, pipelineName);
        });
    }
}
