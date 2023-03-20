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
            PreparedStatement ps = cx.prepareStatement("select * from utilisateur");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(rs.getString(2), rs.getString(3), rs.getInt(4));
                utilisateur.setId(rs.getInt(1));

                if (utilisateur.equals(u)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
