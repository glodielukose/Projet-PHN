public class Caissier extends Utilisateur{
    public Caissier(String nom, String numTel, String id, String genre, String password) {
        super(new Contact(nom, numTel, id, genre), password);

    }
}
