package com.app.apt.processor;


import android.support.annotation.UiThread;

import com.app.annotation.apt.form.FormItem;
import com.app.annotation.apt.form.FormItems;
import com.app.annotation.apt.form.FormLayout;
import com.app.annotation.apt.form.OnFormItemClick;
import com.app.apt.AnnotationProcessor;
import com.app.apt.inter.IProcessor;
import com.app.apt.util.Utils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import static com.google.auto.common.MoreElements.getPackage;
import static com.squareup.javapoet.ClassName.bestGuess;
import static com.squareup.javapoet.TypeSpec.classBuilder;
import static javax.lang.model.element.ElementKind.METHOD;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * @author dupengfei
 * @create 2019/2/11 0011
 * @Describe
 */
public class FormItemProcessor implements IProcessor {



    ClassName FormItemView = ClassName.get("com.feishuixiansheng.compiler", "FormItemView");
    private FormItem[] formItems;
    private String nameL = "formItems";
    private String nameClick = "onFormItemClick";
    private String setter = "setOnClickListener";
    private String type = "android.view.View.OnClickListener";
    private String methodName = "onClick";
    private String[] parameterTypes = new String[]{"android.view.View"};

    //Activity中被OnFormItemClick注解过的方法
    private ExecutableElement executableElement;


    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor) {

        for (Element element : roundEnv.getElementsAnnotatedWith(OnFormItemClick.class)) {
            if (!(element instanceof ExecutableElement) || element.getKind() != METHOD) {
                throw new IllegalStateException(
                        String.format("@%s annotation must be on a method.", OnFormItemClick.class.getSimpleName()));
            }

            executableElement = (ExecutableElement) element;
            TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();

            // Assemble information on the method.
            Annotation annotation = element.getAnnotation(OnFormItemClick.class);
            Method annotationValue = null;
            try {
                annotationValue = OnFormItemClick.class.getDeclaredMethod("value");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (annotationValue.getReturnType() != String[].class) {
                throw new IllegalStateException(
                        String.format("@%s annotation value() type not String[].", OnFormItemClick.class));
            }

            try {
                //获取到的类型（直接文字，无文字，输入，单选，时间）
                String[] types = (String[]) annotationValue.invoke(annotation);


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        }


        for (Element element : roundEnv.getElementsAnnotatedWith(FormItems.class)) {
            String packageName = getPackage(element).getQualifiedName().toString();

            String className = element.getSimpleName().toString();
            TypeName targetTypeName = TypeName.get(element.asType());

            formItems = element.getAnnotation(FormItems.class).value();

            int formLayout = element.getAnnotation(FormItems.class).formLayout();



            TypeSpec.Builder tb = classBuilder(className + "_FormItem");

            tb
                    .addModifiers(PUBLIC, FINAL)
                    .addJavadoc("@ API工厂 此类由apt自动生成");

            for (int i = 0; i < formItems.length; i++) {
                tb.addField(FormItemView, nameL + i);
            }

            MethodSpec methodSpec = createBindingConstructorForActivity(roundEnv, targetTypeName, tb,formLayout);
            tb.addMethod(methodSpec);


            JavaFile javaFile = JavaFile.builder(Utils.PackageName + "." + packageName, tb.build()).build();// 生成源代码
            try {
                javaFile.writeTo(mAbstractProcessor.mFiler);// 在 app module/build/generated/source/apt 生成一份源代码
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }

    private MethodSpec createBindingConstructorForActivity(RoundEnvironment roundEnv, TypeName targetTypeName, TypeSpec.Builder tb, int formLayout) {

        MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                .addModifiers(PUBLIC)
                .addAnnotation(UiThread.class)
                .addParameter(targetTypeName, "target", FINAL);

        ClassName VIEW = ClassName.get("android.view", "View");
        ClassName VIEW_GROUP = ClassName.get("android.view", "ViewGroup");
        ClassName TEXT_VIEW = ClassName.get("android.widget", "TextView");
        ClassName LinearLayout = ClassName.get("android.widget", "LinearLayout");

        builder.addStatement("$T formLayout = target.findViewById($L)",VIEW,formLayout);

        builder.beginControlFlow("if (formLayout instanceof $T)", LinearLayout);//括号开始








//        builder.addStatement("$T viewById = target.getWindow().getDecorView().findViewById(android.R.id.content)", VIEW);
//        builder.beginControlFlow("if (viewById instanceof $T )", VIEW_GROUP);//括号开始
//        builder.addStatement("ViewGroup viewGroup = (ViewGroup) viewById");
//        builder.addStatement("View childAt = viewGroup.getChildAt(0)");
//
//        builder.beginControlFlow("if (childAt instanceof ViewGroup)");


        if (formItems.length > 0) {

            for (int i = 0; i < formItems.length; i++) {
                builder.addStatement("$L = new $T(target)", nameL + i, FormItemView);
                builder.addStatement("$L.setName($S)", nameL + i, formItems[i].leftName());


                if (formItems[i].leftImage() != 0) {
                    builder.addStatement("$L.setLeftIma($L)", nameL + i, formItems[i].leftImage());
                }

                if (formItems[i].rightNotNull()) {
                    builder.addStatement("$L.isNotNull($L)", nameL + i, formItems[i].rightNotNull());
                }

                if (!formItems[i].inputType().equals("")) {
                    builder.addStatement("$L.setInputType($S)", nameL + i, formItems[i].inputType());
                }



                //设置点击事件
                if (formItems[i].clickable()) {

                    //如果Activity中有被OnFormItemClick注解过的方法名，才进行操作
                    if (executableElement != null) {

                        List<? extends VariableElement> parameters = executableElement.getParameters();
                        boolean check = checkParameters(parameters);

                        if (check) {
                            String methodName1 = executableElement.getSimpleName().toString();
                            TypeSpec.Builder callback = TypeSpec.anonymousClassBuilder("")
                                    .superclass(bestGuess(type));

                            MethodSpec.Builder callbackMethod = MethodSpec.methodBuilder(methodName)
                                    .addAnnotation(Override.class)
                                    .addModifiers(PUBLIC);
                            //回掉中的参数
                            callbackMethod.addParameter(bestGuess(parameterTypes[0]), "view");
                            callbackMethod.addStatement("target.$L($L.getInputType())", methodName1, nameL + i);

                            callback.addMethod(callbackMethod.build());

                            builder.addStatement("$L.$L($L)", nameL + i, setter, callback.build());
                        }
                    }
                }
                builder.addStatement("((LinearLayout)formLayout).addView($L)", nameL + i);
            }
        }

//        builder.endControlFlow();
//        builder.endControlFlow();
        builder.endControlFlow();


        return builder.build();
    }

    private boolean checkParameters(List<? extends VariableElement> parameters) {
        if (parameters.size() == 1 && (parameters.get(0).asType().toString().equals(String.class.getName()))) {
            return true;
        } else {
            throw new RuntimeException(
                    String.format("@%s annotation must be a parameter of String type.", OnFormItemClick.class.getSimpleName()));
        }
    }
}