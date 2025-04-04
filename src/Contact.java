public class Contact {
    private String Nom ;
    private String NumTel ;
    private String Id ;
    private String Genre ;

    public Contact(){}

    public Contact(String nom, String numTel, String id, String genre) {
        Nom = nom;
        NumTel = numTel;
        Id = id;
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
}
