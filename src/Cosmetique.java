import java.util.ArrayList;
import java.util.List;

public class Cosmetique extends Produit{
    private List<String> TypeDePeau = new ArrayList<>();
    private List<String> Allergene = new ArrayList<>();
    private String DurreeConservation;

    public Cosmetique(String idProduit, int qte, String marque, String dateExpiration, List<String> typeDePeau, List<String> allergene, String durreeConservation) {
        super(idProduit, qte, marque, dateExpiration);
        TypeDePeau = typeDePeau;
        Allergene = allergene;
        DurreeConservation = durreeConservation;
    }

    public void setTypeDePeau(List<String> typeDePeau) {
        TypeDePeau = typeDePeau;
    }

    public List<String> getAllergene() {
        return Allergene;
    }

    public void setAllergene(List<String> allergene) {
        Allergene = allergene;
    }

    public String getDurreeConservation() {
        return DurreeConservation;
    }

    public void setDurreeConservation(String durreeConservation) {
        DurreeConservation = durreeConservation;
    }

    public List<String> getTypeDePeau() {
        return TypeDePeau;
    }
}
