package org.example;

import org.example.permanent.ProduitManager;
import org.example.permanent.UtilisateurManager;
import org.example.produit.Produit;
import org.example.utilisateur.Administrateur;
import org.example.utilisateur.Caissier;
import org.example.utilisateur.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;
        boolean run = true;
        ProduitManager produitManager = new ProduitManager();
        UtilisateurManager utilisateurManager = new UtilisateurManager();
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
                                            Caissier newCaissier = adm1.ajouterCaissier();
                                            utilisateurManager.ajouterUtilisateur(newCaissier);
                                            System.out.println("Caissier ajouté avec succès !");
                                            break;
                                        case 2:
                                            System.out.println("Liste des caissiers:");
                                            List<Utilisateur> caissiers = utilisateurManager.chargerUtilisateurs();
                                            for (int i = 0; i < caissiers.size(); i++) {
                                                if (caissiers.get(i) instanceof Caissier) {
                                                    System.out.println(i + ". " + caissiers.get(i));
                                                }
                                            }
                                            System.out.println("Entrez l'index du caissier à supprimer:");
                                            int index = sc.nextInt();
                                            sc.nextLine();

                                            if (index >= 0 && index < caissiers.size() && caissiers.get(index) instanceof Caissier) {
                                                utilisateurManager.supprimerUtilisateur(index);
                                                System.out.println("Caissier supprimé avec succès !");
                                            } else {
                                                System.out.println("Index invalide !");
                                            }
                                            break;

                                        case 3: // Mettre à jour Caissier
                                            System.out.println("Liste des caissiers:");
                                            caissiers = utilisateurManager.chargerUtilisateurs();
                                            for (int i = 0; i < caissiers.size(); i++) {
                                                if (caissiers.get(i) instanceof Caissier) {
                                                    System.out.println(i + ". " + caissiers.get(i));
                                                }
                                            }
                                            System.out.println("Entrez l'index du caissier à modifier:");
                                            index = sc.nextInt();
                                            sc.nextLine();

                                            if (index >= 0 && index < caissiers.size() && caissiers.get(index) instanceof Caissier) {
                                                Caissier updatedCaissier = adm1.ajouterCaissier(); // On recrée un nouveau caissier
                                                utilisateurManager.modifierUtilisateur(index, updatedCaissier);
                                                System.out.println("Caissier modifié avec succès !");
                                            } else {
                                                System.out.println("Index invalide !");
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
                    System.out.println(idCaissier);
                    System.out.println("Entrez votre mot de passe : ");
                    String passwordCaissier = sc.nextLine();
                    System.out.println(passwordCaissier);

                    // Vérification avec UtilisateurManager
                    Optional<Utilisateur> user = utilisateurManager.trouverUtilisateurParId(idCaissier);
                    if (user.isPresent() && user.get() instanceof Caissier &&
                            ((Caissier)user.get()).getPassword().equals(passwordCaissier)) {
                        Caissier caissier = (Caissier)user.get();
                        System.out.println("Bienvenu " + caissier.getContact().getNom());
                        caissier.seConnecter();

                        boolean caissierMenu = true;
                        while (caissierMenu) {
                            // ... (menu caissier existant)
                        }
                    } else {
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