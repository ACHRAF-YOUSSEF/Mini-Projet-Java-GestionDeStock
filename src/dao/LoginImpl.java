package dao;

import metier.entity.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginImpl implements ILogin {

    @Override
    public boolean validate(Utilisateur u) {
        Connection cx = SingletonConnection.getInstance();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from utilisateur where nom= ? and mot_de_pass= ? and admin=?");

            ps.setInt(3, u.getAdmin());
            ps.setString(1, u.getNom());
            ps.setString(2, u.getMot_de_pass());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
