package org.example.utilisateur;

import org.example.produit.Alimentaire;
import org.example.produit.Cosmetique;
import org.example.produit.Menager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Administrateur extends Utilisateur {
    public Administrateur(String nom, String username, String numTel, String id, String genre, String password) {
        super(new Contact(nom, numTel, genre), password, id, username);


    }

    public void AfficherAdmnistrateur() {
        System.out.println(super.getContact());
    }

    public Caissier ajouterCaissier() {
        Scanner sc = new Scanner(System.in);

        String nom;
        String numTel;
        String id;
        String genre;
        String password;

        System.out.print("Entrez le numero de Tel : ");
        numTel = sc.nextLine();

        System.out.print("Entrez l'id : ");
        id = sc.nextLine();

        System.out.print("Entrez le genre : ");
        genre = sc.nextLine();

        System.out.print("Entrez le mot de passe : ");
        password = sc.nextLine();

        System.out.print("Entrez le nom : ");
        nom = sc.nextLine();

        System.out.print("Entrez le nom d'utilisateur : ");
        String username = sc.nextLine();

        System.out.println("Le caissier a ete ajoute avec succes");

        return new Caissier(nom, numTel, genre, password, id, username);
    }

    public Cosmetique ajouterCosmetique() {
        Scanner sc = new Scanner(System.in);
        List<String> typeDePeau = new ArrayList<>();
        String peau;
        List<String> allergene = new ArrayList<>();
        String allergie;
        int qte;
        String marque;
        String idProduit;
        String dateExpiration;
        String durreeConservation;
        double prix; // Ajout de la variable prix

        System.out.print("Entrez l'id du produit : ");
        idProduit = sc.nextLine();

        System.out.print("Entrez la quantite : ");
        qte = sc.nextInt();
        sc.nextLine();

        System.out.print("Entrez la marque : ");
        marque = sc.nextLine();

        System.out.print("Entrez la date d'expiration : ");
        dateExpiration = sc.nextLine();

        System.out.print("Entrez la durree de conservation : ");
        durreeConservation = sc.nextLine();

        System.out.print("Entrez le prix : "); // Nouvelle ligne pour le prix
        prix = sc.nextDouble();
        sc.nextLine();

        System.out.println("Entrez les types de peau");
        for (int i = 1; i < 4; i++) {
            System.out.print("peau" + i + " : ");
            peau = sc.nextLine();
            typeDePeau.add(peau);
        }

        System.out.println("Entrez les allergies");
        for (int i = 1; i < 4; i++) {
            System.out.print("Allergie " + i + " : ");
            allergie = sc.nextLine();
            allergene.add(allergie);
        }

        return new Cosmetique(idProduit, qte, marque, dateExpiration, typeDePeau, allergene, durreeConservation, prix);
    }

    public Alimentaire ajouterAlimentaire() {
        Scanner sc = new Scanner(System.in);
        String valuerNutritionnelle;
        List<String> ingredients = new ArrayList<>();
        String ingredient;
        String certification;
        int qte;
        String marque;
        String idProduit;
        String dateExpiration;
        double prix; // Ajout de la variable prix

        System.out.println("Entrez l'id du produit");
        idProduit = sc.nextLine();

        System.out.println("Entrez la quantite");
        qte = sc.nextInt();
        sc.nextLine();

        System.out.println("Entrez la marque");
        marque = sc.nextLine();

        System.out.println("Entrez la date d'expiration");
        dateExpiration = sc.nextLine();

        System.out.println("Entrez le prix"); // Nouvelle ligne pour le prix
        prix = sc.nextDouble();
        sc.nextLine();

        System.out.println("Entrez la certification");
        certification = sc.nextLine();

        System.out.println("Entrez la valeur nutritionnelle");
        valuerNutritionnelle = sc.nextLine();

        System.out.println("Entrez les ingredients");
        for (int i = 1; i < 4; i++) {
            System.out.print("ingredient" + i + " : ");
            ingredient = sc.nextLine();
            ingredients.add(ingredient);
        }

        return new Alimentaire(idProduit, qte, marque, dateExpiration,
                valuerNutritionnelle, ingredients, certification, prix);
    }

    public Menager ajouterMenager() {
        Scanner sc = new Scanner(System.in);
        String efficacite;
        String securite;
        String emballage;
        int qte;
        String marque;
        String idProduit;
        String dateExpiration;
        double prix; // Ajout de la variable prix

        System.out.println("Entrez l'id du produit");
        idProduit = sc.nextLine();

        System.out.println("Entrez la quantite");
        qte = sc.nextInt();
        sc.nextLine();

        System.out.println("Entrez la marque");
        marque = sc.nextLine();

        System.out.println("Entrez la date d'expiration");
        dateExpiration = sc.nextLine();

        System.out.println("Entrez le prix"); // Nouvelle ligne pour le prix
        prix = sc.nextDouble();
        sc.nextLine();

        System.out.println("Entrez l'emballage");
        emballage = sc.nextLine();

        System.out.println("Entrez l'efficacite");
        efficacite = sc.nextLine();

        System.out.println("Entrez la securite");
        securite = sc.nextLine();

        return new Menager(idProduit, qte, marque, dateExpiration,
                emballage, efficacite, securite, prix);
    }
}

