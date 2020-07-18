package com.redhat.pipeline;

import static com.redhat.common.markup.MarkupBuilder.YAML;
import com.redhat.global.GlobalContext;
import com.redhat.global.context.DefaultGlobalContext;
import com.redhat.pipeline.context.DefaultPipelineContext;
import com.redhat.step.ResultStep;
import com.redhat.step.block.LoopStep;
import com.redhat.step.logging.LogContextStep;
import com.redhat.step.logging.LogMsgStep;
import com.redhat.step.script.mvel.DynamicVarStep;
import com.redhat.step.script.mvel.condition.IfStep;
import com.redhat.step.script.mvel.condition.loop.DoWhileStep;
import com.redhat.step.script.mvel.condition.loop.WhileStep;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sfloess
 */
public class PipelineTest {
    GlobalContext globalContext;
    PipelineContext pipelineContext;

    @Before
    public void init() {
        globalContext = new DefaultGlobalContext();
        pipelineContext = new DefaultPipelineContext(globalContext);

        pipelineContext.getStepContext().getStepDefinitions().define("loop", LoopStep.class);
        pipelineContext.getStepContext().getStepDefinitions().define("logMsg", LogMsgStep.class);
        pipelineContext.getStepContext().getStepDefinitions().define("dvar", DynamicVarStep.class);
        pipelineContext.getStepContext().getStepDefinitions().define("do", DoWhileStep.class);
        pipelineContext.getStepContext().getStepDefinitions().define("while", WhileStep.class);
        pipelineContext.getStepContext().getStepDefinitions().define("if", IfStep.class);
        pipelineContext.getStepContext().getStepDefinitions().define("logContext", LogContextStep.class);
        pipelineContext.getStepContext().getStepDefinitions().define("result", ResultStep.class);

    }

    @Test
    public void test_loadYaml() throws Exception {
        final String yamlStr = IOUtils.toString(getClass().getResourceAsStream("/LoopPipeline.yml"), "UTF-8");
        final Map map = YAML.asType(yamlStr, HashMap.class);
        final JSONObject json = new JSONObject(map);
        System.out.println(json.toString(4));
    }

    @Test
    public void test_logStep() throws Exception {
        pipelineContext.getPipelineDefinitions().defineYamlPipeline("logTest", IOUtils.toString(getClass().getResourceAsStream("/LogPipeline.yml"), "UTF-8"));

        System.out.println(pipelineContext.getPipelineDefinitions().getDefinition("logTest").toString(4));

        pipelineContext.getPipelineExecutor().executeNamed("logTest", pipelineContext);

        System.out.println("Result of run: [" + pipelineContext.getResult() + "]");
    }

    @Test
    public void test_dvarStep() throws Exception {
        pipelineContext.getPipelineDefinitions().defineYamlPipeline("dvarTest", IOUtils.toString(getClass().getResourceAsStream("/DvarPipeline.yml"), "UTF-8"));

        System.out.println(pipelineContext.getPipelineDefinitions().getDefinition("dvarTest").toString(4));

        pipelineContext.getPipelineExecutor().executeNamed("dvarTest", pipelineContext);

        System.out.println("Result of run: [" + pipelineContext.getResult() + "]");
    }

    @Test
    public void test_loopStep() throws Exception {
        pipelineContext.getPipelineDefinitions().defineYamlPipeline("loopTest", IOUtils.toString(getClass().getResourceAsStream("/LoopPipeline.yml"), "UTF-8"));

        System.out.println(pipelineContext.getPipelineDefinitions().getDefinition("loopTest").toString(4));
        pipelineContext.getPipelineExecutor().executeNamed("loopTest", pipelineContext);

        System.out.println("Result of run: [" + pipelineContext.getResult() + "]");
    }

    @Test
    public void test_whileStep() throws Exception {
        pipelineContext.getPipelineDefinitions().defineYamlPipeline("whileTest", IOUtils.toString(getClass().getResourceAsStream("/WhilePipeline.yml"), "UTF-8"));

        System.out.println(pipelineContext.getPipelineDefinitions().getDefinition("whileTest").toString(4));
        pipelineContext.getPipelineExecutor().executeNamed("whileTest", pipelineContext);

        System.out.println("Result of run: [" + pipelineContext.getResult() + "]");
    }

    @Test
    public void test_doWhileStep() throws Exception {
        pipelineContext.getPipelineDefinitions().defineYamlPipeline("doTest", IOUtils.toString(getClass().getResourceAsStream("/DoWhilePipeline.yml"), "UTF-8"));

        System.out.println(pipelineContext.getPipelineDefinitions().getDefinition("doTest").toString(4));
        pipelineContext.getPipelineExecutor().executeNamed("doTest", pipelineContext);

        System.out.println("Result of run: [" + pipelineContext.getResult() + "]");
    }

    @Test
    public void test_ifElseStep() throws Exception {
        pipelineContext.getPipelineDefinitions().defineYamlPipeline("ifTest", IOUtils.toString(getClass().getResourceAsStream("/IfElsePipeline.yml"), "UTF-8"));

        System.out.println(pipelineContext.getPipelineDefinitions().getDefinition("ifTest").toString(4));
        pipelineContext.getPipelineExecutor().executeNamed("ifTest", pipelineContext);

        System.out.println("Result of run: [" + pipelineContext.getResult() + "]");
    }

    @Test
    public void test_ifStep() throws Exception {
        pipelineContext.getPipelineDefinitions().defineYamlPipeline("ifTest", IOUtils.toString(getClass().getResourceAsStream("/IfPipeline.yml"), "UTF-8"));

        System.out.println(pipelineContext.getPipelineDefinitions().getDefinition("ifTest").toString(4));
        pipelineContext.getPipelineExecutor().executeNamed("ifTest", pipelineContext);

        System.out.println("Result of run: [" + pipelineContext.getResult() + "]");
    }
}
