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
 * classe de l'entité client
 *
 * @author groupe 2
 */
public class Client {

    private int numeroClient;
    private String nom;
    private String prenom;
    private String sexe;
    private String adresse;
    private int telephone;
    private String mail;
    private String passe;
    private List<Compte> compte;

    public Client() {
    }

    public Client(int numeroClient) {
        this.numeroClient = numeroClient;
    }

    public Client(int numeroClient, String nom, String prenom, String sexe, String adresse, int telephone, String mail, String passe) {
        this.numeroClient = numeroClient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
        this.passe = passe;
        this.sexe = sexe;
    }

    public Client(String nom, String prenom, String sexe, String adresse, int telephone, String mail, String passe) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
        this.passe = passe;
        this.sexe = sexe;

    }

    public Client(int numeroClient, String nom, String prenom, String sexe, String adresse, int telephone, String mail, String passe, List compte) {
        this.numeroClient = numeroClient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
        this.passe = passe;
        this.compte = compte;
        this.sexe = sexe;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(int numeroClient) {
        this.numeroClient = numeroClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone){
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPasse() {
        return passe;
    }

    public void setPasse(String passe) {
        this.passe = passe;
    }

    public List<Compte> getCompte() {
        return compte;
    }

    public void setCompte(ArrayList<Compte> compte) {
        this.compte = compte;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.numeroClient);
        hash = 41 * hash + Objects.hashCode(this.nom);
        hash = 41 * hash + Objects.hashCode(this.prenom);
        hash = 41 * hash + Objects.hashCode(this.adresse);
        hash = 41 * hash + this.telephone;
        hash = 41 * hash + Objects.hashCode(this.mail);
        hash = 41 * hash + Objects.hashCode(this.passe);
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
        final Client other = (Client) obj;
        if (this.telephone != other.telephone) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.passe, other.passe)) {
            return false;
        }
        if (!Objects.equals(this.numeroClient, other.numeroClient)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clients{" + "numeroClient=" + numeroClient + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", telephone=" + telephone + ", mail=" + mail + ", passe=" + passe + '}';
    }

}
