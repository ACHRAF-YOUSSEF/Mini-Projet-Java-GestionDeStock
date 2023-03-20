package metier.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Produit {
    private int code_produit;
    private String nom;
    private String categorie;
    private double prix;

    public Produit(String nom, String categorie, double prix) {
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
    }
}
