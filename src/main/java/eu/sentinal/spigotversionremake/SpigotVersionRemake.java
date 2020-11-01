package eu.sentinal.spigotversionremake;

import org.apache.maven.model.Build;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

@Mojo(name = "SpigotVersionRemake", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class SpigotVersionRemake extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Parameter(name = "update", defaultValue = "Build")
    String update;




    @Override
    public void execute() throws MojoFailureException {

        File file = project.getBasedir();
        if (!file.isDirectory())
            return;
        Path path = file.toPath();
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (file.toFile().getAbsolutePath().contains("target")) return FileVisitResult.CONTINUE;
                    if (file.toFile().getName().startsWith("plugin")) {
                        File finalFile = file.toFile();
                        YamlFile yamlFile = new YamlFile(finalFile);
                        if (yamlFile.exists()) {
                            try {
                                yamlFile.load();
                                VersionManager.handle(SpigotVersionRemake.this, getLog(), yamlFile);
                                return FileVisitResult.TERMINATE;
                            } catch (InvalidConfigurationException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
