package com.app.apt.processor;

import com.app.annotation.apt.AddText;
import com.app.annotation.apt.Extra;
import com.app.apt.AnnotationProcessor;
import com.app.apt.inter.IProcessor;
import com.app.apt.util.Utils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;


import java.io.IOException;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;

import static com.google.auto.common.MoreElements.getPackage;
import static com.squareup.javapoet.TypeSpec.classBuilder;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * @author dupengfei
 * @create 2019/2/1 0001
 * @Describe
 */
public class AddTextProcessor implements IProcessor {

    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor) {

        for (Element element : roundEnv.getElementsAnnotatedWith(AddText.class)) {
            String packageName = getPackage(element).getQualifiedName().toString();
            String className = element.getSimpleName().toString();

            MethodSpec methodSpec = createBindingConstructorForActivity(element);

            TypeSpec.Builder tb = classBuilder(className + "_AddText")
                    .addModifiers(PUBLIC, FINAL)
                    .addJavadoc("@ API工厂 此类由apt自动生成")
                    .addMethod(methodSpec);


            JavaFile javaFile = JavaFile.builder(Utils.PackageName + "." + packageName, tb.build()).build();// 生成源代码
            try {
                javaFile.writeTo(mAbstractProcessor.mFiler);// 在 app module/build/generated/source/apt 生成一份源代码
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private MethodSpec createBindingConstructorForActivity(Element element) {

        String addText = element.getAnnotation(AddText.class).value();

        TypeName targetTypeName = TypeName.get(element.asType());

        MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                .addModifiers(PUBLIC)
                .addParameter(targetTypeName, "target");

        ClassName VIEW = ClassName.get("android.view", "View");
        ClassName VIEW_GROUP = ClassName.get("android.view", "ViewGroup");
        ClassName TEXT_VIEW = ClassName.get("android.widget", "TextView");
        builder.addStatement("$T viewById = target.getWindow().getDecorView().findViewById(android.R.id.content)", VIEW);
        builder.beginControlFlow("if (viewById instanceof $T )", VIEW_GROUP);//括号开始
        builder.addStatement("ViewGroup viewGroup = (ViewGroup) viewById");
        builder.addStatement("View childAt = viewGroup.getChildAt(0)");

        builder.beginControlFlow("if (childAt instanceof ViewGroup)");
        builder.addStatement("$T textView = new $T(target)", TEXT_VIEW, TEXT_VIEW);
        builder.addStatement("textView.setText(\"" + addText + "\")");
        builder.addStatement("((ViewGroup) childAt).addView(textView)");

        builder.endControlFlow();
        builder.endControlFlow();

        return builder.build();
    }

}
