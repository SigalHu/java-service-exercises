package com.sigalhu.jse.mongo.shell;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.data.mongodb.core.script.NamedMongoScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huxujun
 * @date 2019/1/9
 */
@Service
public class MongoScriptsService {

    @Resource(name = "mongoTemplate")
    private MongoOperations mongoOperations;

    public Object execute(String script) {
        ScriptOperations scriptOperations = mongoOperations.scriptOps();
        NamedMongoScript mongoScript = new NamedMongoScript("tsc", script);
        scriptOperations.register(mongoScript);
        return scriptOperations.call("tsc");
    }
}
