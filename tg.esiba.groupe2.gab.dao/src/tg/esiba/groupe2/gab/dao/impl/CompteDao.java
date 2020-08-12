package tg.esiba.groupe2.gab.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tg.esiba.groupe2.gab.dao.ICompteDao;
import tg.esiba.groupe2.gab.entiies.Compte;
import tg.esiba.groupe2.gab.exception.MontantInsuffisantException;
import tg.esiba.groupe2.gab.utils.Connexion;

/**
 *
 * @author fad
 */
public class CompteDao implements ICompteDao {

    Statement state;

    /**
     * crée une nouvelle instance de Connexion
     */
    public CompteDao() {
        Connexion con = new Connexion();

        try {
            this.state = con.getStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @see ICompteDao#creereCompte(tg.esiba.groupe2.gab.entiies.Compte)
     */
    @Override
    public Compte creerCompte(Compte compte) {
        PreparedStatement req;
        SimpleDateFormat format = new SimpleDateFormat("DD/MM/yyyy");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "INSERT INTO comptes(numero_compte, solde, numero_client, numero_administrateur, date_creation) "
                    + "VALUES(?, ?, ?, ?, ?)");
            req.setString(1, compte.getNumeroCompte());
            req.setDouble(2, compte.getSolde());
            req.setInt(3, compte.getNumeroClient());
            req.setInt(4, compte.getNumeroAdministrateur());
            req.setString(5, format1.format(compte.getDateCreation()));

            req.executeUpdate();

            System.out.println("-------------------------------------------------");
            System.out.println("Compte créée");

        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return compte;
    }

    /**
     * @see ICompteDao#getAllCompte()
     */
    @Override
    public ObservableList<Compte> getAllCompte() {
        ObservableList<Compte> liste = FXCollections.observableArrayList();
        String url = "select * from comptes";
        ResultSet resultat = null;
                ClientDao cliD = new ClientDao();
        AdministrateurDao adminD = new AdministrateurDao();


        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (resultat.next()) {
               Compte comptes = new Compte(resultat.getInt(1), resultat.getString(2),
                        resultat.getDouble(3), cliD.getNomPrenomClient(resultat.getInt(4)), adminD.getNomAdmin(resultat.getInt(5)), resultat.getDate(6));
                liste.add(comptes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    /**
     * @see ICompteDao#getOneCompte(java.lang.Long)
     */
    @Override
    public Compte getOneCompte(String numeroCompte) {
        String url = "SELECT * FROM comptes WHERE numero_compte = " + numeroCompte + "";

        Compte compte = new Compte();
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                compte = new Compte(resultat.getInt(1),
                        resultat.getString(2), resultat.getDouble(3),
                        resultat.getInt(4), resultat.getInt(5), resultat.getDate(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return compte;
    }

    /**
     * @see ICompteDao#updateCompte(tg.esiba.groupe2.gab.entiies.Compte)
     */
    @Override
    public Compte updateCompte(Compte compte) {
        PreparedStatement req;
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "UPDATE comptes "
                    + "SET numero_administrateur = ?"
                    + "WHERE id_compte =? ");
            req.setInt(1, compte.getNumeroAdministrateur());
            req.setInt(2, compte.getIdCompte());
            req.executeUpdate();

            System.out.println("-------------------------------------------------");
            System.out.println("Client " + compte.getIdCompte() + " modifier");
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return compte;
    }

    /**
     * @see ICompteDao#supprimerCompte(java.lang.Long)
     */
    @Override
    public int supprimerCompte(int idCompte) {
        PreparedStatement req;
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "DELETE FROM comptes WHERE id_compte = ?");
            req.setLong(1, idCompte);
            req.executeUpdate();

            System.out.println("-------------------------------------------------");
            System.out.println("Compte " + idCompte + " supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

        return 1;
    }
    
    public int supprimerCompteClien(int numeroClient) {
        PreparedStatement req;
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "DELETE FROM comptes WHERE numero_client = ?");
            req.setLong(1, numeroClient);
            req.executeUpdate();

            System.out.println("-------------------------------------------------");
            System.out.println("Compte du client " + numeroClient + " supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

        return 1;
    }

    public String genererNumeroCompte() {
        List<String> comptes = new ArrayList<>();
        String url = "select numero_compte from comptes";
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (resultat.next()) {
                comptes.add(resultat.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String caracteres = "0123456789";
        String leNumero = "";
        String numero = "";
        int taille = 10;
        Random rand = new Random();
        char[] texte = new char[taille];

        for (int i = 0; i < taille; i++) {
            texte[i] = caracteres.charAt(rand.nextInt(caracteres.length()));
        }

        for (int i = 0; i < taille; i++) {
            leNumero += texte[i];
        }

        for (String c : comptes) {
            if (!leNumero.equals(c)) {
                numero = leNumero;
            }
        }
        return "6000" + numero;
    }

    @Override
    public void faireDepot(double montant, Compte destinataire) {
        PreparedStatement req;
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "UPDATE comptes SET solde = ? WHERE id_compte =? ");
            req.setDouble(1, destinataire.getSolde() + montant);
            req.setInt(2, destinataire.getIdCompte());
            req.executeUpdate();

            System.out.println("-------------------------------------------------");
            System.out.println("Client " + destinataire.getIdCompte() + " modifier");
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void faireRetrait(double montant, Compte compte) throws MontantInsuffisantException {
        String url = "select solde from comptes where numero_compte = " + compte.getNumeroCompte();
        ResultSet resultat = null;
        double solde = 0;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                solde = resultat.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (montant < solde) {
            PreparedStatement req;
            try {
                req = Connexion.getConnexion().prepareStatement(""
                        + "UPDATE comptes SET solde = ? WHERE id_compte =? ");
                req.setDouble(1, compte.getSolde() - montant);
                req.setInt(2, compte.getIdCompte());
                req.executeUpdate();

                System.out.println("-------------------------------------------------");
                System.out.println("Compte " + compte.getIdCompte() + " modifier");
            } catch (SQLException ex) {
                Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new MontantInsuffisantException("Solde insuffisant");
        }

    }

    public int nbrCompte() {
        PreparedStatement req = null;
        int nbre = 0;
        ResultSet res;
        try {
            req = Connexion.getConnexion().prepareStatement("SELECT COUNT(id_compte) FROM comptes");
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            res = req.executeQuery();
            while (res.next()) {
                nbre = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbre;
    }

    public ObservableList<Compte> searchCompte(String terme) {
        String url = "select * from comptes where numero_compte like '%" + terme + "%' ";

        ObservableList<Compte> liste = FXCollections.observableArrayList();
        ClientDao cliD = new ClientDao();

        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                Compte comptes = new Compte(resultat.getInt(1), resultat.getString(2),
                        resultat.getDouble(3), cliD.getNomPrenomClient(resultat.getInt(4)), resultat.getDate(6));
                liste.add(comptes);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;

    }
    
    @Override
    public double getSolde(int numeroClient){
        String url = "SELECT solde FROM comptes WHERE numero_client = " + numeroClient + "";
        
        double solde = 0 ;
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                solde = resultat.getDouble(1) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return solde;
    }

}
