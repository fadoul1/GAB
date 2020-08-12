package tg.esiba.groupe2.gab.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tg.esiba.groupe2.gab.dao.IClientDao;
import tg.esiba.groupe2.gab.entiies.Client;
import tg.esiba.groupe2.gab.entiies.Compte;
import tg.esiba.groupe2.gab.exception.ClientException;
import tg.esiba.groupe2.gab.utils.Connexion;

/**
 *
 * @author fad
 */
public class ClientDao implements IClientDao {

    Statement state;

    /**
     * crée une nouvelle instance de Connexion
     */
    public ClientDao() {
        Connexion con = new Connexion();

        try {
            this.state = con.getStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param numAdmin
     * @see IClientDao#creerClient(tg.esiba.groupe2.gab.entiies.Client)
     */
    @Override
    public Client creerClient(Client client, Double solde, int numAdmin) throws ClientException {

        if (clientUnique(client.getNom(), client.getPrenom()) != 0) {
            throw new ClientException("Ce client exixte déjà");
        }

        PreparedStatement req;
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "INSERT INTO clients(nom, prenom, sexe, adresse, telephone, mail, passe) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)");
            req.setString(1, client.getNom());
            req.setString(2, client.getPrenom());
            req.setString(3, client.getSexe());
            req.setString(4, client.getAdresse());
            req.setInt(5, client.getTelephone());
            req.setString(6, client.getMail());
            req.setString(7, this.genererPasse());
            
            if (req.executeUpdate() != -1) {
                CompteDao compteD = new CompteDao();
                Compte compte = new Compte();
                compte.setDateCreation(Date.valueOf(LocalDate.now()));
                compte.setNumeroAdministrateur(numAdmin);
                compte.setNumeroClient(this.getIdClient(client.getNom(), client.getPrenom()));
                compte.setNumeroCompte(compteD.genererNumeroCompte());
                compte.setSolde(solde);
                compteD.creerCompte(compte);
            }

            System.out.println("-------------------------------------------------");
            System.out.println("Client créée");

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
    }

    /**
     * @see IClientDao#getAllClient()
     */
    @Override
    public ObservableList<Client> getAllClient() {
        ObservableList<Client> liste = FXCollections.observableArrayList();
        String url = "select * from clients";
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (resultat.next()) {
                //List<Compte> comptes = this.getComptesClient(resultat.getInt(1));
                Client client = new Client(resultat.getInt(1),
                        resultat.getString(2), resultat.getString(3),
                        resultat.getString(4), resultat.getString(5), resultat.getInt(6),
                        resultat.getString(7), resultat.getString(8));
                liste.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    /**
     * @see IClientDao#getOneClient(int)
     */
    @Override
    public Client getOneClient(int numeroClient) {
        String url = "SELECT * FROM clients WHERE numero_client = " + numeroClient + "";

        Client client = new Client();
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                client = new Client(resultat.getInt(1),
                        resultat.getString(2), resultat.getString(3),
                        resultat.getString(4), resultat.getString(5), resultat.getInt(6),
                        resultat.getString(7), resultat.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
    }

    public String getNomPrenomClient(int numeroClient) {
        String url = "SELECT nom, prenom FROM clients WHERE numero_client = " + numeroClient + "";

        String client = "";
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                client = resultat.getString(1) + " " + resultat.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return client;
    }
    
    public List<String> list() {
        String url = "select nom, prenom from clients";

        List<String> liste = new ArrayList<>();
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                liste.add(resultat.getString(1)+"  "+resultat.getString(2));
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    /**
     * @see IClientDao#updateClient(tg.esiba.groupe2.gab.entiies.Client)
     */
    @Override
    public Client updateClient(Client client) {
        PreparedStatement req;
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "UPDATE clients "
                    + "SET nom = ?, prenom = ?, adresse = ?, telephone = ?, mail = ? "
                    + "WHERE numero_client = ? ");
            req.setString(1, client.getNom());
            req.setString(2, client.getPrenom());
            req.setString(3, client.getAdresse());
            req.setInt(4, client.getTelephone());
            req.setString(5, client.getMail());
            req.setInt(6, client.getNumeroClient());
            req.executeUpdate();

            System.out.println("-------------------------------------------------");
            System.out.println("Client " + client.getNom() + " modifier");
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return client;
    }

    /**
     * @see IClientDao#supprimerClient(int)
     */
    @Override
    public int supprimerClient(int numeroClient) {
        PreparedStatement req;
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "DELETE FROM clients WHERE numero_client = ?");
            req.setInt(1, numeroClient);
            if (req.executeUpdate() != -1) {
                CompteDao compteD = new CompteDao();
                compteD.supprimerCompteClien(numeroClient);
            }

            System.out.println("-------------------------------------------------");
            System.out.println("Client " + numeroClient + " supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

        return 1;
    }

    @Override
    public List<Compte> getComptesClient(int numeroClient) {
        List<Compte> liste = new ArrayList<>();
        String url = "select * from comptes where numero_client = " + numeroClient;
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (resultat.next()) {
                Compte comptes = new Compte(resultat.getInt(1), resultat.getString(2),
                        resultat.getDouble(3), resultat.getInt(4),
                        resultat.getInt(5), resultat.getDate(6));
                liste.add(comptes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

    public String genererPasse() {
        List<String> passes = new ArrayList<>();
        String url = "select passe from clients";
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (resultat.next()) {
                passes.add(resultat.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabdefghijklmnopqrstuvwxz0123456789";
        String lePasse = "";
        String passe = "";
        int taille = 5;
        Random rand = new Random();
        char[] texte = new char[taille];

        for (int i = 0; i < taille; i++) {
            texte[i] = caracteres.charAt(rand.nextInt(caracteres.length()));
        }

        for (int i = 0; i < taille; i++) {
            lePasse += texte[i];
        }

        for (String p : passes) {
            if (!lePasse.equals(p)) {
                passe = lePasse;
            }
        }
        return passe;
    }

    public int clientUnique(String nom, String prenom) {
        PreparedStatement req = null;
        int val = 0;
        ResultSet res;
        try {
            req = Connexion.getConnexion().prepareStatement("SELECT COUNT(numero_client) FROM clients  WHERE nom=? and prenom=?");
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            req.setString(1, nom);
            req.setString(2, prenom);

            res = req.executeQuery();
            while (res.next()) {
                val = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;
    }

    public int nbrClient() {
        PreparedStatement req = null;
        int nbre = 0;
        ResultSet res;
        try {
            req = Connexion.getConnexion().prepareStatement("SELECT COUNT(numero_client) FROM clients");
        } catch (SQLException ex) {
            Logger.getLogger(AdministrateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            res = req.executeQuery();
            while (res.next()) {
                nbre = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbre;
    }

    public int getIdClient(String nom, String prenom) {
        String url = "select numero_client "
                + "   from clients "
                + "   where nom = '" + nom + "' && prenom = '" + prenom + "'";

        int a = 0;
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                a = resultat.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public int maxClient() {
        int nbre = 0;
        String url = "select COUNT(*) as 'nbre' from clients";
        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (resultat.next()) {
                nbre = resultat.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbre;
    }
    
    @Override
    public ObservableList<Client> searchClient(String terme) {
        String url = "select * from clients where nom like '%" + terme + "%' or prenom like '%" + terme + "%' ";

        ObservableList<Client> liste = FXCollections.observableArrayList();

        ResultSet resultat = null;
        try {
            resultat = state.executeQuery(url);
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (resultat.next()) {
                Client client = new Client(resultat.getInt(1),
                        resultat.getString(2), resultat.getString(3),
                        resultat.getString(4), resultat.getString(5), resultat.getInt(6),
                        resultat.getString(7), resultat.getString(8));
                liste.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;

    }
    
    public Client updatePasse(Client client) {
        PreparedStatement req;
        try {
            req = Connexion.getConnexion().prepareStatement(""
                    + "UPDATE clients "
                    + "SET passe = ? "
                    + "WHERE numero_client = ? ");
            req.setString(1, client.getPasse());
            req.setInt(2, client.getNumeroClient());
            req.executeUpdate();

            System.out.println("-------------------------------------------------");
            System.out.println("Client " + client.getNom() + " modifier");
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return client;
    }
    
}
