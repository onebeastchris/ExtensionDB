package net.onebeastchris.extension.extensiondb.api;

import net.onebeastchris.extension.extensiondb.ExtensionDB;
import net.onebeastchris.extensiondb.api.ExtensionDbApi;

import java.sql.Connection;

public class ExtensionDbApiImpl implements ExtensionDbApi {

    @Override
    public Connection getConnection() {
        return ExtensionDB.getConnection();
    }
}
