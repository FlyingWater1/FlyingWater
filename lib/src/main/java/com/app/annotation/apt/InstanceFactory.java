package com.app.annotation.apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 实例化注解,会被主动添加到实例化工厂,自动生成new来替换掉反射的newInstance代码
 */
public @interface InstanceFactory {

}


