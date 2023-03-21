package dao;

import metier.entity.*;

import java.util.List;

public interface IGestion {
    void ajouterProduit(Produit p);
    void modifierProduit(Produit p);
    void supprimerProduit(int id);
    Produit getProduit(int id);
    List<Produit> getAllProduit();
    List<Produit> getProduitPMC(String mc);

    void ajouterUtilisateur(Utilisateur u);
    void modifierUtilisateur(Utilisateur u);
    void supprimerUtilisateur(int id);
    Utilisateur getUtilisateur(int id);
    List<Utilisateur> getAllUtilisateur();
    List<Utilisateur> getUtilisateurPMC(String mc);

    void stocker(Inventaire i);
    void destocker(Inventaire i);
    List<Inventaire> getAllInventaire();
    List<Inventaire> getInventairePMC(String mc);

    List<Inventaire> getAllProduits();
    List<Inventaire> getProduitsPMC(String mc);

    List<Inventaire> getAllProduits_();
    void ajouterInventaire(Inventaire i);
}
