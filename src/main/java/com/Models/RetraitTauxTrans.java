/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Models;

/**
 *
 * @author macbookpro
 */
public class RetraitTauxTrans {
    
    private Retrait retrait;
    private Transaction trans;
    private Tauxrecp taux;

    
    public RetraitTauxTrans(Retrait retrait, Transaction trans, Tauxrecp taux) {
        super();
        this.retrait = retrait;
        this.trans = trans;
        this.taux = taux;
    }
 
    public Retrait getRetrait() {
        return retrait;
    }

    public void setRetrait(Retrait retrait) {
        this.retrait = retrait;
    }

    public Transaction getTrans() {
        return trans;
    }

    public void setTrans(Transaction trans) {
        this.trans = trans;
    }

    public Tauxrecp getTaux() {
        return taux;
    }

    public void setTaux(Tauxrecp taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "RetraitTauxTrans{" + "retrait=" + retrait + ", trans=" + trans + ", taux=" + taux + '}';
    }

    
}
