import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option ;

//        Contact contactAdm = new Contact();

        //adm1.AfficherAdmnistrateur();

//        seConnecter();
        Administrateur adm1 = new Administrateur("Glodie", "0856888233", "1", "M", "1234");
        //
        System.out.println("1. Se Connecter en tant qu'administrateur" +
                "\n2. Se Connecter en tant que caissier");
        option = sc.nextInt();
        switch (option){
            case 1 :
                adm1.seConnecter();
                System.out.println("1. Gerer Produits" +
                        "\n2. Gerer Comptes");
                option = sc.nextInt();
                switch (option){
                    case 1 :
                        System.out.println("1. Ajouter Produit" +
                                "\n2. Supprimer Produit" +
                                "\n3. Mettre à jour Produit"
                        );

                        option = sc.nextInt();
                        switch (option) {
                            case 1:
                                System.out.println("1. Ajouter produit Cosmetique" +
                                        "\n2. Ajouter produit Menager" +
                                        "\n3. Ajouter produit Alimentation"
                                );
                            break;
                            case 2:
                                System.out.println("1. Supprimer produit Cosmetique" +
                                        "\n2. Supprimer produitMenager" +
                                        "\n3. Supprimer produitAlimentation"
                                );
                            break;
                            case 3:
                                System.out.println("1. Modifier produit Cosmetique" +
                                        "\n2. Modifier produitMenager" +
                                        "\n3. Modifier produitAlimentation"
                                );
                            break;
                        }
                    break;
                    case 2:
                        System.out.println("1. Ajouter Caissier" +
                                "\n2. Supprimer Caissier" +
                                "\n3. Mettre à jour Caissier"
                        );
                }
                break;
            case 2 :
                System.out.println("1. Passer commandes");
                break;
        }
    }


}