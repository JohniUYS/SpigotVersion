package eu.sentinal.spigotversionremake;

import java.util.Arrays;

public class SVersion {

    private SpigotVersionRemake spigotVersionRemake;

    private String identifier;
    private int Major = 0;
    private int Minor = 0;
    private int Patch = 0;
    private int Build = 0;

    public SVersion(SpigotVersionRemake spigotVersionRemake, String i) {
        this.spigotVersionRemake = spigotVersionRemake;
        String[] split = i.split("\\.");
        System.out.println(Arrays.toString(split));
        // 0 = Major, 1 = Minor, 2 = Patch, 3 = Build | 4 = Length
        identifier = String.valueOf(split[0].charAt(0));
        Major = Integer.parseInt(String.valueOf(split[0].charAt(1)));
        Minor = Integer.parseInt(split[1]);
        switch (split.length) {
            case 2:
                break;
            case 3:
                Patch = Integer.parseInt(split[2]);
                break;
            case 4:
                Patch = Integer.parseInt(split[2]);
                Build = Integer.parseInt(split[3]);
                break;
            default:
                identifier = "n";
                Major = 0;
                Minor = 0;
                spigotVersionRemake.getLog().error("Invalid Plugin.yml Version!");
        }

    }

    public void recalculate() {
        switch (spigotVersionRemake.update) {
            case "Build":
                Build += 1;
                break;
            case "Patch":
                if ((Patch += 1) == 10) {
                    Patch = 0;
                    Major += 1;
                } else {
                    Patch += 1;
                }
                break;
            case "Minor":
                Minor += 1;
                break;
            case "Major":
                Major += 1;
                break;
        }
    }

    public String build() {

        return identifier +
                Major +
                "." +
                Minor +
                "." +
                Patch +
                "." +
                Build;
    }
}
