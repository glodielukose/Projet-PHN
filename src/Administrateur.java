public class Administrateur extends Utilisateur{
    public Administrateur(String nom, String numTel, String id, String genre, String password) {
        super(new Contact(nom, numTel, id, genre), password);


    }

    public void AfficherAdmnistrateur(){
        System.out.println(super.getContact());
    }
}
