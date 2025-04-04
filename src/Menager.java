import java.lang.invoke.StringConcatFactory;

public class Menager extends Produit{
    private String Emballage;
    private String Efficacite;
    private String Securite;

    public Menager(String idProduit, int qte, String marque, String dateExpiration, String emballage, String efficacite, String securite) {
        super(idProduit, qte, marque, dateExpiration);
        Emballage = emballage;
        Efficacite = efficacite;
        Securite = securite;
    }

    public String getEmballage() {
        return Emballage;
    }

    public void setEmballage(String emballage) {
        Emballage = emballage;
    }

    public String getEfficacite() {
        return Efficacite;
    }

    public void setEfficacite(String efficacite) {
        Efficacite = efficacite;
    }

    public String getSecurite() {
        return Securite;
    }

    public void setSecurite(String securite) {
        Securite = securite;
    }
}
