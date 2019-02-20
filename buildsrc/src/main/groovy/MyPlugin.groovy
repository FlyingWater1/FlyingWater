
import org.gradle.api.Plugin
import org.gradle.api.Project

public class MyPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        println("this is a  test")
        project.task('testPlugin') << {
            println 'hello, world!'
        }
    }
}