/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tg.esiba.groupe2.gab.dao.IAdministrateurDao;
import tg.esiba.groupe2.gab.entiies.Administrateur;
import tg.esiba.groupe2.gab.entiies.Compte;
import tg.esiba.groupe2.gab.exception.AdministrateurException;
import tg.esiba.groupe2.gab.utils.Connexion;

/**
 *
 * @author fad
 */
public class AdministrateurDao implements IAdministrateurDao {

    Statement state;

    /**
     * crée une nouvelle instance de Connexion
     */
    public AdministrateurDao() {
        Connexion con = new Connexion();

        try {
            this.state = con.getStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @see IAdministrateurDao#creereAdministrateur(tg.esiba.groupe2.gab.entiies.Administrateur)
     */
    @Override
    public Administrateur creerAdministrateur(Administrateur administrateur) throws AdministrateurException {
        
            if(adminUnique(administrateur.getNom())!=0){
                throw new AdministrateurException("Cet administrateur eiste déjà");
            }
            
        PreparedStatement req;
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "INSERT INTO administrateurs(nom, passe) "
                    + "VALUES(?, ?)");
            req.setString(1, administrateur.getNom());
            req.setString(2, administrateur.getPasse());
            req.executeUpdate();

            System.out.println("-------------------------------------------------");
            System.out.println("Administrateur créée");

        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return administrateur;
    }

    /**
     * @see IAdministrateurDao#getAllAdministrateur()
     */
    @Override
    public ObservableList<Administrateur> getAllAdministrateur() {
        ObservableList<Administrateur> liste = FXCollections.observableArrayList();
        String url = "select * from administrateurs";
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (resultat.next()) {
                Administrateur administrateur = new Administrateur(resultat.getInt(1),
                        resultat.getString(2), resultat.getString(3));
                liste.add(administrateur);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    /**
     * @see IAdministrateurDao#getOneAdministrateur(int)
     */
    @Override
    public Administrateur getOneAdministrateur(int numeroAdministrateur) {
        String url = "SELECT * FROM administrateurs WHERE numero_administrateur = " + numeroAdministrateur + "";

        Administrateur administrateur = new Administrateur();
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                administrateur = new Administrateur(resultat.getInt(1),
                        resultat.getString(2), resultat.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return administrateur;
    }

    /**
     * @see IAdministrateurDao#getCompteGeree(int)
     */
    @Override
    public ObservableList<Compte> getCompteGeree(int numeroAdministraeur) {
        ObservableList<Compte> liste = FXCollections.observableArrayList();
        String url = "select * from comptes where numero_administrateur = " + numeroAdministraeur;
        ResultSet resultat = null;
        ClientDao cliD = new ClientDao();
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (resultat.next()) {
                Compte comptes = new Compte(resultat.getInt(1), resultat.getString(2),
                        resultat.getDouble(3), cliD.getNomPrenomClient(resultat.getInt(4)), resultat.getDate(6));
                liste.add(comptes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    /**
     * @see
     * IAdministrateurDao#updateAdministrateur(tg.esiba.groupe2.gab.entiies.Administrateur)
     */
    @Override
    public Administrateur updateAdministrateur(Administrateur administrateur) {
        PreparedStatement req;
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "UPDATE administrateurs "
                    + "SET nom = ?, passe = ?"
                    + "WHERE numero_administrateur =? ");
            req.setString(1, administrateur.getNom());
            req.setString(2, administrateur.getPasse());
            req.setInt(3, administrateur.getNumeroAdministrateur());;
            req.executeUpdate();

            System.out.println("-------------------------------------------------");
            System.out.println("Administrateur " + administrateur.getNumeroAdministrateur() + " modifier");
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return administrateur;
    }

    /**
     * @see IAdministrateurDao#supprimerAdministrateur(java.lang.Long)
     */
    @Override
    public int supprimerAdministrateur(int numeroAdministrateur) {
        PreparedStatement req;
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "DELETE FROM administrateurs WHERE numero_administrateur = ?");
            req.setInt(1, numeroAdministrateur);
            req.executeUpdate();

            System.out.println("-------------------------------------------------");
            System.out.println("Administrateur " + numeroAdministrateur + " supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

        return 1;
    }

    public int adminUnique(String nom) {
        PreparedStatement req = null;
        int val = 0;
        ResultSet res;
        try {
            req = Connexion.getConnexion().prepareStatement("SELECT COUNT(numero_administrateur) FROM administrateurs  WHERE nom=?");
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            req.setString(1, nom);

            res = req.executeQuery();
            while (res.next()) {
                val = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;
    }
    
    public int nbrAdmin() {
        PreparedStatement req = null;
        int nbre = 0;
        ResultSet res;
        try {
            req = Connexion.getConnexion().prepareStatement("SELECT COUNT(numero_administrateur) FROM administrateurs");
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            res = req.executeQuery();
            while (res.next()) {
                nbre = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbre;
    }
    
    public String getNomAdmin(int numeroAdministrateur) {
        String url = "SELECT nom FROM administrateurs WHERE numero_administrateur = " + numeroAdministrateur + "";

        String admin = "";
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                admin = resultat.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return admin;
    }
    
    public List<String> list() {
        String url = "select nom from administrateurs";

        List<String> liste = new ArrayList<>();
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                liste.add(resultat.getString(1));       
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
    public int maxAdmin() {
        int nbre = 0;
        String url = "select COUNT(*) as 'nbre' from administrateurs";
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (resultat.next()) {
                nbre = resultat.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbre;
    }
    
    public int getIdAdmin(String nom) {
        String url = "select numero_administrateur "
                + "   from administrateurs  "
                + "   where nom = '" + nom + "' ";

        int a = 0;
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                a = resultat.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
}
