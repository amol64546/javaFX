package org.openjfx;

public enum ScriptPaths {

    TEST("test.sh"),

    INSTALL_MONGO("install/mongodb.sh"),
    INSTALL_POSTGRES("install/postgres.sh"),
    INSTALL_REDIS("install/redis.sh"),

    UNINSTALL_MONGO("uninstall/mongodb.sh"),
    UNINSTALL_POSTGRES("uninstall/postgres.sh"),
    UNINSTALL_REDIS("uninstall/redis.sh");

    private final static String BASE_PATH = "/home/amol/Desktop/javaFX/javaFX/src/main/resources/scripts/";
    private final String path;

    // Constructor
    ScriptPaths(String path) {
        this.path = path;
    }

    // Method to get the full path
    public String getPath() {
        return BASE_PATH + path;
    }
}
