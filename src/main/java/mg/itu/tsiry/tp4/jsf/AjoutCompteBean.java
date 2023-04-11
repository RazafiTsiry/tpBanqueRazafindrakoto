/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tsiry.tp4.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tsiry.tp4.ejb.GestionnaireCompte;
import mg.itu.tsiry.tp4.entities.CompteBancaire;
import mg.itu.tsiry.tp4.jsf.util.Util;

/**
 *
 * @author tsiry
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompteBean {
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    
    @NotNull
    private String nom;
    @PositiveOrZero
    private int solde;

    public String action(){
        CompteBancaire nouveau=new CompteBancaire(nom,solde);
        gestionnaireCompte.creerCompte(nouveau);
        Util.addFlashInfoMessage("Ajout du compte correctement effectué");
        return "ajoutCompte?faces-redirect=true";
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    
    
}
