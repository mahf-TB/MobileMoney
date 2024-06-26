/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Models;

/**
 *
 * @author macbookpro
 */
public class Transaction {
    private int id;
    private String numEnvoyeur;
    private String numRecepteur;
    private String type;
    private double montant;
    private String date;

    
    public Transaction() {
        super();
    } 
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumEnvoyeur() {
        return numEnvoyeur;
    }

    public void setNumEnvoyeur(String numEnvoyeur) {
        this.numEnvoyeur = numEnvoyeur;
    }

    public String getNumRecepteur() {
        return numRecepteur;
    }

    public void setNumRecepteur(String numRecepteur) {
        this.numRecepteur = numRecepteur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", numEnvoyeur=" + numEnvoyeur + ", numRecepteur=" + numRecepteur + ", type=" + type + ", montant=" + montant + ", date=" + date + '}';
    }   
}
