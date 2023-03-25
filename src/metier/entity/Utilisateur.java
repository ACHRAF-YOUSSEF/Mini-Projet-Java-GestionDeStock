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
}
