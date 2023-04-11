/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tsiry.tp4.ejb;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import mg.itu.tsiry.tp4.entities.CompteBancaire;

/**
 *
 * @author tsiry
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "root", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public void update(CompteBancaire c){
        em.merge(c);    
    }
    
    public void delete(CompteBancaire c){
        CompteBancaire cc=em.merge(c);
        em.remove(cc);
    }
    
    public void transfert(long id,long idD,int solde){
        CompteBancaire destinateur=this.findByID(id);
        CompteBancaire destinataire=this.findByID(idD);
        destinateur.retirer(solde);
        destinataire.deposer(solde);
        em.persist(destinateur);
        em.persist(destinataire);
    }
    
    public CompteBancaire findByID(long id){
        return (CompteBancaire)em.find(CompteBancaire.class, id);
    }
    
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }    
}
