package com.sigalhu.jse.lombok;

import lombok.Builder;

/**
 * @author huxujun
 * @date 2018/8/21
 */
@Builder
public class BuilderExample {

    private String name;

    private String mob;

    private String sex;

//    @java.beans.ConstructorProperties({"name", "mob", "sex"})
//    BuilderExample(String name, String mob, String sex) {
//        this.name = name;
//        this.mob = mob;
//        this.sex = sex;
//    }
//
//    public static BuilderExampleBuilder builder() {
//        return new BuilderExampleBuilder();
//    }
//
//    public static class BuilderExampleBuilder {
//        private String name;
//        private String mob;
//        private String sex;
//
//        BuilderExampleBuilder() {
//        }
//
//        public BuilderExampleBuilder name(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public BuilderExampleBuilder mob(String mob) {
//            this.mob = mob;
//            return this;
//        }
//
//        public BuilderExampleBuilder sex(String sex) {
//            this.sex = sex;
//            return this;
//        }
//
//        public BuilderExample build() {
//            return new BuilderExample(name, mob, sex);
//        }
//
//        public String toString() {
//            return "BuilderExample.BuilderExampleBuilder(name=" + this.name + ", mob=" + this.mob + ", sex=" + this.sex + ")";
//        }
//    }
}
