/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.dao;

import java.util.List;
import javafx.collections.ObservableList;
import tg.esiba.groupe2.gab.entiies.Client;
import tg.esiba.groupe2.gab.entiies.Compte;
import tg.esiba.groupe2.gab.exception.ClientException;

/**
 *
 * @author fad
 */
public interface IClientDao {
    
    /**
     * permet de creer un client
     * @param client , un objet de type client
     * @param solde , le montant déposer à la création du compte
     * @param numAdmin, le numero de l'administrateur du compte du client
     * @return un client
     * @throws tg.esiba.groupe2.gab.exception.ClientException
     */
    public Client creerClient(Client client, Double solde, int numAdmin) throws ClientException;
    
    /**
     * recupere la liste de tous les clients
     * @return une liste de type Client
     */
    public List<Client> getAllClient();
    
    /**
     * affiche un client
     * @param numeroClient ,  numero du client à afficher
     * @return le client correspondant au codeClient
     */
    public Client getOneClient(int numeroClient);
    
    /**
     * retourne tous les comptes du client
     * @param numeroClient, le numero du client
     * @return une liste de compte
     */
    public List<Compte> getComptesClient(int numeroClient);
    
    /**
     * permet de mettre à jour un client
     * @param client , le nouveau client
     * @return le client modifier
     */
    public Client updateClient(Client client);
    
    /**
     * supprime un client
     * @param numeroClient , numero du client
     * @return 1 si la suppression a été éffectué
     */
    public int supprimerClient(int numeroClient);
    
    /**
     * Fait la recherche de clients qui ont pour nom ou prenom le terme passé en paramètre,
     * @param terme, le nom ou prenom du client
     * @return la liste des clients
     */
    public ObservableList<Client> searchClient(String terme);
}
