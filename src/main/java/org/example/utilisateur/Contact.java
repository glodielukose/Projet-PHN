package org.example.utilisateur;

public class Contact {
    private String Nom ;
    private String NumTel ;
    private String Genre ;

    public Contact(){}

    public Contact(String nom, String numTel, String genre) {
        Nom = nom;
        NumTel = numTel;
        Genre = genre;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getNumTel() {
        return NumTel;
    }

    public void setNumTel(String numTel) {
        NumTel = numTel;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "Nom='" + Nom + '\'' +
                ", NumTel='" + NumTel + '\'' +
                ", Genre='" + Genre + '\'' +
                '}';
    }
}
