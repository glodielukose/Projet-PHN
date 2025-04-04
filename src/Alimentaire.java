public class Alimentaire extends Produit{
    private String ValuerNutritionnelle;
    private String[] Ingredients;
    private String Certification;

    public Alimentaire(String idProduit, int qte, String marque, String dateExpiration, String valuerNutritionnelle, String[] ingredients, String certification) {
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

    public String[] getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String[] ingredients) {
        Ingredients = ingredients;
    }

    public String getCertification() {
        return Certification;
    }

    public void setCertification(String certification) {
        Certification = certification;
    }
}
