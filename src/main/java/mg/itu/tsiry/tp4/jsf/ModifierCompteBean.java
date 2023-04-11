/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tsiry.tp4.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import mg.itu.tsiry.tp4.ejb.GestionnaireCompte;
import mg.itu.tsiry.tp4.entities.CompteBancaire;
import mg.itu.tsiry.tp4.jsf.util.Util;

/**
 *
 * @author tsiry
 */

@Named(value = "modifierCompte")
@ViewScoped
public class ModifierCompteBean implements Serializable{

    @EJB
    private GestionnaireCompte gestionnaireCompte;
    private long idCompte;

    private CompteBancaire compte;

    @NotNull
    private String nom;

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public void loadCompte() {
        this.compte = gestionnaireCompte.findByID(idCompte);
    }

    public String modifier() {
        if(compte.getSolde()<0){
            Util.messageErreur("Solde negatif", "Solde negatif", "form:solde");
            return null;
        }
        if(compte.getNom()==""){
            Util.messageErreur("Nom vide", "Nom vide", "form:nom");
            return null;
        }
        gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage("Modification du compte correctement effectué");
        return "listeComptes?faces-redirect=true";
    }
}
