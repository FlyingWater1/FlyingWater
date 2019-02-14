package com.app.apt.form;

import android.support.annotation.UiThread;

import com.app.annotation.apt.form.FormItem;
import com.app.annotation.apt.form.OnFormItemClick;
import com.google.common.collect.ImmutableList;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import static com.google.auto.common.MoreElements.getPackage;
import static com.squareup.javapoet.ClassName.bestGuess;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * @author dupengfei
 * @create 2019/2/13 0013
 * @Describe
 */
public final class BindingSet {

    private final ClassName bindingClassName;
    private ImmutableList<ViewBinding> viewBindings;

    private String nameL = "formItems";
    ClassName FormItemView = ClassName.get("com.feishuixiansheng.compiler", "FormItemView");

    private String setter = "setOnClickListener";
    private String[] parameterTypes = new String[]{"android.view.View"};
    private String methodName = "onClick";
    private String type = "android.view.View.OnClickListener";


    public BindingSet(ClassName bindingClassName, ImmutableList<ViewBinding> viewBindings) {
        this.bindingClassName = bindingClassName;
        this.viewBindings = viewBindings;
    }

    public static Builder newBuilder(TypeElement enclosingElement) {
        String packageName = getPackage(enclosingElement).getQualifiedName().toString();
        String className = enclosingElement.getQualifiedName().toString().substring(
                packageName.length() + 1).replace('.', '$');
        ClassName bindingClassName = ClassName.get(packageName, className);

        return new Builder(bindingClassName);
    }

    public JavaFile brewJava() {
        TypeSpec bindingConfiguration = createType();
        return JavaFile.builder(bindingClassName.packageName(), bindingConfiguration)
                .addFileComment("Generated code from Butter Knife. Do not modify!")
                .build();
    }

    private TypeSpec createType() {
        String simpleName = bindingClassName.simpleName();
        TypeSpec.Builder result = TypeSpec.classBuilder(simpleName + "_FormItem")
                .addModifiers(PUBLIC, FINAL)
                .addJavadoc("@ API工厂 此类由apt自动生成");


        for (int i = 0; i < viewBindings.size(); i++) {

            createBindingConstructorForActivity(i, result);

        }


        return result.build();
    }

    public static class Builder {

        ClassName bindingClassName;

        private final Map<String, ViewBinding.Builder> viewIdMap = new LinkedHashMap<>();

        public Builder(ClassName bindingClassName) {
            this.bindingClassName = bindingClassName;
        }

        public BindingSet build() {
            ImmutableList.Builder<ViewBinding> viewBindings = ImmutableList.builder();
            for (ViewBinding.Builder builder : viewIdMap.values()) {
                viewBindings.add(builder.build());
            }
            return new BindingSet(bindingClassName, viewBindings.build());
        }

        public void setParent(BindingSet parentBinding) {

        }

        public void addField(String qualifiedName, FieldViewBinding fieldViewBinding) {
//            FieldViewBinding fieldBinding
            getOrCreateViewBindings(qualifiedName).setFieldBinding(fieldViewBinding);
        }

        private ViewBinding.Builder getOrCreateViewBindings(String qualifiedName) {
            ViewBinding.Builder viewId = viewIdMap.get(qualifiedName);
            if (viewId == null) {
                viewId = new ViewBinding.Builder(qualifiedName);
                viewIdMap.put(qualifiedName, viewId);
            }
            return viewId;
        }

        public void addMethod(String qualifiedName, MethodViewBinding methodViewBinding) {
            getOrCreateViewBindings(qualifiedName).addMethodBinding(methodViewBinding);
        }
    }

