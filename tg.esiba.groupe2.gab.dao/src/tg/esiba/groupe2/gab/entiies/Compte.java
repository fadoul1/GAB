/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.entiies;

import java.util.Date;
import java.util.Objects;

/**
 * classe de l'entité compte
 *
 * @author fad
 */
public class Compte {

    private int idCompte;
    private String numeroCompte;
    private double solde;
    private int numeroClient;
    private int numeroAdministrateur;
    private Date dateCreation;
   
    //nom et prenom du titulaire du compte
    private String titulaire;
    private String administrateur;
    

    public Compte() {
    }

    public Compte(int idCompte, String numeroCompte, double solde, int numeroClient, int numeroAdministrateur, Date dateCreation) {
        this.idCompte = idCompte;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.numeroClient = numeroClient;
        this.numeroAdministrateur = numeroAdministrateur;
        this.dateCreation = dateCreation;
    }

    public Compte(String numeroCompte, double solde, int numeroClient, int numeroAdministrateur, Date dateCreation) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.numeroClient = numeroClient;
        this.numeroAdministrateur = numeroAdministrateur;
        this.dateCreation = dateCreation;
    }

    public Compte(int idCompte, String numeroCompte, double solde, String titulaire, Date dateCreation) {
        this.idCompte = idCompte;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.titulaire = titulaire;
    }

    public Compte(int idCompte, String numeroCompte, double solde, String titulaire, String administrateur, Date dateCreation) {
        this.idCompte = idCompte;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.titulaire = titulaire;
        this.administrateur = administrateur;
    }

    public String getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(String administrateur) {
        this.administrateur = administrateur;
    }
    
    

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    
    
    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(int numeroClient) {
        this.numeroClient = numeroClient;
    }

    public int getNumeroAdministrateur() {
        return numeroAdministrateur;
    }

    public void setNumeroAdministrateur(int numeroAdministrateur) {
        this.numeroAdministrateur = numeroAdministrateur;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.numeroCompte);
        hash = 89 * hash + Objects.hashCode(this.solde);
        hash = 89 * hash + (int) (this.numeroClient ^ (this.numeroClient >>> 32));
        hash = 89 * hash + (int) (this.numeroAdministrateur ^ (this.numeroAdministrateur >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compte other = (Compte) obj;
        if (this.numeroClient != other.numeroClient) {
            return false;
        }
        if (this.numeroAdministrateur != other.numeroAdministrateur) {
            return false;
        }
        if (!Objects.equals(this.numeroCompte, other.numeroCompte)) {
            return false;
        }
        if (!Objects.equals(this.solde, other.solde)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comptes{" + "numeroCompte=" + numeroCompte + ", solde=" + solde + ", numeroClient=" + numeroClient + ", numeroAdministrateur=" + numeroAdministrateur + '}';
    }

}
