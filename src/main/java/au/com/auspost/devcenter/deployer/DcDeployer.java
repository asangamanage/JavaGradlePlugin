package au.com.auspost.devcenter.deployer;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class DcDeployer extends DefaultTask {

    private String apiVersion;
    private String environment;
    private String devCenterHost;
    private String elbHost;
    private String apiKey;
    private String applicationClass;

    @TaskAction
    void startDeployement() {
        System.out.printf("Starting deployment %s", this);
    }

    public void configure() {
        apiVersion = getString("apiVersion");
        environment = getString("environment");
        apiKey = getString("apiKey");
        applicationClass = getString("applicationClass");
        devCenterHost = getString(this.environment + "_dc");
        elbHost = getString(this.environment + "_elb");
        try (ConfigurableApplicationContext ignored = SpringApplication.run(Class.forName(applicationClass))) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getDevCenterHost() {
        return devCenterHost;
    }

    public void setDevCenterHost(String devCenterHost) {
        this.devCenterHost = devCenterHost;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    private String getString(String value) {
        return (String) getProject().property(value);
    }

    @Override
    public String toString() {
        return "DcDeployer{" +
                "apiVersion='" + apiVersion + '\'' +
                ", environment='" + environment + '\'' +
                ", devCenterHost='" + devCenterHost + '\'' +
                ", elbHost='" + elbHost + '\'' +
                ", apiKey='" + apiKey + '\'' +
                '}';
    }
}
