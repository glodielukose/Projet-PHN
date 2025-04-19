package org.example.permanent;

import org.example.produit.*;
import org.example.database.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProduitManager {
    public void ajouterProduit(Produit produit) {
        String sql = "INSERT INTO produits(id, type, nom, prix, quantite, details) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produit.getIdProduit());
            pstmt.setString(2, getProductType(produit));
            pstmt.setString(3, produit.getNomProduit());
            pstmt.setDouble(4, produit.getPrix());
            pstmt.setInt(5, produit.getQte());
            pstmt.setString(6, getProductDetails(produit));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produit> chargerProduits() {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produits";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produit produit = createProductFromResultSet(rs);
                if (produit != null) {
                    produits.add(produit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    public boolean modifierProduit(int index, Produit nouveauProduit) {
        List<Produit> produits = chargerProduits();
        if (index < 0 || index >= produits.size()) {
            return false;
        }

        String id = produits.get(index).getIdProduit();
        String sql = "UPDATE produits SET type=?, nom=?, prix=?, quantite=?, details=? WHERE id=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, getProductType(nouveauProduit));
            pstmt.setString(2, nouveauProduit.getNomProduit());
            pstmt.setDouble(3, nouveauProduit.getPrix());
            pstmt.setInt(4, nouveauProduit.getQte());
            pstmt.setString(5, getProductDetails(nouveauProduit));
            pstmt.setString(6, id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerProduit(int index) {
        List<Produit> produits = chargerProduits();
        if (index < 0 || index >= produits.size()) {
            return false;
        }

        String sql = "DELETE FROM produits WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produits.get(index).getIdProduit());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthodes utilitaires
    private String getProductType(Produit produit) {
        if (produit instanceof Alimentaire) return "alimentaire";
        if (produit instanceof Cosmetique) return "cosmetique";
        if (produit instanceof Menager) return "menager";
        return "autre";
    }

    private String getProductDetails(Produit produit) {
        // Implémentez la sérialisation des détails spécifiques
        // Par exemple avec Gson si nécessaire
        return ""; // À adapter
    }

    private Produit createProductFromResultSet(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        String details = rs.getString("details");

        // Implémentez la désérialisation selon le type
        // À adapter selon votre structure
        switch (type) {
            case "alimentaire":
                return new Alimentaire(
                        rs.getString("id"),
                        rs.getString("nom"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite")
                        // D'autres paramètres
                );
            case "cosmetique":
                return new Cosmetique(
                        rs.getString("id"),
                        rs.getString("nom"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite")
                        // D'autres paramètres
                );
            case "menager":
                return new Menager(
                        rs.getString("id"),
                        rs.getString("nom"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite")
                        // D'autres paramètres
                );
            default:
                return null;
        }
    }
}