    private void createBindingConstructorForActivity(int position, TypeSpec.Builder result) {

        ViewBinding viewBinding = viewBindings.get(position);

        FormItem[] formItems = viewBinding.fieldBinding.formItems;

        MethodViewBinding methodViewBinding = viewBinding.methodViewBinding;

        TypeName targetTypeName = viewBinding.fieldBinding.typeName;
        int formLayout = viewBinding.fieldBinding.formLayout;

        MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                .addModifiers(PUBLIC)
                .addAnnotation(UiThread.class)
                .addParameter(targetTypeName, "target", FINAL);

        ClassName VIEW = ClassName.get("android.view", "View");
        ClassName VIEW_GROUP = ClassName.get("android.view", "ViewGroup");
        ClassName TEXT_VIEW = ClassName.get("android.widget", "TextView");
        ClassName LinearLayout = ClassName.get("android.widget", "LinearLayout");

        builder.addStatement("$T formLayout = target.findViewById($L)", VIEW, formLayout);
//
        builder.beginControlFlow("if (formLayout instanceof $T)", LinearLayout);//括号开始

        for (int j = 0; j < formItems.length; j++) {

            result.addField(FormItemView, nameL + j);

            builder.addStatement("$L = new $T(target)", nameL + j, FormItemView);
            builder.addStatement("$L.setName($S)", nameL + j, formItems[j].leftName());
            if (formItems[j].leftImage() != 0) {
                builder.addStatement("$L.setLeftIma($L)", nameL + j, formItems[j].leftImage());
            }

            if (formItems[j].rightNotNull()) {
                builder.addStatement("$L.isNotNull($L)", nameL + j, formItems[j].rightNotNull());
            }

            if (!formItems[j].inputType().equals("")) {
                builder.addStatement("$L.setInputType($S)", nameL + j, formItems[j].inputType());
            }

            if (formItems[j].clickable()) {
//

                //如果Activity中有被OnFormItemClick注解过的方法名，才进行操作
//                Method annotationValue = null;
//                try {
//                    annotationValue = OnFormItemClick.class.getDeclaredMethod("value");
//                    String[] types = (String[]) annotationValue.invoke(methodViewBinding.annotation);
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }

                if (methodViewBinding != null) {

//                        List<? extends VariableElement> parameters = executableElement.getParameters();
//                        boolean check = checkParameters(parameters);

                    String methodName1 = methodViewBinding.simpleName;
                    TypeSpec.Builder callback = TypeSpec.anonymousClassBuilder("")
                            .superclass(bestGuess(type));

                    MethodSpec.Builder callbackMethod = MethodSpec.methodBuilder(methodName)
                            .addAnnotation(Override.class)
                            .addModifiers(PUBLIC);
                    //回掉中的参数
                    callbackMethod.addParameter(bestGuess(parameterTypes[0]), "view");
                    callbackMethod.addStatement("target.$L($L.getInputType(),$L)", methodName1, nameL + j,j);

                    callback.addMethod(callbackMethod.build());

                    builder.addStatement("$L.$L($L)", nameL + j, setter, callback.build());

                }
            }
            builder.addStatement("((LinearLayout)formLayout).addView($L)", nameL + j);
        }


//        builder.addStatement("$T viewById = target.getWindow().getDecorView().findViewById(android.R.id.content)", VIEW);
//        builder.beginControlFlow("if (viewById instanceof $T )", VIEW_GROUP);//括号开始
//        builder.addStatement("ViewGroup viewGroup = (ViewGroup) viewById");
//        builder.addStatement("View childAt = viewGroup.getChildAt(0)");
//
//        builder.beginControlFlow("if (childAt instanceof ViewGroup)");


//        if (formItems.length > 0) {
//
//            for (int i = 0; i < formItems.length; i++) {


//                if (formItems[i].leftImage() != 0) {
//                    builder.addStatement("$L.setLeftIma($L)", nameL + i, formItems[i].leftImage());
//                }
//
//                if (formItems[i].rightNotNull()) {
//                    builder.addStatement("$L.isNotNull($L)", nameL + i, formItems[i].rightNotNull());
//                }
//
//                if (!formItems[i].inputType().equals("")) {
//                    builder.addStatement("$L.setInputType($S)", nameL + i, formItems[i].inputType());
//                }
//
//
//                //设置点击事件
//
//            }
//        }

        builder.endControlFlow();
//        builder.endControlFlow();
//        builder.endControlFlow();

        result.addMethod(builder.build());

    }


}
