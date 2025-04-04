import java.util.List;

public class Alimentaire extends Produit{
    private String ValuerNutritionnelle;
    private List<String> Ingredients;
    private String Certification;

    public Alimentaire(String idProduit, int qte, String marque, String dateExpiration, String valuerNutritionnelle, List<String> ingredients, String certification) {
        super(idProduit, qte, marque, dateExpiration);
        ValuerNutritionnelle = valuerNutritionnelle;
        Ingredients = ingredients;
        Certification = certification;
    }

    public String getValuerNutritionnelle() {
        return ValuerNutritionnelle;
    }

    public void setValuerNutritionnelle(String valuerNutritionnelle) {
        ValuerNutritionnelle = valuerNutritionnelle;
    }

    public List<String> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        Ingredients = ingredients;
    }

    public String getCertification() {
        return Certification;
    }

    public void setCertification(String certification) {
        Certification = certification;
    }
}
