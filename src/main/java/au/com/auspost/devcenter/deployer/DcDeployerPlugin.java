package au.com.auspost.devcenter.deployer;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class DcDeployerPlugin implements Plugin<Project> {

    private Project project;

    public void apply(Project project) {
        project.getTasks().create("dcUpdate", DcDeployer.class, DcDeployer::configure);
    }

}