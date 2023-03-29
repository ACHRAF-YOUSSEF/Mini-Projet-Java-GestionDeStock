package metier.entity;

import lombok.*;

@ToString
public class Pdf {
    private int id;
    private static int count;
    private String nom;

    public Pdf() {
        id = ++count;
        nom = "pdf " + id + ".pdf";
    }

    public Pdf(String nom) {
        id = ++count;
        this.nom = nom + ".pdf";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
