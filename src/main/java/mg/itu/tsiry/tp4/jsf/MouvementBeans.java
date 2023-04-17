/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tsiry.tp4.jsf;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import mg.itu.tsiry.tp4.ejb.GestionnaireCompte;
import mg.itu.tsiry.tp4.entities.CompteBancaire;
import mg.itu.tsiry.tp4.jsf.util.Util;

/**
 *
 * @author tsiry
 */
@Named(value = "mouvement")
@ViewScoped
public class MouvementBeans implements Serializable {

    @EJB
    private GestionnaireCompte gestionnaireCompte;

    private CompteBancaire compte;
    private long idCompte;
    @PositiveOrZero
    private int montant;

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public void loadCompte() {
        this.compte = gestionnaireCompte.findByID(idCompte);
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }

    public String ajouter() {
        try {
//            this.compte = gestionnaireCompte.findByID(idCompte);
            compte.deposer(montant);
            gestionnaireCompte.update(compte);
            Util.addFlashInfoMessage("Ajout d'argent correctement effectué");
            return "listeComptes?faces-redirect=true";
        } catch (EJBException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                if (cause instanceof OptimisticLockException) {
                    Util.messageErreur("Le compte de " + compte.getNom()
                            + " a été modifié ou supprimé par un autre utilisateur !");
                } else { // Afficher le message de ex si la cause n'est pas une OptimisticLockException
                    Util.messageErreur(cause.getMessage());
                }
            } else { // Pas de cause attachée à l'EJBException
                Util.messageErreur(e.getMessage());
            }
            return null;
        }

    }

    public String retirer() {
        try {
//            this.compte = gestionnaireCompte.findByID(idCompte);
            if (montant > compte.getSolde()) {
                Util.messageErreur("Solde insuffisant", "Solde insuffisant", "form:solde");
                return null;
            }
            compte.retirer(montant);
            gestionnaireCompte.update(compte);
            Util.addFlashInfoMessage("Retrait d'argent correctement effectué");
            return "listeComptes?faces-redirect=true";
        } catch (EJBException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                if (cause instanceof OptimisticLockException) {
                    Util.messageErreur("Le compte de " + compte.getNom()
                            + " a été modifié ou supprimé par un autre utilisateur !");
                } else { // Afficher le message de ex si la cause n'est pas une OptimisticLockException
                    Util.messageErreur(cause.getMessage());
                }
            } else { // Pas de cause attachée à l'EJBException
                Util.messageErreur(e.getMessage());
            }
            return null;
        }
    }

}
