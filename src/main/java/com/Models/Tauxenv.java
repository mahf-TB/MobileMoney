/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Models;

/**
 *
 * @author macbookpro
 */
public class Tauxenv {
    private int id;
    private int montant_min;
    private int montant_max;
    private double fraisEnv;

    public Tauxenv() {
        super();
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMontant_min() {
        return montant_min;
    }

    public void setMontant_min(int montant_min) {
        this.montant_min = montant_min;
    }

    public int getMontant_max() {
        return montant_max;
    }

    public void setMontant_max(int montant_max) {
        this.montant_max = montant_max;
    }

    public double getFraisEnv() {
        return fraisEnv;
    }

    public void setFraisEnv(double fraisEnv) {
        this.fraisEnv = fraisEnv;
    }

    @Override
    public String toString() {
        return "Tauxenv{" + "id=" + id + ", montant_min=" + montant_min + ", montant_max=" + montant_max + ", fraisEnv=" + fraisEnv + '}';
    }
   
    

}
