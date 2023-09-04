package net.onebeastchris.extensiondb.api;

public final class InstanceHolder {
    private static ExtensionDbApi instance = null;

    public static void set(ExtensionDbApi instance) {
        InstanceHolder.instance = instance;
    }

    public static ExtensionDbApi getInstance() {
        if (instance == null) {
            throw new IllegalStateException("You cannot access the ExtensionDbApi before the extension has been loaded");
        }
        return instance;
    }
}
