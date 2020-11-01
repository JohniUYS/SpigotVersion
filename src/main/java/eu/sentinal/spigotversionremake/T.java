package eu.sentinal.spigotversionremake;

import java.util.UUID;

public class T {

    private UUID uuid;
    private String displayName;
    private String tabName;

    public static void main(String[] args) {
        print();
    }

    T(){

    }

    public UUID getUuid() {
        return uuid;
    }

    public T setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public T setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getTabName() {
        return tabName;
    }

    public T setTabName(String tabName) {
        this.tabName = tabName;
        return this;
    }

    public static void print() {
        T itema = new T().setDisplayName("DisplayName").setTabName("TabName").setUuid(UUID.randomUUID());
        System.out.println(itema.getDisplayName());
        System.out.println(itema.getTabName());
        System.out.println(itema.getUuid().toString());
    }
}
