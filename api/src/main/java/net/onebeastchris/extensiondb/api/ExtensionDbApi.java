package net.onebeastchris.extensiondb.api;

import java.sql.Connection;

public interface ExtensionDbApi {

    /**
     * Get the instance of the ExtensionDbApi
     *
     * @return the instance of the ExtensionDbApi
     */
    static ExtensionDbApi getInstance() {
        return InstanceHolder.getInstance();
    }

    /**
     * Get the connection to the SQLite database
     *
     * @return the connection to the database
     */
    Connection getConnection();
}

