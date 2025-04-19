package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:supermarket.db";
    private static Connection connection;

    public static void initializeDatabase() {
        try {
            // Fermer la connexion existante si elle est ouverte
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            // Ouvrir une nouvelle connexion
            connection = DriverManager.getConnection(DB_URL);
            connection.setAutoCommit(true); // Activer l'auto-commit
            createTables();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'initialisation de la base de données:");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        // Si la connexion est fermée ou nulle, la rouvrir
        if (connection == null || connection.isClosed()) {
            initializeDatabase();
        }
        return connection;
    }

    private static void createTables() throws SQLException {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS utilisateurs (" +
                "id TEXT PRIMARY KEY, " +
                "nom TEXT, " +
                "username TEXT, " +
                "numTel TEXT, " +
                "genre TEXT, " +
                "password TEXT, " +
                "role TEXT)";

        String createProductsTable = "CREATE TABLE IF NOT EXISTS produits (" +
                "idProduit TEXT PRIMARY KEY, " +
                "type TEXT, " +
                "qte INTEGER, " +
                "marque TEXT, " +
                "dateExpiration TEXT, " +
                "prix REAL, " +
                "details TEXT)";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createProductsTable);
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion:");
            e.printStackTrace();
        }
    }
}