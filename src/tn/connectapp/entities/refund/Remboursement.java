/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.entities.refund;

import java.util.Date;

/**
 *
 * @author i_dkh
 */
public class Remboursement {
        private Integer id;
        private Date date;
    	private String nom;
	private String club;
	private String email;
	private String ville;
	private int telephone;
	private String demande; 
        private String Etatdemande;
        private Integer idUser;
    public Remboursement(Integer id, Date date, String nom, String club, String email, String ville, int telephone, String demande, String Etatdemande, Integer idUser) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.club = club;
        this.email = email;
        this.ville = ville;
        this.telephone = telephone;
        this.demande = demande;
        this.Etatdemande = Etatdemande;
        this.idUser = idUser;
    }

    public Remboursement() {
    }

    @Override
    public String toString() {
        return "Remboursement{" + "id=" + id + ", nom=" + nom + ", club=" + club + ", email=" + email + ", ville=" + ville + ", telephone=" + telephone + ", demande=" + demande + ", Etatdemande=" + Etatdemande + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getDemande() {
        return demande;
    }

    public void setDemande(String demande) {
        this.demande = demande;
    }

    public String getEtatdemande() {
        return Etatdemande;
    }

    public void setEtatdemande(String Etatdemande) {
        this.Etatdemande = Etatdemande;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
}