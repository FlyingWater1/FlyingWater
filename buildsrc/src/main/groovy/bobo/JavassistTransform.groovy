package bobo

import com.android.build.api.transform.Context
import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format;
import com.android.build.api.transform.Transform
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformOutputProvider
import com.google.common.collect.Sets
import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import javassist.ClassPool



/**
 * @author dupengfei
 * @create 2019/2/20 0020
 * @Describe
 */
public class JavassistTransform extends Transform {

    Project project

    public JavassistTransform(Project project) {
        // 构造函数，我们将Project保存下来备用
        this.project = project
    }

    //用于指明本Transform的名字，这个name不是最终的名字，在TransformManager中会对名字再处理
    @Override
    String getName() {
        "JavassistTrans"
    }

    //用于指明Transform的输出类型，可以作为过滤的手段
    // -CLASSES 表示要处理预编译后的字节码，可能是jar包也可能是目录
    // -RESOURCES 表示处理标准的java资源
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return Sets.immutableEnumSet(QualifiedContent.DefaultContentType.CLASSES)
    }

    //用于指明Transform的作用域
    //–PROJECT                         只处理当前项目    
    //–SUB_PROJECTS  只处理子项目    
    //–PROJECT_LOCAL_DEPS  只处理当前项目的本地依赖,例如jar, aar    
    //–EXTERNAL_LIBRARIES  只处理外部的依赖库    
    //–PROVIDED_ONLY  只处理本地或远程以provided形式引入的依赖库    
    //–TESTED_CODE                         只处理测试代码
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return Sets.immutableEnumSet(
                QualifiedContent.Scope.PROJECT,
                QualifiedContent.Scope.PROJECT_LOCAL_DEPS,
                QualifiedContent.Scope.SUB_PROJECTS,
                QualifiedContent.Scope.SUB_PROJECTS_LOCAL_DEPS,
                QualifiedContent.Scope.EXTERNAL_LIBRARIES)

    }

    //用于指明是否是增量构建。
    @Override
    boolean isIncremental() {
        return false
    }

    //核心方法，用于自定义处理,
    //在这个方法中我们可以拿到要处理的.class文件路径、jar包路径、输出文件路径等，拿到文件之后就可以对他们进行操作
    @Override
    void transform(Context context, Collection<TransformInput> inputs,
                   Collection<TransformInput> referencedInputs,
                   TransformOutputProvider outputProvider, boolean isIncremental)
            throws IOException, TransformException, InterruptedException {
        def startTime = System.currentTimeMillis();
        // Transform的inputs有两种类型，一种是目录，一种是jar包，要分开遍历
        inputs.each { TransformInput input ->
            try {
                input.jarInputs.each {
                    MyInject.injectDir(it.file.getAbsolutePath(), "com", project)
                    String outputFileName = it.name.replace(".jar", "") + '-' + it.file.path.hashCode()
                    def output = outputProvider.getContentLocation(outputFileName, it.contentTypes, it.scopes, Format.JAR)
                    FileUtils.copyFile(it.file, output)
                }
            } catch (Exception e) {
                project.logger.err e.getMessage()
            }
            //对类型为“文件夹”的input进行遍历
            input.directoryInputs.each { DirectoryInput directoryInput ->
                //文件夹里面包含的是我们手写的类以及R.class、BuildConfig.class以及R$XXX.class等
                MyInject.injectDir(directoryInput.file.absolutePath, "com", project)
                // 获取output目录
                def dest = outputProvider.getContentLocation(directoryInput.name,
                        directoryInput.contentTypes, directoryInput.scopes,
                        Format.DIRECTORY)

                // 将input的目录复制到output指定目录
                FileUtils.copyDirectory(directoryInput.file, dest)
            }
        }
        ClassPool.getDefault().clearImportedPackages();
        project.logger.error("JavassistTransform cast :" + (System.currentTimeMillis() - startTime) / 1000 + " secs");
    }
}
