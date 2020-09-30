package com.redhat.pipeline.jee.ejb;

import com.redhat.pipeline.PipelineDefinitions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test the PipelineSvcSingleton
 *
 * @author sfloess
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultPipelineSvcSingletonTest {
    DefaultPipelineSvcSingleton svc;

    @Mock
    PipelineDefinitions pipelineDefinitions;

    @Before
    public void setup() {
        svc = new DefaultPipelineSvcSingleton();

        svc.init();
    }

    @Test
    public void test_getNameSpace() {
        Assert.assertNotNull("Should have a context!", svc.getNameSpace());
    }

    @Test
    public void test_getGlobalContext() {
        Assert.assertNotNull("Should have a context!", svc.getGlobalContext());
    }

    @Test
    public void test_getPipelineExecutor() {
        Assert.assertNotNull("Should have an executor!", svc.getPipelineExecutor());
    }

    @Test
    public void test_createPipelineVars() {
        Assert.assertNotNull("Should have created pipeline vars!", svc.createPipelineVars());
    }

    @Test
    public void test_createPipelineDefinitions() {
        Assert.assertNotNull("Should have created pipeline definitions!", svc.createPipelineDefinitions());
    }

    @Test
    public void test_createStepContext() {
        Assert.assertNotNull("Should have created a context!", svc.createStepContext());
    }
}
