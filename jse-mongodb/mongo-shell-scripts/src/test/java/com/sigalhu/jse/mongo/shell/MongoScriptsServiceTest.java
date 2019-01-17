package com.sigalhu.jse.mongo.shell;

import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2019/1/9
 */
public class MongoScriptsServiceTest extends BaseTest {

    @Resource
    private MongoScriptsService mongoScriptsService;

    @Test
    public void execute() {
        Object result = mongoScriptsService.execute("function(){return db.test.find().toArray();}");
        System.out.println(result);

    }
}