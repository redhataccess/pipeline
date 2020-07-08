package com.redhat.pipeline.jee.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Pipeline resource
 *
 * @author sfloess
 */
public interface AbstractPipelineResource {

    @PUT
    @Path("/define/{namespace:.+}/{pipeline:.+}")
    @Consumes({"application/yml"})
    Response definePipeline(@PathParam("namespace") String namespace, @PathParam("pipeline") String pipeline, String pipelineDef);

    @GET
    @Path("/define/{namespace:.+}/{pipeline:.+}")
    @Consumes({"application/yml"})
    Response retrievePipeline(@PathParam("namespace") String namespace, @PathParam("pipeline") String name);

    @GET
    @Path("/define/{namespace:.+}")
    @Consumes({"application/yml"})
    Response retrievePipelines(@PathParam("namespace") String namespace);

    @DELETE
    @Path("/define/{namespace:.+}/{pipeline:.+}")
    Response deletePipeline(@PathParam("namespace") String namespace, @PathParam("pipeline") String name);

    @POST
    @Path("/execute/{namespace:.+}/{pipeline:.+}")
    Response runPipelineWithPayload(@PathParam("namespace") String namespace, @PathParam("pipeline") String name, Object payload);

    @GET
    @Path("/execute/{namespace:.+}/{pipeline:.+}")
    Response runPipelineNoPayload(@PathParam("namespace") String namespace, @PathParam("pipeline") String name);
}
