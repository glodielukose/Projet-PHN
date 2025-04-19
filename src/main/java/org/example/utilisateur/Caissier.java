package org.example.utilisateur;

public class Caissier extends Utilisateur{
    public Caissier(String nom, String username, String numTel, String id, String genre, String password) {
        super(new Contact(nom, numTel, genre), password, id, username);
    }
}
