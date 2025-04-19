package org.example;

import org.example.permanent.ProduitManager;
import org.example.produit.Produit;
import org.example.utilisateur.Administrateur;
import org.example.utilisateur.Caissier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;
        boolean run = true;
        List<Caissier> caissiers = new ArrayList<>();
        ProduitManager produitManager = new ProduitManager();
        Administrateur adm1 = new Administrateur("Glodie", "glodielukose", "0856888233", "1", "M", "1234");

        do {
            System.out.println("-------------------------------------\n" +
                    "\t\t MENU DE CONNEXION\n\n" +
                    "1. Se Connecter en tant qu'administrateur\n" +
                    "2. Se Connecter en tant que caissier\n" +
                    "3. Quitter\n\n" +
                    "----------------------------------------");
            option = sc.nextInt();
            sc.nextLine(); // Pour consommer le retour à la ligne

            switch (option) {
                case 1:
                    adm1.seConnecter();
                    boolean adminMenu = true;
                    while (adminMenu) {
                        System.out.println("-------------------------------------\n" +
                                "\t\t MENU ADMINISTRATEUR\n\n" +
                                "1. Gerer Produits\n" +
                                "2. Gerer Comptes\n" +
                                "3. Retour\n\n" +
                                "----------------------------------------");
                        option = sc.nextInt();
                        sc.nextLine();

                        switch (option) {
                            case 1:
                                boolean produitMenu = true;
                                while (produitMenu) {
                                    System.out.println("------------------------------------------\n" +
                                            "\t\t GESTION DES PRODUITS\n\n" +
                                            "1. Ajouter Produit\n" +
                                            "2. Supprimer Produit\n" +
                                            "3. Mettre à jour Produit\n" +
                                            "4. Afficher Produits\n" +
                                            "5. Retour\n\n" +
                                            "----------------------------------------");
                                    option = sc.nextInt();
                                    sc.nextLine();

                                    switch (option) {
                                        case 1:
                                            System.out.println("-----------------------------------\n" +
                                                    "\t\t AJOUT DES PRODUITS\n\n" +
                                                    "1. Ajouter produit Cosmetique\n" +
                                                    "2. Ajouter produit Menager\n" +
                                                    "3. Ajouter produit Alimentation\n" +
                                                    "4. Retour\n\n" +
                                                    "----------------------------------------");
                                            option = sc.nextInt();
                                            sc.nextLine();

                                            switch (option) {
                                                case 1:
                                                    produitManager.ajouterProduit(adm1.ajouterCosmetique());
                                                    break;
                                                case 2:
                                                    produitManager.ajouterProduit(adm1.ajouterMenager());
                                                    break;
                                                case 3:
                                                    produitManager.ajouterProduit(adm1.ajouterAlimentaire());
                                                    break;
                                                case 4:
                                                    break;
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Liste des produits:");
                                            List<Produit> produits = produitManager.chargerProduits();
                                            for (int i = 0; i < produits.size(); i++) {
                                                System.out.println(i + ". " + produits.get(i));
                                            }
                                            System.out.println("Entrez l'index du produit à supprimer:");

                                            produitManager.supprimerProduit();
                                            break;
                                        case 3:
                                            System.out.println("Liste des produits:");
                                            produits = produitManager.chargerProduits();
                                            for (int i = 0; i < produits.size(); i++) {
                                                System.out.println(i + ". " + produits.get(i));
                                            }

                                            sc.nextLine();
                                            System.out.println("1. Modifier produit Cosmetique\n" +
                                                    "2. Modifier produit Menager\n" +
                                                    "3. Modifier produit Alimentation");
                                            option = sc.nextInt();
                                            sc.nextLine();
                                            switch (option) {
                                                case 1:
                                                    produitManager.modifierProduit(adm1.ajouterCosmetique());
                                                    break;
                                                case 2:
                                                    produitManager.modifierProduit(adm1.ajouterMenager());
                                                    break;
                                                case 3:
                                                    produitManager.modifierProduit(adm1.ajouterAlimentaire());
                                                    break;
                                            }
                                            break;
                                        case 4:
                                            System.out.println(produitManager.chargerProduits());
                                            break;
                                        case 5:
                                            produitMenu = false;
                                            break;
                                    }
                                }
                                break;
                            case 2:
                                boolean compteMenu = true;
                                while (compteMenu) {
                                    System.out.println("1. Ajouter Caissier\n" +
                                            "2. Supprimer Caissier\n" +
                                            "3. Mettre à jour Caissier\n" +
                                            "4. Retour");
                                    option = sc.nextInt();
                                    sc.nextLine();

                                    switch (option) {
                                        case 1:
                                            Caissier new_caissier = adm1.ajouterCaissier();
                                            caissiers.add(new_caissier);
                                            break;
                                        case 2:
                                            System.out.println("Entrez l'id du caissier à supprimer");
                                            String idCaissier = sc.nextLine();
                                            for (int i = 0; i < caissiers.size(); i++) {
                                                if (caissiers.get(i).getId().equals(idCaissier)) {
                                                    caissiers.remove(i);
                                                    System.out.println("Caissier supprimé avec succès");
                                                    break;
                                                }
                                            }
                                            break;
                                        case 3:
                                            System.out.println("Entrez l'id du caissier à modifier");
                                            String idCaissierModif = sc.nextLine();
                                            for (int i = 0; i < caissiers.size(); i++) {
                                                if (caissiers.get(i).getId().equals(idCaissierModif)) {
                                                    System.out.println("Entrez le nouveau nom");
                                                    String nom = sc.nextLine();
                                                    caissiers.get(i).getContact().setNom(nom);
                                                    System.out.println("Entrez le nouveau numéro de téléphone");
                                                    String numTel = sc.nextLine();
                                                    caissiers.get(i).getContact().setNumTel(numTel);
                                                    System.out.println("Entrez le nouveau genre");
                                                    String genre = sc.nextLine();
                                                    caissiers.get(i).getContact().setGenre(genre);
                                                    System.out.println("Caissier modifié avec succès");
                                                    break;
                                                }
                                            }
                                            break;
                                        case 4:
                                            compteMenu = false;
                                            break;
                                    }
                                }
                                break;
                            case 3:
                                adminMenu = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Entrez votre id : ");
                    String idCaissier = sc.nextLine();
                    System.out.println("Entrez votre mot de passe : ");
                    String passwordCaissier = sc.nextLine();

                    boolean found = false;
                    for (Caissier caissier : caissiers) {
                        if (caissier.getId().equals(idCaissier) && caissier.getPassword().equals(passwordCaissier)) {
                            found = true;
                            System.out.println("Bienvenu " + caissier.getContact().getNom());
                            caissier.seConnecter();

                            boolean caissierMenu = true;
                            while (caissierMenu) {
                                System.out.println("-------------------------------------\n" +
                                        "\t\t MENU DE CAISSIER\n\n" +
                                        "1. Passer commande\n" +
                                        "2. Deconnexion\n\n" +
                                        "----------------------------------------");
                                option = sc.nextInt();
                                sc.nextLine();

                                switch (option) {
                                    case 1:
                                        System.out.println("Entrez l'id du produit à commander");
                                        String idProduit = sc.nextLine();
                                        // Implémentez la logique de commande ici
                                        break;
                                    case 2:
                                        caissierMenu = false;
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Identifiants incorrects ou compte inexistant");
                    }
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    run = false;
                    break;
                default:
                    System.out.println("Option invalide");
            }
        } while (run);
    }
}