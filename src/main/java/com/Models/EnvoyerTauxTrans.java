package com.Models;

/**
 *
 * @author macbookpro
 */
public class EnvoyerTauxTrans {

    private Envoyer envoyer;
    private Transaction trans;
    private Tauxenv taux;


    public EnvoyerTauxTrans(Envoyer envoyer, Transaction trans, Tauxenv taux) {
        super();
        this.envoyer = envoyer;
        this.trans = trans;
        this.taux = taux;
    }
    
    
    public Envoyer getEnvoyer() {
        return envoyer;
    }

    public void setEnvoyer(Envoyer envoyer) {
        this.envoyer = envoyer;
    }

    public Transaction getTrans() {
        return trans;
    }

    public void setTrans(Transaction trans) {
        this.trans = trans;
    }

    public Tauxenv getTaux() {
        return taux;
    }

    public void setTaux(Tauxenv taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "EnoyerTauxTrans{" + "envoyer=" + envoyer + ", trans=" + trans + ", taux=" + taux + '}';
    }
    
    
    
}
