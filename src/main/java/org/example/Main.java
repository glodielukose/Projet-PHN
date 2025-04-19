package org.example;

import org.example.permanent.ProduitManager;
import org.example.permanent.UtilisateurManager;
import org.example.produit.Produit;
import org.example.utilisateur.Administrateur;
import org.example.utilisateur.Caissier;
import org.example.utilisateur.Utilisateur;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialisation de la base de données
        DatabaseManager.initializeDatabase();

        Scanner sc = new Scanner(System.in);
        int option;
        boolean run = true;
        ProduitManager produitManager = new ProduitManager();
        UtilisateurManager utilisateurManager = new UtilisateurManager();

        // Création de l'admin par défaut
        Administrateur adm1 = new Administrateur("Glodie", "glodielukose", "0856888233", "1", "M", "1234");

        // Vérifier si l'admin existe déjà, sinon l'ajouter
        if (utilisateurManager.trouverUtilisateurParId("1").isEmpty()) {
            utilisateurManager.ajouterUtilisateur(adm1);
        }

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
                    menuAdministrateur(adm1, produitManager, utilisateurManager, sc);
                    break;
                case 2:
                    menuCaissier(produitManager, utilisateurManager, sc);
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    run = false;
                    break;
                default:
                    System.out.println("Option invalide");
            }
        } while (run);

        sc.close();
    }

    private static void menuAdministrateur(Administrateur admin, ProduitManager produitManager,
                                           UtilisateurManager utilisateurManager, Scanner sc) {
        boolean adminMenu = true;
        while (adminMenu) {
            System.out.println("-------------------------------------\n" +
                    "\t\t MENU ADMINISTRATEUR\n\n" +
                    "1. Gerer Produits\n" +
                    "2. Gerer Comptes\n" +
                    "3. Retour\n\n" +
                    "----------------------------------------");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    menuGestionProduits(admin, produitManager, sc);
                    break;
                case 2:
                    menuGestionComptes(admin, utilisateurManager, sc);
                    break;
                case 3:
                    adminMenu = false;
                    break;
                default:
                    System.out.println("Option invalide");
            }
        }
    }

    private static void menuGestionProduits(Administrateur admin, ProduitManager produitManager, Scanner sc) {
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
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    menuAjoutProduit(admin, produitManager, sc);
                    break;
                case 2:
                    supprimerProduit(produitManager, sc);
                    break;
                case 3:
                    modifierProduit(admin, produitManager, sc);
                    break;
                case 4:
                    afficherProduits(produitManager);
                    break;
                case 5:
                    produitMenu = false;
                    break;
                default:
                    System.out.println("Option invalide");
            }
        }
    }

    private static void menuAjoutProduit(Administrateur admin, ProduitManager produitManager, Scanner sc) {
        System.out.println("-----------------------------------\n" +
                "\t\t AJOUT DES PRODUITS\n\n" +
                "1. Ajouter produit Cosmetique\n" +
                "2. Ajouter produit Menager\n" +
                "3. Ajouter produit Alimentation\n" +
                "4. Retour\n\n" +
                "----------------------------------------");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1:
                produitManager.ajouterProduit(admin.ajouterCosmetique());
                System.out.println("Produit cosmétique ajouté avec succès !");
                break;
            case 2:
                produitManager.ajouterProduit(admin.ajouterMenager());
                System.out.println("Produit ménager ajouté avec succès !");
                break;
            case 3:
                produitManager.ajouterProduit(admin.ajouterAlimentaire());
                System.out.println("Produit alimentaire ajouté avec succès !");
                break;
            case 4:
                break;
            default:
                System.out.println("Option invalide");
        }
    }

    private static void supprimerProduit(ProduitManager produitManager, Scanner sc) {
        System.out.println("Liste des produits:");
        List<Produit> produits = produitManager.chargerProduits();
        for (int i = 0; i < produits.size(); i++) {
            System.out.println(i + ". " + produits.get(i).getIdProduit() + " - " + produits.get(i).getMarque());
        }
        System.out.println("Entrez l'index du produit à supprimer:");

        produitManager.supprimerProduit();
        System.out.println("Produit supprimé avec succès !");
    }

    private static void modifierProduit(Administrateur admin, ProduitManager produitManager, Scanner sc) {
        System.out.println("Liste des produits:");
        List<Produit> produits = produitManager.chargerProduits();
        for (int i = 0; i < produits.size(); i++) {
            System.out.println(i + ". " + produits.get(i).getIdProduit() + " - " + produits.get(i).getMarque());
        }

        System.out.println("Entrez l'index du produit à modifier:");
        int index = sc.nextInt();
        sc.nextLine();

        System.out.println("1. Modifier produit Cosmetique\n" +
                "2. Modifier produit Menager\n" +
                "3. Modifier produit Alimentation");
        int type = sc.nextInt();
        sc.nextLine();

        Produit produitModifie = null;
        switch (type) {
            case 1:
                produitModifie = admin.ajouterCosmetique();
                break;
            case 2:
                produitModifie = admin.ajouterMenager();
                break;
            case 3:
                produitModifie = admin.ajouterAlimentaire();
                break;
            default:
                System.out.println("Option invalide");
                return;
        }

        produitManager.modifierProduit(produitModifie);
        System.out.println("Produit modifié avec succès !");
    }

    private static void afficherProduits(ProduitManager produitManager) {
        List<Produit> produits = produitManager.chargerProduits();
        if (produits.isEmpty()) {
            System.out.println("Aucun produit enregistré.");
        } else {
            System.out.println("----- LISTE DES PRODUITS -----");
            for (Produit p : produits) {
                System.out.println(p);
                System.out.println("-----------------------------");
            }
        }
    }

    private static void menuGestionComptes(Administrateur admin, UtilisateurManager utilisateurManager, Scanner sc) {
        boolean compteMenu = true;
        while (compteMenu) {
            System.out.println("1. Ajouter Caissier\n" +
                    "2. Supprimer Caissier\n" +
                    "3. Mettre à jour Caissier\n" +
                    "4. Afficher Caissiers\n" +
                    "5. Retour");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    Caissier newCaissier = admin.ajouterCaissier();
                    utilisateurManager.ajouterUtilisateur(newCaissier);
                    System.out.println("Caissier ajouté avec succès !");
                    break;
                case 2:
                    supprimerCaissier(utilisateurManager, sc);
                    break;
                case 3:
                    modifierCaissier(admin, utilisateurManager, sc);
                    break;
                case 4:
                    afficherCaissiers(utilisateurManager);
                    break;
                case 5:
                    compteMenu = false;
                    break;
                default:
                    System.out.println("Option invalide");
            }
        }
    }

    private static void supprimerCaissier(UtilisateurManager utilisateurManager, Scanner sc) {
        System.out.println("Liste des caissiers:");
        List<Utilisateur> caissiers = utilisateurManager.chargerUtilisateurs()
                .stream()
                .filter(u -> u instanceof Caissier)
                .toList();

        for (int i = 0; i < caissiers.size(); i++) {
            System.out.println(i + ". " + caissiers.get(i).getUsername());
        }

        System.out.println("Entrez l'index du caissier à supprimer:");
        int index = sc.nextInt();
        sc.nextLine();

        if (index >= 0 && index < caissiers.size()) {
            utilisateurManager.supprimerUtilisateur(index);
            System.out.println("Caissier supprimé avec succès !");
        } else {
            System.out.println("Index invalide !");
        }
    }

    private static void modifierCaissier(Administrateur admin, UtilisateurManager utilisateurManager, Scanner sc) {
        System.out.println("Liste des caissiers:");
        List<Utilisateur> caissiers = utilisateurManager.chargerUtilisateurs()
                .stream()
                .filter(u -> u instanceof Caissier)
                .toList();

        for (int i = 0; i < caissiers.size(); i++) {
            System.out.println(i + ". " + caissiers.get(i).getUsername());
        }

        System.out.println("Entrez l'index du caissier à modifier:");
        int index = sc.nextInt();
        sc.nextLine();

        if (index >= 0 && index < caissiers.size()) {
            Caissier updatedCaissier = admin.ajouterCaissier();
            utilisateurManager.modifierUtilisateur(index, updatedCaissier);
            System.out.println("Caissier modifié avec succès !");
        } else {
            System.out.println("Index invalide !");
        }
    }

    private static void afficherCaissiers(UtilisateurManager utilisateurManager) {
        List<Utilisateur> caissiers = utilisateurManager.chargerUtilisateurs()
                .stream()
                .filter(u -> u instanceof Caissier)
                .toList();

        if (caissiers.isEmpty()) {
            System.out.println("Aucun caissier enregistré.");
        } else {
            System.out.println("----- LISTE DES CAISSIERS -----");
            for (Utilisateur c : caissiers) {
                System.out.println("Nom: " + c.getContact().getNom());
                System.out.println("Username: " + c.getUsername());
                System.out.println("Téléphone: " + c.getContact().getNumTel());
                System.out.println("-----------------------------");
            }
        }
    }

    private static void menuCaissier(ProduitManager produitManager, UtilisateurManager utilisateurManager, Scanner sc) {
        System.out.println("Entrez votre id : ");
        String idCaissier = sc.nextLine();

        System.out.println("Entrez votre mot de passe : ");
        String passwordCaissier = sc.nextLine();

        Optional<Utilisateur> user = utilisateurManager.trouverUtilisateurParId(idCaissier);
        if (user.isPresent() && user.get() instanceof Caissier &&
                user.get().getPassword().equals(passwordCaissier)) {
            Caissier caissier = (Caissier) user.get();
            System.out.println("Bienvenue " + caissier.getContact().getNom());

            boolean caissierMenu = true;
            while (caissierMenu) {
                System.out.println("-------------------------------------\n" +
                        "\t\t MENU CAISSIER\n\n" +
                        "1. Voir les produits disponibles\n" +
                        "2. Effectuer une vente\n" +
                        "3. Retour\n\n" +
                        "----------------------------------------");
                int option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        afficherProduits(produitManager);
                        break;
                    case 2:
                        System.out.println("Fonctionnalité de vente à implémenter");
                        break;
                    case 3:
                        caissierMenu = false;
                        break;
                    default:
                        System.out.println("Option invalide");
                }
            }
        } else {
            System.out.println("Identifiants incorrects ou compte inexistant");
        }
    }
}