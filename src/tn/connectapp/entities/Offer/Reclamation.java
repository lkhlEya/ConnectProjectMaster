/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.entities.Offer;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author haifaj
 */
public class Reclamation {
        private int id;
	private String type;
	private String nom;
	private String prenom;
	private String titre;
	private String description;
	private Date date_declaration;
        private String answer;
	private String status;
        
        public Reclamation () {
		
	}
	
	public Reclamation (int id,String nom,String prenom,String titre,Date date, 
			String description,String answer,String status) {
		this.id= id;
		this.nom = nom;
		this.prenom = prenom;
		this.titre= titre;
		this.description= description;
		this.date_declaration= date;
                this.answer = answer;
		this.status = status;
		
		
	}
        public Reclamation (int id,String nom,String prenom,String type,String titre,Date date, 
			String description,String answer,String status) {
		this.id= id;
		this.nom = nom;
		this.prenom = prenom;
		this.titre= titre;
		this.description= description;
		this.date_declaration= date;
                this.answer = answer;
		this.status = status;
                this.type = type;
		
		
	}
	public Reclamation (int id,String nom,String prenom,String type,String titre, 
			Date date_declaration,String description,String status) {
		this.id= id;
		this.type = type;
		this.nom = nom;
		this.prenom = prenom;
		this.titre= titre;
		this.description= description;
		this.date_declaration= date_declaration;
		this.status = status;
		
		
	}
        public Reclamation (int id, String titre,String description,String type, 
			String status) {
		this.id= id;
		this.type = type;
		this.nom = nom;
		this.prenom = prenom;
		this.titre= titre;
		this.description= description;
		this.date_declaration= date_declaration;
		this.status = status;
		
		
	}
       public Reclamation (String type,String nom, String prenom, String titre,Date date,String description) {
		this.nom= nom;
		this.titre= titre;
                this.date_declaration= date;
		this.description= description;
		this.status = status;	
	}

    public Reclamation(String nom, String titre, Date date, String description, String status) {
                this.nom= nom;
		this.titre= titre;
                this.date_declaration= date;
		this.description= description;
		this.status = status;	
    }
	
	public int getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	
	public String getTitre() {
		return titre;
	}
        public String getAnswer() {
		return answer;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	
	public String getDescription() {
		return description;
	}
	public String getStatus() {
		return status;
	}
	public Date getDate_declaration() {
		return date_declaration;
	}
	
	
	public void setId(int id) {
		this.id= id;
	}
	public void setType(String type) {
		this.type= type;
	}
	
	public void setTitre(String titre) {
		this.titre= titre;
	}
	public void setNom(String nom) {
		this.nom= nom;
	}
	public void setPrenom(String prenom) {
		this.prenom= prenom;
	}
	
	public void setDescription(String description ) {
		this.description = description;
	}
	
	
	public void setDeclaration(Date date_declaration ) {
		this.date_declaration = date_declaration;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String ToString() {
		return "reclamations : "+id+type+titre+nom+prenom+description+date_declaration+status;
	}
}
