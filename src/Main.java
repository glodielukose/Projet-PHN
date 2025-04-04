import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option ;
        boolean run = true;
        List <Caissier> caissiers  = new ArrayList<>();
        List <Menager> menagers = new ArrayList<>();
        List <Cosmetique> cosmetiques = new ArrayList<>();
        List <Alimentaire> alimentaires = new ArrayList<>();


//        Contact contactAdm = new Contact();

        //adm1.AfficherAdmnistrateur();

//        seConnecter();
        Administrateur adm1 = new Administrateur("Glodie", "0856888233", "1", "M", "1234");
        //
        do {
            System.out.println("-------------------------------------" +
                    "\n\t\t MENU DE CONNEXION" +
                    "\n\n1. Se Connecter en tant qu'administrateur" +
                    "\n2. Se Connecter en tant que caissier" +
                    "\n3. Quitter" +
                    "\n\n----------------------------------------");
            option = sc.nextInt();
            switch (option){
                case 1 :
                    adm1.seConnecter();
                    System.out.println("-------------------------------------" +
                            "\n\t\t MENU DE CONNEXION" +
                            "\n\n1. Gerer Produits" +
                            "\n2. Gerer Comptes" +
                            "\n\n----------------------------------------");

                    option = sc.nextInt();
                    switch (option){
                        case 1 :
                            System.out.println("------------------------------------------" +
                                    "\n\t\t GESTION DES PRODUITS" +
                                    "\n\n1. Ajouter Produit" +
                                    "\n2. Supprimer Produit" +
                                    "\n3. Mettre à jour Produit" +
                                    "\n4. Afficher Produits" +
                                    "\n\n----------------------------------------"
                            );

                            option = sc.nextInt();
                            switch (option) {
                                case 1:
                                    System.out.println("-----------------------------------" +
                                            "\n\t\t AJOUT DES PRODUITS" +
                                            "\n\n1. Ajouter produit Cosmetique" +
                                            "\n2. Ajouter produit Menager" +
                                            "\n3. Ajouter produit Alimentation" +
                                            "\n\n----------------------------------------"
                                    );
                                    option = sc.nextInt();
                                    switch (option) {
                                        case 1:
                                            cosmetiques.add(adm1.ajouterCosmetique());
                                            System.out.println("Produit ajouté avec succès");
                                            break;
                                        case 2:
                                            menagers.add(adm1.ajouterMenager());
                                            break;
                                        case 3:
                                            alimentaires.add(adm1.ajouterAlimentaire());
                                            break;
                                    }
                                    break;
                                case 2:
                                    System.out.println("1. Supprimer produit Cosmetique" +
                                            "\n2. Supprimer produitMenager" +
                                            "\n3. Supprimer produitAlimentation"
                                    );
                                    option = sc.nextInt();
                                    switch (option){
                                        case 1:
                                            System.out.println("Entrez l'id du produit à supprimer");
                                            String idProduit = sc.nextLine();
                                            for (int i = 0; i < cosmetiques.size(); i++) {
                                                if (cosmetiques.get(i).getIdProduit().equals(idProduit)){
                                                    cosmetiques.remove(i);
                                                }
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Entrez l'id du produit à supprimer");
                                            String idProduitMenager = sc.nextLine();
                                            for (int i = 0; i < menagers.size(); i++) {
                                                if (menagers.get(i).getIdProduit().equals(idProduitMenager)){
                                                    menagers.remove(i);
                                                }
                                            }
                                            break;
                                        case 3:
                                            System.out.println("Entrez l'id du produit à supprimer");
                                            String idProduitAlimentaire = sc.nextLine();
                                            for (int i = 0; i < alimentaires.size(); i++) {
                                                if (alimentaires.get(i).getIdProduit().equals(idProduitAlimentaire)){
                                                    alimentaires.remove(i);
                                                }
                                            }

                                    }

                                    break;
                                case 3:
                                    System.out.println("1. Modifier produit Cosmetique" +
                                            "\n2. Modifier produitMenager" +
                                            "\n3. Modifier produitAlimentation"
                                    );
                                    break;
                                case 4:
                                    System.out.println("1. Afficher produit Cosmetique" +
                                            "\n2. Afficher produitMenager" +
                                            "\n3. Afficher produitAlimentation"
                                    );
                                    option = sc.nextInt();
                                    switch (option){
                                        case 1:
                                            for (int i = 0; i < cosmetiques.size(); i++) {
                                                cosmetiques.get(i).afficherProduit();
                                            }
                                            break;
                                        case 2:
                                            for (int i = 0; i < menagers.size(); i++) {
                                                menagers.get(i).afficherProduit();
                                            }
                                            break;
                                        case 3:
                                            for (int i = 0; i < alimentaires.size(); i++) {
                                                alimentaires.get(i).afficherProduit();
                                            }
                                            break;
                                    }
                            }
                            break;
                        case 2:
                            System.out.println("1. Ajouter Caissier" +
                                    "\n2. Supprimer Caissier" +
                                    "\n3. Mettre à jour Caissier"
                            );
                            option = sc.nextInt();
                            switch (option){
                                case 1 :
                                    Caissier new_caissier = adm1.ajouterCaissier();
                                    caissiers.add(new_caissier);
                                    break;
                                case 2:
                                    System.out.println("Entrez l'id du caissier à supprimer");
                                    String idCaissier = sc.nextLine();
                                    for (int i = 0; i < caissiers.size(); i++) {
                                        if (caissiers.get(i).getContact().getId().equals(idCaissier)){
                                            caissiers.remove(i);
                                        }
                                    }
                                    break;
                                case 3:
                                    System.out.println("Entrez l'id du caissier à modifier");
                                    String idCaissierModif = sc.nextLine();
                                    for (int i = 0; i < caissiers.size(); i++) {
                                        if (caissiers.get(i).getContact().getId().equals(idCaissierModif)){
                                            System.out.println("Entrez le nouveau nom");
                                            String nom = sc.nextLine();
                                            caissiers.get(i).getContact().setNom(nom);
                                            System.out.println("Entrez le nouveau numéro de téléphone");
                                            String numTel = sc.nextLine();
                                            caissiers.get(i).getContact().setNumTel(numTel);
                                            System.out.println("Entrez le nouveau genre");
                                            String genre = sc.nextLine();
                                            caissiers.get(i).getContact().setGenre(genre);
                                        }
                                    }
                                    break;
                            }
                    }
                    break;
                case 2 :
                    System.out.println("Entrez votre id : ");
                    String idCaissier = sc.nextLine();
                    System.out.println("Entrez votre mot de passe : ");
                    String passwordCaissier = sc.nextLine();
                    for (int i = 0; i < caissiers.size(); i++) {
                        if (caissiers.get(i).getContact().getId().equals(idCaissier) && caissiers.get(i).getPassword().equals(passwordCaissier)){
                            System.out.println("Bienvenu " + caissiers.get(i).getContact().getNom());
                            caissiers.get(i).seConnecter();
                        }
                    }

                    System.out.println("-------------------------------------" +
                            "\n\t\t MENU DE CAISSIER" +
                            "\n\n1. Passer commande" +
                            "\n\n----------------------------------------");
                    option = sc.nextInt();
                    switch (option){
                        case 1 :
                            System.out.println("Saississez l'id du produit");

                    }

                    break;
                case 3:
                    System.out.println("Deconnexion");
                    run = false;
            }
        } while(run);
    }


}