package org.example.permanent;

import org.example.DatabaseManager;
import org.example.produit.Produit;
import org.example.produit.Alimentaire;
import org.example.produit.Cosmetique;
import org.example.produit.Menager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProduitManager {
    public void ajouterProduit(Produit produit) {
        String sql = "INSERT INTO produits(idProduit, type, qte, marque, dateExpiration, prix, details) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            JSONObject details = new JSONObject();
            String type = "";

            // Remplir les détails spécifiques selon le type de produit
            if (produit instanceof Cosmetique) {
                type = "cosmetique";
                Cosmetique cosmetique = (Cosmetique) produit;
                details.put("typeDePeau", new JSONArray(cosmetique.getTypeDePeau()));
                details.put("allergene", new JSONArray(cosmetique.getAllergene()));
                details.put("durreeConservation", cosmetique.getDurreeConservation());
            } else if (produit instanceof Menager) {
                type = "menager";
                Menager menager = (Menager) produit;
                details.put("emballage", menager.getEmballage());
                details.put("efficacite", menager.getEfficacite());
                details.put("securite", menager.getSecurite());
            } else if (produit instanceof Alimentaire) {
                type = "alimentaire";
                Alimentaire alimentaire = (Alimentaire) produit;
                details.put("valuerNutritionnelle", alimentaire.getValuerNutritionnelle());
                details.put("ingredients", new JSONArray(alimentaire.getIngredients()));
                details.put("certification", alimentaire.getCertification());
            }

            // Définir les paramètres de la requête
            pstmt.setString(1, produit.getIdProduit());
            pstmt.setString(2, type);
            pstmt.setInt(3, produit.getQte());
            pstmt.setString(4, produit.getMarque());
            pstmt.setString(5, produit.getDateExpiration());
            pstmt.setDouble(6, produit.getPrix());
            pstmt.setString(7, details.toString());

            // Exécuter la requête
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produit ajouté avec succès !");
            } else {
                System.out.println("Aucun produit n'a été ajouté.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du produit dans la base de données:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erreur inattendue:");
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
                String type = rs.getString("type");
                JSONObject details = new JSONObject(rs.getString("details"));

                switch (type) {
                    case "cosmetique":
                        produits.add(new Cosmetique(
                                rs.getString("idProduit"),
                                rs.getInt("qte"),
                                rs.getString("marque"),
                                rs.getString("dateExpiration"),
                                jsonArrayToList(details.getJSONArray("typeDePeau")),
                                jsonArrayToList(details.getJSONArray("allergene")),
                                details.getString("durreeConservation"),
                                rs.getDouble("prix")
                        ));
                        break;
                    case "menager":
                        produits.add(new Menager(
                                rs.getString("idProduit"),
                                rs.getInt("qte"),
                                rs.getString("marque"),
                                rs.getString("dateExpiration"),
                                details.getString("emballage"),
                                details.getString("efficacite"),
                                details.getString("securite"),
                                rs.getDouble("prix")
                        ));
                        break;
                    case "alimentaire":
                        produits.add(new Alimentaire(
                                rs.getString("idProduit"),
                                rs.getInt("qte"),
                                rs.getString("marque"),
                                rs.getString("dateExpiration"),
                                details.getString("valuerNutritionnelle"),
                                jsonArrayToList(details.getJSONArray("ingredients")),
                                details.getString("certification"),
                                rs.getDouble("prix")
                        ));
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }

    private List<String> jsonArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }

    public void supprimerProduit() {
        List<Produit> produits = chargerProduits();
        Scanner sc = new Scanner(System.in);
        int index = sc.nextInt();

        if (index >= 0 && index < produits.size()) {
            String sql = "DELETE FROM produits WHERE idProduit = ?";

            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, produits.get(index).getIdProduit());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void modifierProduit(Produit produit) {
        String sql = "UPDATE produits SET qte = ?, marque = ?, dateExpiration = ?, prix = ?, details = ? WHERE idProduit = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            JSONObject details = new JSONObject();
            String type = "";

            if (produit instanceof Cosmetique) {
                Cosmetique cosmetique = (Cosmetique) produit;
                details.put("typeDePeau", new JSONArray(cosmetique.getTypeDePeau()));
                details.put("allergene", new JSONArray(cosmetique.getAllergene()));
                details.put("durreeConservation", cosmetique.getDurreeConservation());
            } else if (produit instanceof Menager) {
                Menager menager = (Menager) produit;
                details.put("emballage", menager.getEmballage());
                details.put("efficacite", menager.getEfficacite());
                details.put("securite", menager.getSecurite());
            } else if (produit instanceof Alimentaire) {
                Alimentaire alimentaire = (Alimentaire) produit;
                details.put("valuerNutritionnelle", alimentaire.getValuerNutritionnelle());
                details.put("ingredients", new JSONArray(alimentaire.getIngredients()));
                details.put("certification", alimentaire.getCertification());
            }

            pstmt.setInt(1, produit.getQte());
            pstmt.setString(2, produit.getMarque());
            pstmt.setString(3, produit.getDateExpiration());
            pstmt.setDouble(4, produit.getPrix());
            pstmt.setString(5, details.toString());
            pstmt.setString(6, produit.getIdProduit());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}