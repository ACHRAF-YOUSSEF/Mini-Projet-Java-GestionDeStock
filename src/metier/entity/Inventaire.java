package metier.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inventaire {
    private int inventaireID;
    private int code_produit;
    private int quantite;

    public Inventaire(int code_produit, int quantite) {
        this.code_produit = code_produit;
        this.quantite = quantite;
    }
}
