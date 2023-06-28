package net.onebeastchris.extensiondb.api;

public final class InstanceHolder {
    private static ExtensionDbApi instance;

    public static void set(ExtensionDbApi instance) {
        InstanceHolder.instance = instance;
    }

    public static ExtensionDbApi getInstance() {
        return instance;
    }
}
