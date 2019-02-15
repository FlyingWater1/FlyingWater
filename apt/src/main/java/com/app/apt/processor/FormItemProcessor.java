package com.app.apt.processor;


import android.support.annotation.UiThread;

import com.app.annotation.apt.form.FormItem;
import com.app.annotation.apt.form.FormItems;
import com.app.annotation.apt.form.FormLayout;
import com.app.annotation.apt.form.OnFormItemClick;
import com.app.apt.AnnotationProcessor;
import com.app.apt.form.BindingSet;
import com.app.apt.form.FieldViewBinding;
import com.app.apt.form.MethodViewBinding;
import com.app.apt.inter.IProcessor;
import com.app.apt.util.Utils;
import com.google.auto.common.SuperficialValidation;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

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
    //    private FormItem[] formItems;
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

        Map<TypeElement, BindingSet> bindingMap = findAndParseTargets(roundEnv);

        for (Map.Entry<TypeElement, BindingSet> entry : bindingMap.entrySet()) {
//            TypeElement typeElement = entry.getKey();
            BindingSet binding = entry.getValue();

            JavaFile javaFile = binding.brewJava();
            try {
                javaFile.writeTo(mAbstractProcessor.mFiler);
            } catch (IOException e) {
//                error(typeElement, "Unable to write binding for type %s: %s", typeElement, e.getMessage());
            }
        }


//////////////////////////////**************************
        //*****************************************************
        //*****************************************************
        //*****************************************************
        //*****************************************************
        //*****************************************************
        //*****************************************************
        //*****************************************************


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
//
//
//        for (Element element : roundEnv.getElementsAnnotatedWith(FormItems.class)) {
//            String packageName = getPackage(element).getQualifiedName().toString();
//
//            String className = element.getSimpleName().toString();
//            TypeName targetTypeName = TypeName.get(element.asType());
//
//            formItems = element.getAnnotation(FormItems.class).value();
//
//            int formLayout = element.getAnnotation(FormItems.class).formLayout();
//
//
//
//            TypeSpec.Builder tb = classBuilder(className + "_FormItem");
//
//            tb
//                    .addModifiers(PUBLIC, FINAL)
//                    .addJavadoc("@ API工厂 此类由apt自动生成");
//
//            for (int i = 0; i < formItems.length; i++) {
//                tb.addField(FormItemView, nameL + i);
//            }
//
//            MethodSpec methodSpec = createBindingConstructorForActivity(roundEnv, targetTypeName, tb,formLayout);
//            tb.addMethod(methodSpec);
//
//
//            JavaFile javaFile = JavaFile.builder(Utils.PackageName + "." + packageName, tb.build()).build();// 生成源代码
//            try {
//                javaFile.writeTo(mAbstractProcessor.mFiler);// 在 app module/build/generated/source/apt 生成一份源代码
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


