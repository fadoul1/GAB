/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.entiies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe de l'entité Administrateur
 *
 * @author groupe2
 */
public class Administrateur {

    private int numeroAdministrateur;
    private String nom;
    private String passe;
    private List<Compte> comptes;

    public Administrateur() {
    }

    public Administrateur(int numeroAdministrateur) {
        this.numeroAdministrateur = numeroAdministrateur;
    }

    public Administrateur(String nom, String passe) {
        this.nom = nom;
        this.passe = passe;
    }

    
    
    public Administrateur(int numeroAdministrateur, String nom, String passe) {
        this.numeroAdministrateur = numeroAdministrateur;
        this.nom = nom;
        this.passe = passe;
    }
    
    

    public Administrateur(int numeroAdministrateur, String nom, String passe, List comptes) {
        this.numeroAdministrateur = numeroAdministrateur;
        this.nom = nom;
        this.passe = passe;
        this.comptes = comptes;
    }

    public int getNumeroAdministrateur() {
        return numeroAdministrateur;
    }

    public void setNumeroAdministrateur(int numeroAdministrateur) {
        this.numeroAdministrateur = numeroAdministrateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPasse() {
        return passe;
    }

    public void setPasse(String passe) {
        this.passe = passe;
    }

    public List<Compte> getCompte() {
        return comptes;
    }

    public void setCompte(ArrayList<Compte> comptes) {
        this.comptes = comptes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.numeroAdministrateur);
        hash = 59 * hash + Objects.hashCode(this.nom);
        hash = 59 * hash + Objects.hashCode(this.passe);
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
        final Administrateur other = (Administrateur) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.passe, other.passe)) {
            return false;
        }
        if (!Objects.equals(this.numeroAdministrateur, other.numeroAdministrateur)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Administrateur{" + "numeroAdministrateur=" + numeroAdministrateur + ", nom=" + nom + ", passe=" + passe + '}';
    }

}
