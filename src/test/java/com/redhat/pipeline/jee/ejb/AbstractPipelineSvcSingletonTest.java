package com.redhat.pipeline.jee.ejb;

import com.redhat.pipeline.PipelineDefinitions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test the PipelineSvcSingleton
 *
 * @author sfloess
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractPipelineSvcSingletonTest {
    DefaultPipelineSvcSingleton svc;

    @Mock
    PipelineDefinitions pipelineDefinitions;

    @Before
    public void setup() {
        svc = new DefaultPipelineSvcSingleton();

        svc.init();
    }

    @Test
    public void test_isPipelinePresent_PipelineDefinitions_String() {
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent((PipelineDefinitions) null, "foo"));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent(pipelineDefinitions, null));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent(pipelineDefinitions, ""));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent(pipelineDefinitions, "   "));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent(pipelineDefinitions, "" + System.currentTimeMillis()));

        Mockito.when(pipelineDefinitions.getDefinition(Mockito.anyString())).thenReturn(new JSONObject());

        Assert.assertTrue("Should have a pipeline present", svc.isPipelinePresent(pipelineDefinitions, "123"));
        Mockito.verify(pipelineDefinitions, Mockito.times(1)).getDefinition("123");
    }

    @Test
    public void test_isPipelinePresent_String_String() throws Exception {
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent((String) null, "foo"));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent((String) null, null));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent("", "foo"));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent("", ""));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent(" ", ""));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent("", " "));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent(" ", " "));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent("alpha", ""));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent("beta", "theta"));
        Assert.assertFalse("Pipeline should not be present", svc.isPipelinePresent("beta", "theta"));

        Assert.assertEquals("Should be no namespaces", 0, svc.getTotalNameSpaces());

        svc.definePipeline("foo", "bar", "---\n\"alpha\":  \"beta\"");

        Assert.assertTrue("Pipeline should be present", svc.isPipelinePresent("foo", "bar"));

        Assert.assertEquals("Should be one namespaces", 1, svc.getTotalNameSpaces());
    }

    @Test
    public void test_getNameSpace() {
        Assert.assertNotNull("Should have a namespace collection!", svc.getNameSpaceCopy());
    }
}
