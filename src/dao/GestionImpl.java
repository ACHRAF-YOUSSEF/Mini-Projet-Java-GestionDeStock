package dao;

import metier.entity.Inventaire;
import metier.entity.Produit;
import metier.entity.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionImpl implements IGestion {
    private static IGestion gestion;

    private GestionImpl() {}

    public static IGestion getGestion() {
        if (gestion == null) {
            gestion = new GestionImpl();
        }

        return gestion;
    }

    @Override
    public void ajouterProduit(Produit p) {
        Connection cx = SingletonConnection.getInstance();

        try {
            PreparedStatement st = cx.prepareStatement("insert into produit(nom, categorie, prix) values(?, ?, ?)");

            st.setString(1, p.getNom());
            st.setString(2, p.getCategorie());
            st.setDouble(3, p.getPrix());

            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void modifierProduit(Produit p) {

    }

    @Override
    public void supprimerProduit(int code) {
        Connection cx = SingletonConnection.getInstance();

        try {
            PreparedStatement st = cx.prepareStatement("delete from produit where code_produit= ?");

            st.setInt(1, code);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Produit getProduit(int code) {
        Connection cx = SingletonConnection.getInstance();
        Produit produit = null;

        try {
            PreparedStatement ps = cx.prepareStatement("select * from produit where code_produit= ?");

            ps.setInt(1, code);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                produit = new Produit(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)
                );

                produit.setCode_produit(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produit;
    }

    @Override
    public List<Produit> getAllProduit() {
        Connection cx = SingletonConnection.getInstance();
        List<Produit> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from produit");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produit produit = new Produit(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)
                );
                produit.setCode_produit(rs.getInt(1));

                list.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Produit> getProduitPMC(String mc) {
        Connection cx = SingletonConnection.getInstance();
        List<Produit> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from produit where nom like ?");

            ps.setString(1, "%" + mc + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produit produit = new Produit(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)
                );

                produit.setCode_produit(rs.getInt(1));

                list.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void ajouterUtilisateur(Utilisateur u) {
        Connection cx = SingletonConnection.getInstance();

        try {
            PreparedStatement st = cx.prepareStatement("insert into utilisateur(nom, mot_de_pass, admin) values(?, ?, ?)");

            st.setString(1, u.getNom());
            st.setString(2, u.getMot_de_pass());
            st.setInt(3, u.getAdmin());

            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void modifierUtilisateur(Utilisateur u) {
        Connection cx = SingletonConnection.getInstance();

        try {
            PreparedStatement st = cx.prepareStatement("update utilisateur set nom= ?, mot_de_pass= ?, admin= ? where id= ?");

            st.setString(1, u.getNom());
            st.setString(2, u.getMot_de_pass());
            st.setInt(3, u.getAdmin());
            st.setInt(4, u.getId());

            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void supprimerUtilisateur(int id) {
        Connection cx = SingletonConnection.getInstance();

        try {
            PreparedStatement st = cx.prepareStatement("delete from utilisateur where id= ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Utilisateur getUtilisateur(int id) {
        Connection cx = SingletonConnection.getInstance();
        Utilisateur utilisateur = null;

        try {
            PreparedStatement ps = cx.prepareStatement("select * from utilisateur where id= ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                 utilisateur = new Utilisateur(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );

                utilisateur.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateur;
    }

    @Override
    public List<Utilisateur> getAllUtilisateur() {
        Connection cx = SingletonConnection.getInstance();
        List<Utilisateur> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from utilisateur");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                utilisateur.setId(rs.getInt(1));

                list.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Utilisateur> getUtilisateurPMC(String mc) {
        Connection cx = SingletonConnection.getInstance();
        List<Utilisateur> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from utilisateur where nom like ?");

            ps.setString(1, "%" + mc + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                utilisateur.setId(rs.getInt(1));

                list.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void stocker(Inventaire i) {

    }

    @Override
    public void destocker(Inventaire i) {

    }

    @Override
    public List<Inventaire> getAllInventaire() {
        return null;
    }

    @Override
    public List<Inventaire> getInventairePMC(String mc) {
        return null;
    }

    @Override
    public List<Inventaire> getAllProduits() {
        return null;
    }

    @Override
    public List<Inventaire> getProduitsPMC(String mc) {
        return null;
    }

    @Override
    public List<Inventaire> getAllProduits_() {
        return null;
    }

    @Override
    public void ajouterInventaire(Inventaire i) {

    }
}
