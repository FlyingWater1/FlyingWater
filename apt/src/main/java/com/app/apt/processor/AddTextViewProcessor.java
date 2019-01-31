package com.app.apt.processor;

import com.app.annotation.apt.AddTextView;
import com.app.apt.AnnotationProcessor;
import com.app.apt.inter.IProcessor;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

import static com.google.auto.common.MoreElements.getPackage;

/**
 * https://www.jianshu.com/p/4fef2ad51f5a?utm_campaign
 * Android APT 编译期进入debug模式
 * @author dupengfei
 * @create 2019/1/31 0031
 * @Describe
 */
public class AddTextViewProcessor implements IProcessor {
    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor) {
        for (Element element : roundEnv.getElementsAnnotatedWith(AddTextView.class)) {
            String adsnm = "dsad";
//            TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();

//            String packageName = getPackage(enclosingElement).getQualifiedName().toString();

//            String packageName = getPackage(element).getQualifiedName().toString();
//
//
//
//            String packageName2 = getPackage(element).getQualifiedName().toString();
//
//            String className2 = element.getSimpleName().toString().substring(
//                    packageName2.length() + 1).replace('.', '$');
//
//            String className = element.getSimpleName().toString().substring(
//                    packageName.length() + 1).replace('.', '$');



        }


    }
}
