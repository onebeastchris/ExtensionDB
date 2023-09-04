package net.onebeastchris.extension.extensiondb;

import net.onebeastchris.extension.extensiondb.api.ExtensionDbApiImpl;
import net.onebeastchris.extensiondb.api.InstanceHolder;
import org.geysermc.event.PostOrder;
import org.geysermc.event.subscribe.Subscribe;
import org.geysermc.geyser.api.event.lifecycle.GeyserPreInitializeEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserShutdownEvent;
import org.geysermc.geyser.api.extension.Extension;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExtensionDB implements Extension {
    private static Connection connection;

    @Subscribe(postOrder = PostOrder.FIRST)
    @SuppressWarnings("unused")
    public void onPreInit(GeyserPreInitializeEvent event) {
        logger().info("Loading ExtensionDB...");
        load();
        InstanceHolder.set(new ExtensionDbApiImpl());
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onGeyserShutdown(GeyserShutdownEvent event) {
        logger().info("Stopping ExtensionDB...");
        stop();
    }

    public void load() {

        // Create the data folder if it doesn't exist
        if (!this.dataFolder().toFile().exists()) {
            if (!this.dataFolder().toFile().mkdirs()) {
                logger().error("Failed to create data folder");
                return;
            }
        }

        Path databasePath = this.dataFolder().resolve("extension-storage.db");

        if (!databasePath.toFile().exists()) {
            // First time loading - idk, print something?
            logger().info("This is the first time loading ExtensionDB, creating database...");
        }

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
            //try (Statement statement = connection.createStatement()) {
            //
            //}
        } catch (ClassNotFoundException exception) {
            logger().error("The required class to load the SQLite database wasn't found");
        } catch (SQLException exception) {
            logger().error("Error while loading database", exception);
        }
        logger().info("ExtensionDB loaded!");
    }

    public void stop() {
        try {
            connection.close();
        } catch (SQLException exception) {
            logger().error("Error while closing database connection", exception);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
