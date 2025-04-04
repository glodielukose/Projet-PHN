public class Cosmetique extends Produit{
    private String[] TypeDePeau;
    private String[] Allergene;
    private String DurreeConservation;

    public Cosmetique(String idProduit, int qte, String marque, String dateExpiration, String[] typeDePeau, String[] allergene, String durreeConservation) {
        super(idProduit, qte, marque, dateExpiration);
        TypeDePeau = typeDePeau;
        Allergene = allergene;
        DurreeConservation = durreeConservation;
    }

    public String[] getAllergene() {
        return Allergene;
    }

    public void setAllergene(String[] allergene) {
        Allergene = allergene;
    }

    public String getDurreeConservation() {
        return DurreeConservation;
    }

    public void setDurreeConservation(String durreeConservation) {
        DurreeConservation = durreeConservation;
    }

    public String[] getTypeDePeau() {
        return TypeDePeau;
    }

    public void setTypeDePeau(String[] typeDePeau) {
        TypeDePeau = typeDePeau;
    }
}
