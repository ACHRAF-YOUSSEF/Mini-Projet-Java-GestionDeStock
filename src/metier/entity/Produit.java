package metier.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Produit)) return false;
        final Produit other = (Produit) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$nom = this.getNom();
        final Object other$nom = other.getNom();
        if (this$nom == null ? other$nom != null : !this$nom.equals(other$nom)) return false;
        final Object this$categorie = this.getCategorie();
        final Object other$categorie = other.getCategorie();
        if (this$categorie == null ? other$categorie != null : !this$categorie.equals(other$categorie)) return false;
        if (Double.compare(this.getPrix(), other.getPrix()) != 0) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Produit;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getCode_produit();
        final Object $nom = this.getNom();
        result = result * PRIME + ($nom == null ? 43 : $nom.hashCode());
        final Object $categorie = this.getCategorie();
        result = result * PRIME + ($categorie == null ? 43 : $categorie.hashCode());
        final long $prix = Double.doubleToLongBits(this.getPrix());
        result = result * PRIME + (int) ($prix >>> 32 ^ $prix);
        return result;
    }
}
