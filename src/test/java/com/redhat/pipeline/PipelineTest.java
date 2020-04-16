package com.redhat.pipeline;

import com.redhat.common.utils.JsonUtils;
import com.redhat.pipeline.context.DefaultPipelineContext;
import com.redhat.step.block.LoopStep;
import com.redhat.step.logging.LogMsgStep;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sfloess
 */
public class PipelineTest {
    PipelineContext pipelineContext;

    @Before
    public void init() {
        pipelineContext = new DefaultPipelineContext();

        pipelineContext.getStepContext().getStepDefinitions().define("loop", LoopStep.class);
        pipelineContext.getStepContext().getStepDefinitions().define("logMsg", LogMsgStep.class);

    }

    @Test
    public void test_loadYaml() throws Exception {
        final String yamlStr = FileUtils.readFileToString(new File("/home/sfloess/Development/redhat/scm/gitlab/cee/customer-platform/pipeline_redesign/src/test/resources/LoopPipeline.yaml"));
        final Map map = JsonUtils.createYamlMapper().readValue(yamlStr, HashMap.class);
        final JSONObject json = new JSONObject(map);
        System.out.println(json.toString(4));
    }

    @Test
    public void test_logStep() throws Exception {
        pipelineContext.getPipelineDefinitions().defineYamlPipeline("logTest", FileUtils.readFileToString(new File("/home/sfloess/Development/redhat/scm/gitlab/cee/customer-platform/pipeline_redesign/src/test/resources/LogPipeline.yaml")));

        System.out.println(pipelineContext.getPipelineDefinitions().getDefinition("logTest").toString(4));

        pipelineContext.getPipelineVars().set("name", "Flossy " + System.currentTimeMillis());

        pipelineContext.getPipelineExecutor().runPipeline("logTest", pipelineContext);

        System.out.println("Result of run: [" + pipelineContext.getResult() + "]");
    }

    @Test
    public void test_loopStep() throws Exception {
        pipelineContext.getPipelineDefinitions().defineYamlPipeline("loopTest", FileUtils.readFileToString(new File("/home/sfloess/Development/redhat/scm/gitlab/cee/customer-platform/pipeline_redesign/src/test/resources/LoopPipeline.yaml")));

        System.out.println(pipelineContext.getPipelineDefinitions().getDefinition("loopTest").toString(4));
        pipelineContext.getPipelineExecutor().runPipeline("loopTest", pipelineContext);

        System.out.println("Result of run: [" + pipelineContext.getResult() + "]");
    }

}
