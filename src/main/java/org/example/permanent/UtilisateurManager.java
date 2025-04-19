package org.example.permanent;

import org.example.utilisateur.Administrateur;
import org.example.utilisateur.Caissier;
import org.example.utilisateur.Utilisateur;
import org.example.database.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilisateurManager {
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateurs(id, type, nom, username, numTel, genre, password, email, autres_details) VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, utilisateur.getId());
            pstmt.setString(2, utilisateur instanceof Administrateur ? "admin" : "caissier");
            pstmt.setString(3, utilisateur.getContact().getNom());
            pstmt.setString(4, utilisateur.getUsername());
            pstmt.setString(5, utilisateur.getContact().getNumTel());
            pstmt.setString(6, utilisateur.getContact().getGenre());
            pstmt.setString(7, utilisateur.getPassword());

            if (utilisateur instanceof Administrateur) {
                pstmt.setString(8, ((Administrateur) utilisateur).getEmail());
                pstmt.setString(9, null);
            } else {
                pstmt.setString(8, null);
                pstmt.setString(9, ((Caissier) utilisateur).getAutresDetails());
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Utilisateur> chargerUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String type = rs.getString("type");
                if ("admin".equals(type)) {
                    utilisateurs.add(new Administrateur(
                            rs.getString("nom"),
                            rs.getString("username"),
                            rs.getString("numTel"),
                            rs.getString("id"),
                            rs.getString("genre"),
                            rs.getString("password"),
                            rs.getString("email")
                    ));
                } else {
                    utilisateurs.add(new Caissier(
                            rs.getString("nom"),
                            rs.getString("username"),
                            rs.getString("numTel"),
                            rs.getString("id"),
                            rs.getString("genre"),
                            rs.getString("password"),
                            rs.getString("autres_details")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public boolean modifierUtilisateur(int index, Utilisateur nouvelUtilisateur) {
        List<Utilisateur> utilisateurs = chargerUtilisateurs();
        if (index < 0 || index >= utilisateurs.size()) {
            return false;
        }

        String id = utilisateurs.get(index).getId();
        String sql = "UPDATE utilisateurs SET nom=?, username=?, numTel=?, genre=?, password=?, email=?, autres_details=? WHERE id=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nouvelUtilisateur.getContact().getNom());
            pstmt.setString(2, nouvelUtilisateur.getUsername());
            pstmt.setString(3, nouvelUtilisateur.getContact().getNumTel());
            pstmt.setString(4, nouvelUtilisateur.getContact().getGenre());
            pstmt.setString(5, nouvelUtilisateur.getPassword());

            if (nouvelUtilisateur instanceof Administrateur) {
                pstmt.setString(6, ((Administrateur) nouvelUtilisateur).getEmail());
                pstmt.setString(7, null);
            } else {
                pstmt.setString(6, null);
                pstmt.setString(7, ((Caissier) nouvelUtilisateur).getAutresDetails());
            }

            pstmt.setString(8, id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerUtilisateur(int index) {
        List<Utilisateur> utilisateurs = chargerUtilisateurs();
        if (index < 0 || index >= utilisateurs.size()) {
            return false;
        }

        String sql = "DELETE FROM utilisateurs WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, utilisateurs.get(index).getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Utilisateur> trouverUtilisateurParId(String id) {
        String sql = "SELECT * FROM utilisateurs WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String type = rs.getString("type");
                if ("admin".equals(type)) {
                    return Optional.of(new Administrateur(
                            rs.getString("nom"),
                            rs.getString("username"),
                            rs.getString("numTel"),
                            rs.getString("id"),
                            rs.getString("genre"),
                            rs.getString("password"),
                            rs.getString("email")
                    ));
                } else {
                    return Optional.of(new Caissier(
                            rs.getString("nom"),
                            rs.getString("username"),
                            rs.getString("numTel"),
                            rs.getString("id"),
                            rs.getString("genre"),
                            rs.getString("password"),
                            rs.getString("autres_details")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}