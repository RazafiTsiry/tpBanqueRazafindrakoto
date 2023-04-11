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

/**
 *
 * @author tsiry
 */

@Named(value ="transfertArgent")
@RequestScoped
public class TransfertArgentBean implements Serializable{
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    private long idSource;
    private long idDestinataire;
    private int solde;

    public void transfert(){
        gestionnaireCompte.transfert(idSource, idDestinataire, solde);
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
    
    public TransfertArgentBean (){}
}
