/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tsiry.tp4.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mg.itu.tsiry.tp4.entities.CompteBancaire;

/**
 *
 * @author tsiry
 */

@Singleton
@Startup
public class InsertionCompte {
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;
    
    @PostConstruct
    public void insertionComptes(){
       CompteBancaire c1=new CompteBancaire("John Lennon", 150000 );
       CompteBancaire c2=new CompteBancaire("Paul McCartney", 950000 );
       CompteBancaire c3=new CompteBancaire("Ringo Starr", 20000 );
       CompteBancaire c4=new CompteBancaire("Georges Harrisson", 100000 );
         em.persist(c1);
         em.persist(c2);
         em.persist(c3);
         em.persist(c4);
       
    }
    
}
