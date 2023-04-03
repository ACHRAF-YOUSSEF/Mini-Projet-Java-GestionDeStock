package metier.entity;

import dao.GestionImpl;
import dao.IGestion;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Inventaire {
    private int inventaireID;
    private int quantite;
    private int idTransaction;
    private String remarques;
    private Date date;
    private Produit produit;

    public Inventaire(int quantite, int idTransaction, String remarques, Date date, Produit produit) {
        this.produit = produit;
        this.quantite = quantite;
        this.idTransaction = idTransaction;
        this.remarques = remarques;
        this.date = date;
    }
}
