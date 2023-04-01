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
    private int code_produit;
    private int quantite;
    private int idTransaction;
    private String remarques;
    private Date date;
    private IGestion gestion = GestionImpl.getGestion();

    public Inventaire(int code_produit, int quantite, int idTransaction, String remarques, Date date) {
        this.code_produit = code_produit;
        this.quantite = quantite;
        this.idTransaction = idTransaction;
        this.remarques = remarques;
        this.date = date;
    }
}
