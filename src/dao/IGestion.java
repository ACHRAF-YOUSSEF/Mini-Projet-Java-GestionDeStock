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
    Inventaire getInventaire(int id);
    List<Inventaire> getAllInventaire();
    List<Inventaire> getInventairePMC(String mc);

    List<Inventaire> getAllInventaire_();
    Inventaire ajouterInventaire(Inventaire inventaire);
    List<Inventaire> popInventaire();
    void imprimer(String FILE_NAME, List<Inventaire> list, double argent, double money);
}
