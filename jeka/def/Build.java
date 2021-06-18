import dev.jeka.core.api.java.JkJavaVersion;
import dev.jeka.core.tool.JkClass;
import dev.jeka.core.tool.JkInit;
import dev.jeka.core.tool.builtins.java.JkPluginJava;

class Build extends JkClass {

    final JkPluginJava java = getPlugin(JkPluginJava.class);

    /*
     * Configures plugins to be bound to this command class. When this method is called, option
     * fields have already been injected from command line.
     */
    @Override
    protected void setup() {
        java.getProject().simpleFacade()
            .setJavaVersion(JkJavaVersion.V8)
            .setCompileDependencies(deps -> deps
                .and("com.google.guava:guava:21.0"))
            .setTestDependencies(deps -> deps
                .and("org.junit.jupiter:junit-jupiter:5.6.2"))

            // Only necessary if your project is published in a binary repository.
            .setPublishedMavenModuleId("your.group:your.project")
            .setPublishedMavenVersionFromGit();  // Version inferred from Git
    }

    public void cleanPack() {
        clean(); java.pack();
    }

    public static void main(String[] args) {
        JkInit.instanceOf(Build.class, args).cleanPack();
    }

}