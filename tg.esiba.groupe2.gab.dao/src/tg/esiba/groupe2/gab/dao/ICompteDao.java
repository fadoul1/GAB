/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.dao;
import java.util.List;
import tg.esiba.groupe2.gab.entiies.Compte;
import tg.esiba.groupe2.gab.exception.MontantInsuffisantException;


/**
 *
 * @author fad
 */
public interface ICompteDao {
     /**
     * permet de creer un compte
     * @param compte , un objet de type compte
     * @return un compte
     */
    public Compte creerCompte(Compte compte);
    
    /**
     * recupere la liste de tous les comptes
     * @return une liste de type Compte
     */
    public List<Compte> getAllCompte();
    
    /**
     * affiche un compte
     * @param numeroCompte ,  numero du compte a afficher
     * @return le compte correspondant au codeCompte
     */
    public Compte getOneCompte(String numeroCompte);
    
    /**
     * permet de mettre à jour un compte
     * @param compte , le nouveau compte
     * @return le compte modifier
     */
    public Compte updateCompte(Compte compte);
    
    /**
     * supprime un compte
     * @param idCompte , l'id du compte
     * @return 1 si la suppression a été éffectué
     */
    public int supprimerCompte(int idCompte);
    
    /**
     * fait un depôt
     * @param montant, le montant à deposer
     * @param destinataire , le compte destinataire
     */
    public void faireDepot(double montant,Compte destinataire);
    
    /**
     * fait un retrait
     * @param montant, le montant à retirer
     * @param compte , le compte 
     * @throws tg.esiba.groupe2.gab.exception.MontantInsuffisantException 
     */
    public void faireRetrait(double montant,Compte compte) throws MontantInsuffisantException;
    
    /**
     * retourne le solde d'un client
     * @param numeroClient, le numero du client
     * @return le solde
     */
    public double getSolde(int numeroClient);
}
