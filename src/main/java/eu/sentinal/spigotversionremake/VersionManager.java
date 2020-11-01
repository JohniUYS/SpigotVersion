package eu.sentinal.spigotversionremake;

import org.apache.maven.plugin.logging.Log;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;

public class VersionManager {


    public static void handle(SpigotVersionRemake spigotVersionRemake, Log log, YamlFile yamlFile) {
        String currentVal = yamlFile.getString("version");
        log.info("Found current version : " + currentVal);
        String newVersion = update(spigotVersionRemake,currentVal);
        log.info("Updating to version : " + newVersion);
        yamlFile.set("version", newVersion);
        try {
            yamlFile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String update(SpigotVersionRemake spigotVersionRemake, String input) {
        SVersion sVersion = new SVersion(spigotVersionRemake, input);
        sVersion.recalculate();
        return sVersion.build();
    }
}
