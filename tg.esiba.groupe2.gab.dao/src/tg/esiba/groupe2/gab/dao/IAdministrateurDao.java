/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.dao;

import java.util.List;
import tg.esiba.groupe2.gab.entiies.Administrateur;
import tg.esiba.groupe2.gab.entiies.Compte;
import tg.esiba.groupe2.gab.exception.AdministrateurException;

/**
 *
 * @author fad
 */
public interface IAdministrateurDao {
     /**
     * permet de creer un administrateur
     * @param administrateur , un objet de type administrateur
     * @return un administrateur
     * @throws tg.esiba.groupe2.gab.exception.AdministrateurException
     */
    public Administrateur creerAdministrateur(Administrateur administrateur) throws AdministrateurException;
    
    /**
     * recupere la liste de tous les administrateurs
     * @return une liste de type Administrateur
     */
    public List<Administrateur> getAllAdministrateur();
    
    /**
     * affiche un administrateur
     * @param numeroAdministrateur ,  numero du administrateur a afficher
     * @return le administrateur correspondant au codeAdministrateur
     */
    public Administrateur getOneAdministrateur(int numeroAdministrateur);
    
    /**
     * retourne la liste de tous les comptes géré par l'administrateur
     * @param numeroAdministraeur ,  numero du administrateur a afficher
     * @return une liste de compte
     */
    public List<Compte> getCompteGeree(int numeroAdministraeur);
    
    /**
     * permet de mettre à jour un administrateur
     * @param administrateur , le nouveau administrateur
     * @return le administrateur modifier
     */
    public Administrateur updateAdministrateur(Administrateur administrateur);
    
    /**
     * supprime un administrateur
     * @param numeroAdministrateur , numero de l'administrateur
     * @return 1 si la suppression a été éffectué
     */
    public int supprimerAdministrateur(int numeroAdministrateur);
}
