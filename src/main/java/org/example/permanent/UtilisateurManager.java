package org.example.permanent;

import org.example.DatabaseManager;
import org.example.utilisateur.Contact;
import org.example.utilisateur.Utilisateur;
import org.example.utilisateur.Caissier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilisateurManager {
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateurs(id, nom, username, numTel, genre, password, role) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Remplir les paramètres de la requête
            pstmt.setString(1, utilisateur.getId());
            pstmt.setString(2, utilisateur.getContact().getNom());
            pstmt.setString(3, utilisateur.getUsername());
            pstmt.setString(4, utilisateur.getContact().getNumTel());
            pstmt.setString(5, utilisateur.getContact().getGenre());
            pstmt.setString(6, utilisateur.getPassword());

            // Déterminer le rôle (Caissier ou Administrateur)
            String role = (utilisateur instanceof Caissier) ? "caissier" : "admin";
            pstmt.setString(7, role);

            // Exécuter la requête
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Utilisateur ajouté avec succès !");
            } else {
                System.out.println("Aucun utilisateur n'a été ajouté.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'utilisateur dans la base de données:");
            e.printStackTrace();

            // Gestion spécifique des contraintes d'unicité
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.err.println("Erreur: Un utilisateur avec cet ID existe déjà.");
            }
        }
    }

    public List<Utilisateur> chargerUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String role = rs.getString("role");
                Contact contact = new Contact(
                        rs.getString("nom"),
                        rs.getString("numTel"),
                        rs.getString("genre")
                );

                if ("caissier".equals(role)) {
                    utilisateurs.add(new Caissier(
                            contact.getNom(),
                            rs.getString("username"),
                            contact.getNumTel(),
                            rs.getString("id"),
                            contact.getGenre(),
                            rs.getString("password")
                    ));
                } else {
                    // Gérer les administrateurs si nécessaire
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }

    public void supprimerUtilisateur(int index) {
        List<Utilisateur> utilisateurs = chargerUtilisateurs();
        if (index >= 0 && index < utilisateurs.size()) {
            String sql = "DELETE FROM utilisateurs WHERE id = ?";

            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, utilisateurs.get(index).getId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void modifierUtilisateur(int index, Utilisateur nouvelUtilisateur) {
        List<Utilisateur> utilisateurs = chargerUtilisateurs();
        if (index >= 0 && index < utilisateurs.size()) {
            String sql = "UPDATE utilisateurs SET nom = ?, username = ?, numTel = ?, genre = ?, password = ? WHERE id = ?";

            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, nouvelUtilisateur.getContact().getNom());
                pstmt.setString(2, nouvelUtilisateur.getUsername());
                pstmt.setString(3, nouvelUtilisateur.getContact().getNumTel());
                pstmt.setString(4, nouvelUtilisateur.getContact().getGenre());
                pstmt.setString(5, nouvelUtilisateur.getPassword());
                pstmt.setString(6, utilisateurs.get(index).getId());

                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Optional<Utilisateur> trouverUtilisateurParId(String id) {
        String sql = "SELECT * FROM utilisateurs WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                Contact contact = new Contact(
                        rs.getString("nom"),
                        rs.getString("numTel"),
                        rs.getString("genre")
                );

                if ("caissier".equals(role)) {
                    return Optional.of(new Caissier(
                            contact.getNom(),
                            rs.getString("username"),
                            contact.getNumTel(),
                            rs.getString("id"),
                            contact.getGenre(),
                            rs.getString("password")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}