//////////////////////////////**************************
        //*****************************************************
        //*****************************************************
        //*****************************************************
        //*****************************************************
        //*****************************************************
        //*****************************************************
        //*****************************************************


    }

    private Map<TypeElement, BindingSet> findAndParseTargets(RoundEnvironment roundEnv) {
        Map<TypeElement, BindingSet.Builder> builderMap = new LinkedHashMap<>();
        Set<TypeElement> erasedTargetNames = new LinkedHashSet<>();

        for (Element element : roundEnv.getElementsAnnotatedWith(FormItems.class)) {
            if (!SuperficialValidation.validateElement(element)) continue;
            parseFormItems(element, builderMap, erasedTargetNames);
        }

        findAndParseListener(roundEnv, OnFormItemClick.class, builderMap, erasedTargetNames);

        Deque<Map.Entry<TypeElement, BindingSet.Builder>> entries =
                new ArrayDeque<>(builderMap.entrySet());

        Map<TypeElement, BindingSet> bindingMap = new LinkedHashMap<>();

        while (!entries.isEmpty()) {
            Map.Entry<TypeElement, BindingSet.Builder> entry = entries.removeFirst();

            TypeElement type = entry.getKey();
            BindingSet.Builder builder = entry.getValue();

            TypeElement parentType = findParentType(type, erasedTargetNames);
            if (parentType == null) {
                bindingMap.put(type, builder.build());
            } else {
                BindingSet parentBinding = bindingMap.get(parentType);
                if (parentBinding != null) {
                    builder.setParent(parentBinding);
                    bindingMap.put(type, builder.build());
                } else {
                    // Has a superclass binding but we haven't built it yet. Re-enqueue for later.
                    entries.addLast(entry);
                }
            }
        }

        return bindingMap;
    }

    private void findAndParseListener(RoundEnvironment roundEnv, Class<OnFormItemClick> annotationClass, Map<TypeElement, BindingSet.Builder> builderMap, Set<TypeElement> erasedTargetNames) {
        for (Element element : roundEnv.getElementsAnnotatedWith(annotationClass)) {
            if (!SuperficialValidation.validateElement(element)) continue;
            parseListenerAnnotation(annotationClass, element, builderMap, erasedTargetNames);
        }
    }

    private void parseListenerAnnotation(Class<OnFormItemClick> annotationClass, Element element, Map<TypeElement, BindingSet.Builder> builderMap, Set<TypeElement> erasedTargetNames) {
        if (!(element instanceof ExecutableElement) || element.getKind() != METHOD) {
            throw new IllegalStateException(
                    String.format("@%s annotation must be on a method.", OnFormItemClick.class.getSimpleName()));
        }
        ExecutableElement executableElement = (ExecutableElement) element;
        List<? extends VariableElement> parameters = executableElement.getParameters();
//        boolean check = checkParameters(parameters);
//        if (!check) {
//            return;
//        }


        TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();


        BindingSet.Builder builder = builderMap.get(enclosingElement);
        if (builder == null) {
            builder = getOrCreateBindingBuilder(builderMap, enclosingElement);
        }

        //全名
        String qualifiedName = enclosingElement.getQualifiedName().toString();
        //包名
        String packageName = getPackage(element).getQualifiedName().toString();
        //类名
        String simpleName = element.getSimpleName().toString();

        Annotation annotation = element.getAnnotation(OnFormItemClick.class);
//        TypeName typeName = TypeName.get(element.asType());

        builder.addMethod(qualifiedName, new MethodViewBinding(simpleName, true, annotation));

        erasedTargetNames.add(enclosingElement);

    }

    private void parseFormItems(Element element, Map<TypeElement, BindingSet.Builder> builderMap, Set<TypeElement> erasedTargetNames) {
        TypeElement enclosingElement = (TypeElement) element;
        BindingSet.Builder builder = builderMap.get(enclosingElement);
        if (builder == null) {
            builder = getOrCreateBindingBuilder(builderMap, enclosingElement);
        }

        //全名
        String qualifiedName = enclosingElement.getQualifiedName().toString();
        //包名
        String packageName = getPackage(element).getQualifiedName().toString();
        //类名
        String simpleName = element.getSimpleName().toString();


        TypeName typeName = TypeName.get(element.asType());

        FormItem[] formItems = element.getAnnotation(FormItems.class).value();

        int formLayout = element.getAnnotation(FormItems.class).formLayout();

        builder.addField(qualifiedName, new FieldViewBinding(typeName, simpleName, true, formItems, formLayout));

        erasedTargetNames.add(enclosingElement);


    }

    private boolean checkParameters(List<? extends VariableElement> parameters) {
        if (parameters.size() == 1 && (parameters.get(0).asType().toString().equals(String.class.getName()))) {
            return true;
        } else {
            throw new RuntimeException(
                    String.format("@%s annotation must be a parameter of String type.", OnFormItemClick.class.getSimpleName()));
        }
    }

    /**
     * Finds the parent binder type in the supplied set, if any.
     */
    private @Nullable
    TypeElement findParentType(TypeElement typeElement, Set<TypeElement> parents) {
        TypeMirror type;
        while (true) {
            type = typeElement.getSuperclass();
            if (type.getKind() == TypeKind.NONE) {
                return null;
            }
            typeElement = (TypeElement) ((DeclaredType) type).asElement();
            if (parents.contains(typeElement)) {
                return typeElement;
            }
        }
    }

    private BindingSet.Builder getOrCreateBindingBuilder(
            Map<TypeElement, BindingSet.Builder> builderMap, TypeElement enclosingElement) {
        BindingSet.Builder builder = builderMap.get(enclosingElement);
        if (builder == null) {
            builder = BindingSet.newBuilder(enclosingElement);
            builderMap.put(enclosingElement, builder);
        }
        return builder;
    }
}
