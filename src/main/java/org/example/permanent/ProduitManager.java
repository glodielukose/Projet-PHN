package org.example.permanent;
import com.google.gson.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import org.example.produit.Alimentaire;
import org.example.produit.Cosmetique;
import org.example.produit.Menager;
import org.example.produit.Produit;

import java.io.*;
import java.util.*;

public class ProduitManager {
    private static final String FILE_PATH = "produits.json";
    private final Gson gson;
    Scanner sc = new Scanner(System.in);

    public ProduitManager() {
        RuntimeTypeAdapterFactory<Produit> typeFactory = RuntimeTypeAdapterFactory
                .of(Produit.class, "type")
                .registerSubtype(Cosmetique.class, "cosmetique")
                .registerSubtype(Alimentaire.class, "alimentaire")
                .registerSubtype(Menager.class, "menager");

        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(typeFactory)
                .setPrettyPrinting()
                .create();
    }

    public List<Produit> chargerProduits() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Produit[] produitsArray = gson.fromJson(reader, Produit[].class);
            return produitsArray != null ? new ArrayList<>(Arrays.asList(produitsArray)) : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void sauvegarderProduits(List<Produit> produits) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(produits, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterProduit(Produit produit) {
        List<Produit> produits = chargerProduits();
        produits.add(produit);
        sauvegarderProduits(produits);
    }

    public boolean modifierProduit(Produit nouveauProduit) {
        System.out.println("Veillez entrer l'id du produit");
        String id = sc.nextLine();
        List<Produit> produits = chargerProduits();

        for (int i = 0; i < produits.size(); i++) {
            if (produits.get(i).getIdProduit().equals(id)) { // suppose que Produit a une méthode getId()
                produits.set(i, nouveauProduit);
                sauvegarderProduits(produits);
                return true;
            }
        }

        return false;
    }

    public boolean supprimerProduit() {
        System.out.println("Veillez entrer l'id du prosuit");
        String index = sc.nextLine();

        List<Produit> produits = chargerProduits();
        for(int i = 0; i < produits.size(); i++) {
            if (produits.get(i).getIdProduit().equals(index)) { // suppose que Produit a une méthode getId()
                produits.remove(i);
                sauvegarderProduits(produits);
                return true;
            }
        }
        return false;
    }

    // Méthode alternative pour supprimer par référence d'objet
    public boolean supprimerProduit(Produit produit) {
        List<Produit> produits = chargerProduits();
        boolean removed = produits.remove(produit);
        if (removed) {
            sauvegarderProduits(produits);
        }
        return removed;
    }

    // Méthode pour trouver un produit par son identifiant (si vous en avez un)
    public Optional<Produit> trouverProduitParId(String id) {
        return chargerProduits().stream()
                .filter(p -> p.getIdProduit().equals(id)) // suppose que Produit a une méthode getId()
                .findFirst();
    }
}