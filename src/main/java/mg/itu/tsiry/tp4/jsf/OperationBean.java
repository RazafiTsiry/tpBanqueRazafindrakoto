/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tsiry.tp4.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;
import mg.itu.tsiry.tp4.ejb.GestionnaireCompte;
import mg.itu.tsiry.tp4.entities.CompteBancaire;
import mg.itu.tsiry.tp4.entities.OperationBancaire;

/**
 *
 * @author tsiry
 */
@Named(value = "operations")
@RequestScoped
public class OperationBean {
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    List<OperationBancaire> operations;
    private long idCompte;
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    public void loadOperations(){
        CompteBancaire compte=gestionnaireCompte.findByID(idCompte);
        operations=compte.getOperations();
        nom=compte.getNom();
    }

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }
    
    public List<OperationBancaire> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationBancaire> operations) {
        this.operations = operations;
    }
    
    
}
