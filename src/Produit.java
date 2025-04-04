public class Produit {
    protected String idProduit;
    protected int Qte;
    protected String marque;
    protected String dateExpiration;

    public Produit(String idProduit, int qte, String marque, String dateExpiration) {
        this.idProduit = idProduit;
        Qte = qte;
        this.marque = marque;
        this.dateExpiration = dateExpiration;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int qte) {
        Qte = qte;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public void afficherProduit() {
        System.out.println("ID Produit: " + idProduit);
        System.out.println("Quantité: " + Qte);
        System.out.println("Marque: " + marque);
        System.out.println("Date d'Expiration: " + dateExpiration);
    }
}
