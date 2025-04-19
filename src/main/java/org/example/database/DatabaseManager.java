package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:supermarket.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            // Création de la table utilisateurs
            conn.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS utilisateurs (" +
                            "id TEXT PRIMARY KEY," +
                            "type TEXT NOT NULL," +
                            "nom TEXT NOT NULL," +
                            "username TEXT NOT NULL," +
                            "numTel TEXT NOT NULL," +
                            "genre TEXT NOT NULL," +
                            "password TEXT NOT NULL," +
                            "email TEXT," +
                            "autres_details TEXT" +
                            ")"
            );

            // Création de la table produits
            conn.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS produits (" +
                            "id TEXT PRIMARY KEY," +
                            "type TEXT NOT NULL," +
                            "nom TEXT NOT NULL," +
                            "prix REAL NOT NULL," +
                            "quantite INTEGER NOT NULL," +
                            "details TEXT" +
                            ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}