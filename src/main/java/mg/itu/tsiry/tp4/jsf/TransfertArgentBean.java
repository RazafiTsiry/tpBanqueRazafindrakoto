/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tsiry.tp4.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import mg.itu.tsiry.tp4.ejb.GestionnaireCompte;
import mg.itu.tsiry.tp4.entities.CompteBancaire;
import mg.itu.tsiry.tp4.jsf.util.Util;

/**
 *
 * @author tsiry
 */
@Named(value = "transfertArgent")
@RequestScoped
public class TransfertArgentBean implements Serializable {

    @EJB
    private GestionnaireCompte gestionnaireCompte;
    private long idSource;
    private long idDestinataire;
    private int solde;

    public String transfert() {
        boolean erreur = false;
        CompteBancaire source = gestionnaireCompte.findByID(idSource);
        CompteBancaire destinataire = gestionnaireCompte.findByID(idDestinataire);
        if (source == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        }
        if (destinataire == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destinataire");
            erreur = true;
        }
        if (source != null && destinataire != null) {
            if (solde < 0) {
                Util.messageErreur("Solde negatif", "Solde negatif", "form:solde");
                erreur = true;
            } else {
                if (source.getSolde() < solde) {
                    Util.messageErreur("Solde insuffisant", "Solde insuffisant", "form:solde");
                    erreur = true;
                }

            }

        }

        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }
        // Message de succès ; addFlash à cause de la redirection.
        Util.addFlashInfoMessage("Transfert correctement effectué");
        gestionnaireCompte.transfert(idSource, idDestinataire, solde);
        return "transfertArgent?faces-redirect=true";
    }

    public long getIdSource() {
        return idSource;
    }

    public void setIdSource(long idSource) {
        this.idSource = idSource;
    }

    public long getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(long idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public TransfertArgentBean() {
    }
}
