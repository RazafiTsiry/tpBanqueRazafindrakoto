/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tsiry.tp4.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import mg.itu.tsiry.tp4.ejb.GestionnaireCompte;
import mg.itu.tsiry.tp4.entities.CompteBancaire;

/**
 *
 * @author tsiry
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    @EJB
    private GestionnaireCompte gestionnaireCompte;
    private List<CompteBancaire> compteBancaires;

    public List<CompteBancaire> getCompteBancaires() {
        if (compteBancaires == null) {
            compteBancaires = gestionnaireCompte.getAllComptes();
        }
        return compteBancaires;
    }

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

}
