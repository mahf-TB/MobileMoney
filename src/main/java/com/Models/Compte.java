/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Models;

/**
 *
 * @author macbookpro
 */
public class Compte {

    private int id;
    private String numero;
    private String motdepasse;
    private boolean isActive;
    private double solde;
    private int id_client;

    public Compte() {
        super();
    }
       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Compte{" + "id=" + id + ", numero=" + numero + ", motdepasse=" + motdepasse + ", isActive=" + isActive + ", solde=" + solde + ", id_client=" + id_client + '}';
    }
         
}
