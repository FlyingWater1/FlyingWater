package com.bobo.plugin

import org.gradle.api.Plugin;
import org.gradle.api.Project;

class CustomPluginOne implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.extensions.add("personInfo", CustomPluginOneExtension)
        project.
        task("personInfoTask") << {
            println("姓名： " + project.personInuploadArchivesfo.name)
            println("年龄： " + project.personInfo.age)
            println("性别： " + project.personInfo.sex)
            println("家庭住址： " + project.personInfo.address)
            println("公司： " + project.personInfo.company)
            println("公司住址： " + project.personInfo.companyAddress)
        }
    }
}
