package metier.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Utilisateur {
    private int id;
    private String nom;
    private String mot_de_pass;
    private int admin;

    public Utilisateur(String nom, String mot_de_pass, int admin) {
        this.nom = nom;
        this.mot_de_pass = mot_de_pass;
        this.admin = admin;
    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Utilisateur)) return false;
        final Utilisateur other = (Utilisateur) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$nom = this.getNom();
        final Object other$nom = other.getNom();
        if (this$nom == null ? other$nom != null : !this$nom.equals(other$nom)) return false;
        final Object this$mot_de_pass = this.getMot_de_pass();
        final Object other$mot_de_pass = other.getMot_de_pass();
        if (this$mot_de_pass == null ? other$mot_de_pass != null : !this$mot_de_pass.equals(other$mot_de_pass))
            return false;
        if (this.getAdmin() != other.getAdmin()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Utilisateur;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $nom = this.getNom();
        result = result * PRIME + ($nom == null ? 43 : $nom.hashCode());
        final Object $mot_de_pass = this.getMot_de_pass();
        result = result * PRIME + ($mot_de_pass == null ? 43 : $mot_de_pass.hashCode());
        result = result * PRIME + this.getAdmin();
        return result;
    }
}
