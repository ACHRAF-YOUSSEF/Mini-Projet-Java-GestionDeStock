package metier.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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

    public Utilisateur(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom.substring(0, 1).toUpperCase() + nom.substring(1).toLowerCase();
    }
}
