/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tg.esiba.groupe2.gab.exception.AuthentificationException;

/**
 *
 * @author fad
 */
public class Authentification {

    public Authentification() {
    }

    public int clientAuthentification(String numeroCompte) throws AuthentificationException {
        int val = 0;
        ResultSet res;
        PreparedStatement req = null;
        try {
            req = Connexion.getConnexion().prepareStatement("SELECT COUNT(numero_compte) "
                    + "FROM comptes  WHERE numero_compte=?");
        } catch (SQLException ex) {
            Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            req.setString(1, numeroCompte);

            res = req.executeQuery();
            while (res.next()) {
                val = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (val != 1) {
            throw new AuthentificationException("Numero incorrect");
        }

        return val;

    }

    public int adminAuthentification(String nom, String passe) throws AuthentificationException {
        int val = 0;
        ResultSet res;
        PreparedStatement req = null;
        try {
            req = Connexion.getConnexion().prepareStatement("SELECT COUNT(numero_administrateur) FROM administrateurs  WHERE nom=? and passe=?");
        } catch (SQLException ex) {
            Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            req.setString(1, nom);
            req.setString(2, passe);

            res = req.executeQuery();
            while (res.next()) {
                val = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (val != 1) {
            throw new AuthentificationException("Combinaison incorrect");
        }
        return val;
    }
}
