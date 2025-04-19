package org.example.permanent;

import com.google.gson.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import org.example.utilisateur.Administrateur;
import org.example.utilisateur.Caissier;
import org.example.utilisateur.Utilisateur;

import java.io.*;
import java.util.*;

public class UtilisateurManager {
    private static final String FILE_PATH = "utilisateurs.json";
    private final Gson gson;

    public UtilisateurManager() {
        RuntimeTypeAdapterFactory<Utilisateur> typeFactory = RuntimeTypeAdapterFactory
                .of(Utilisateur.class, "type")
                .registerSubtype(Administrateur.class, "admin")
                .registerSubtype(Caissier.class, "caissier");

        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(typeFactory)
                .setPrettyPrinting()
                .create();
    }

    public List<Utilisateur> chargerUtilisateurs() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Utilisateur[] utilisateursArray = gson.fromJson(reader, Utilisateur[].class);
            return utilisateursArray != null ? new ArrayList<>(Arrays.asList(utilisateursArray)) : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void sauvegarderUtilisateurs(List<Utilisateur> utilisateurs) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(utilisateurs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        List<Utilisateur> utilisateurs = chargerUtilisateurs();
        utilisateurs.add(utilisateur);
        sauvegarderUtilisateurs(utilisateurs);
    }

    public boolean modifierUtilisateur(int index, Utilisateur nouvelUtilisateur) {
        List<Utilisateur> utilisateurs = chargerUtilisateurs();
        if (index >= 0 && index < utilisateurs.size()) {
            utilisateurs.set(index, nouvelUtilisateur);
            sauvegarderUtilisateurs(utilisateurs);
            return true;
        }
        return false;
    }

    public boolean supprimerUtilisateur(int index) {
        List<Utilisateur> utilisateurs = chargerUtilisateurs();
        if (index >= 0 && index < utilisateurs.size()) {
            utilisateurs.remove(index);
            sauvegarderUtilisateurs(utilisateurs);
            return true;
        }
        return false;
    }

    // Méthode alternative pour supprimer par référence d'objet
    public boolean supprimerUtilisateur(Utilisateur utilisateur) {
        List<Utilisateur> utilisateurs = chargerUtilisateurs();
        boolean removed = utilisateurs.remove(utilisateur);
        if (removed) {
            sauvegarderUtilisateurs(utilisateurs);
        }
        return removed;
    }

    // Méthode pour trouver un utilisateur par son identifiant
    public Optional<Utilisateur> trouverUtilisateurParId(String id) {
        return chargerUtilisateurs().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    // Méthode pour trouver un utilisateur par son nom d'utilisateur
    public Optional<Utilisateur> trouverUtilisateurParUsername(String username) {
        return chargerUtilisateurs().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
}