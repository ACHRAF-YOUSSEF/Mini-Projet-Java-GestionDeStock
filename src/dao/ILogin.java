package dao;

import metier.entity.Utilisateur;

public interface ILogin {
    boolean validate(Utilisateur u);
}
